package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbTaskDao;
import com.yins.health.entity.*;
import com.yins.health.entity.dto.TbTaskDto;
import com.yins.health.entity.vo.TbMobileVo;
import com.yins.health.entity.vo.TbTaskPageVo;
import com.yins.health.entity.vo.TbTaskVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.*;
import com.yins.health.util.CommonUtil;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务表;(TbTask)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
@Service("tbTaskService")
public class TbTaskServiceImpl extends ServiceImpl<TbTaskDao, TbTask> implements TbTaskService {

    @Autowired
    private TbTaskUserService tbTaskUserService;
    @Autowired
    private TbQuestionnaireItemService tbQuestionnaireItemService;
    @Autowired
    private TbViewService tbViewService;
    @Autowired
    private TbAddService tbAddService;
    @Autowired
    private TbStatisticsService tbStatisticsService;

    @Override
    public void removeTbTask(Integer id) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        TbTask tbTask = baseMapper.selectById(id);
        tbTask.setUpdatedUser(userid);
        tbTask.setId(id);
        tbTask.setDel(1);
        baseMapper.updateById(tbTask);
        List<TbTaskUser> taskUserList = tbTaskUserService.list(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId, id));
        tbStatisticsService.deleteTbStatistics(taskUserList,tbTask.getYear());
        tbTaskUserService.remove(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId,id));
    }

    @Override
    public void updateTbTask(TbTaskVo tbTaskVo) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbTaskVo.setUpdatedUser(userid);
        baseMapper.updateById(tbTaskVo);
        List<TbTaskUser> taskUserList = tbTaskUserService.list(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId, tbTaskVo.getId()));
        tbStatisticsService.updateTbStatistics(tbTaskVo,taskUserList);
        tbTaskUserService.remove(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId,tbTaskVo.getId()));
        List<TbTaskUser> list = new ArrayList<TbTaskUser>();
        tbTaskVo.getUserIds().forEach(userId -> {
            TbTaskUser tbTaskUser = new TbTaskUser();
            tbTaskUser.setTaskId(tbTaskVo.getId());
            tbTaskUser.setUserId(userId);
            list.add(tbTaskUser);
        });
        tbTaskUserService.saveBatch(list);
    }

    @Override
    public void saveTbTask(TbTaskVo tbTaskVo) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbTaskVo.setCreatedUser(userid);
        baseMapper.insert(tbTaskVo);
        List<TbTaskUser> list = new ArrayList<TbTaskUser>();
        tbTaskVo.getUserIds().forEach(userId -> {
            TbTaskUser tbTaskUser = new TbTaskUser();
            tbTaskUser.setTaskId(tbTaskVo.getId());
            tbTaskUser.setUserId(userId);
            list.add(tbTaskUser);
        });
        tbStatisticsService.saveTbStatistics(tbTaskVo);
        tbTaskUserService.saveBatch(list);
    }

    @Override
    public TbTaskVo getTbTaskById(Serializable id) {
        TbTask tbTask = baseMapper.selectById(id);
        if(tbTask == null){
            return null;
        }
        TbTaskVo tbTaskVo = CommonUtil.convert(tbTask, TbTaskVo.class);
        List<TbTaskUser> taskUserList = tbTaskUserService.list(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId, id));
        List<String> userIdList = taskUserList.stream()
                .map(TbTaskUser::getUserId)
                .distinct()
                .collect(Collectors.toList());
        if(!userIdList.isEmpty()){
            tbTaskVo.setUserIds(userIdList);
        }
        return tbTaskVo;
    }

    @Override
    public IPage<TbTaskPageVo> selectByPage(TbTaskDto tbTaskDto) {
        IPage<TbTaskPageVo> page = new Page<>();
        page.setCurrent(tbTaskDto.getPageNum());
        page.setSize(tbTaskDto.getPageSize());
        tbTaskDto.setBeginTime(tbTaskDto.getBeginDateTime());
        tbTaskDto.setEndTime(tbTaskDto.getEndDateTime());
        return baseMapper.selectByPage(page,tbTaskDto);
    }

    @Override
    public List<TbMobileVo> seleteOne() {
        String userid = LoginInterceptor.threadLocal.get().getId();
        TbTaskUser taskUser = tbTaskUserService.getOne(
                new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getUserId, userid)
        );

        if (taskUser == null) {
            return new ArrayList<>(); // 或者抛出异常，视业务而定
        }
        TbTask tbTask = this.getOne(
                new LambdaQueryWrapper<TbTask>().eq(TbTask::getDel, 0).eq(TbTask::getId, taskUser.getTaskId())
        );
        List<TbMobileVo> list = new ArrayList<TbMobileVo>();
        TbMobileVo tbMobileVo = new TbMobileVo();
        tbMobileVo.setType("面见/陪访");
        tbMobileVo.setDayTasks(tbTask.getDayViewsTasks());
        int counts = tbViewService.count(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                .eq(TbView::getCreatedUser,userid).ge(TbView::getUpdatedTime, TbRuleModelUtil.day()));
        tbMobileVo.setDayWorks(counts);
        tbMobileVo.setWeekTasks(tbTask.getWeekViewsTasks());
        counts = tbViewService.count(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                .eq(TbView::getCreatedUser,userid).ge(TbView::getUpdatedTime, TbRuleModelUtil.week()));
        tbMobileVo.setWeekWorks(counts);
        getMonth(tbMobileVo,tbTask,1);
        counts = tbViewService.count(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                .eq(TbView::getCreatedUser,userid).ge(TbView::getUpdatedTime, TbRuleModelUtil.month()));
        tbMobileVo.setMonthWorks(counts);

        list.add(tbMobileVo);
        tbMobileVo = new TbMobileVo();
        tbMobileVo.setType("客户信息采集");
        tbMobileVo.setDayTasks(tbTask.getDayQuestionnaireTasks());
        counts = tbQuestionnaireItemService.count(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                .eq(TbQuestionnaireItem::getCreatedUser,userid).ge(TbQuestionnaireItem::getUpdatedTime, TbRuleModelUtil.day()));
        tbMobileVo.setDayWorks(counts);
        tbMobileVo.setWeekTasks(tbTask.getWeekQuestionnaireTasks());
        counts = tbQuestionnaireItemService.count(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                .eq(TbQuestionnaireItem::getCreatedUser,userid).ge(TbQuestionnaireItem::getUpdatedTime, TbRuleModelUtil.week()));
        tbMobileVo.setWeekWorks(counts);
        getMonth(tbMobileVo,tbTask,2);
        counts = tbQuestionnaireItemService.count(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                .eq(TbQuestionnaireItem::getCreatedUser,userid).ge(TbQuestionnaireItem::getUpdatedTime, TbRuleModelUtil.month()));
        tbMobileVo.setMonthWorks(counts);

        list.add(tbMobileVo);
        tbMobileVo = new TbMobileVo();
        tbMobileVo.setType("增员面试");
        tbMobileVo.setDayTasks(tbTask.getDayAddTasks());
        counts = tbAddService.count(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0).eq(TbAdd::getState, "有效")
                .eq(TbAdd::getCreatedUser,userid).ge(TbAdd::getUpdatedTime, TbRuleModelUtil.day()));
        tbMobileVo.setDayWorks(counts);
        tbMobileVo.setWeekTasks(tbTask.getWeekAddTasks());
        counts = tbAddService.count(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0).eq(TbAdd::getState, "有效")
                .eq(TbAdd::getCreatedUser,userid).ge(TbAdd::getUpdatedTime, TbRuleModelUtil.week()));
        tbMobileVo.setWeekWorks(counts);
        getMonth(tbMobileVo,tbTask,3);
        counts = tbAddService.count(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0).eq(TbAdd::getState, "有效")
                .eq(TbAdd::getCreatedUser,userid).ge(TbAdd::getUpdatedTime, TbRuleModelUtil.month()));
        tbMobileVo.setMonthWorks(counts);
        list.add(tbMobileVo);
        return list;
    }


    private static void getMonth(TbMobileVo tbMobileVo,TbTask tbTask,Integer type) {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        switch (month) {
            case 1:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getOneViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getOneQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getOneAddTasks());
                }
                break;
            case 2:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getTwoViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getTwoQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getTwoAddTasks());
                }
                break;
            case 3:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getThreeViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getThreeQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getThreeAddTasks());
                }
                break;
            case 4:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getFourViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getFourQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getFourAddTasks());
                }
                break;
            case 5:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getFiveViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getFiveQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getFiveAddTasks());
                }
                break;
            case 6:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getSixViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getSixQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getSixAddTasks());
                }
                break;
            case 7:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getSevenViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getSevenQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getSevenAddTasks());
                }
                break;
            case 8:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getEightViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getEightQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getEightAddTasks());
                }
                break;
            case 9:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getNineViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getNineQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getNineAddTasks());
                }
                break;
            case 10:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getTenViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getTenQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getTenAddTasks());
                }
                break;
            case 11:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getElevenViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getElevenQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getElevenAddTasks());
                }
                break;
            case 12:
                if(type == 1) {
                    tbMobileVo.setMonthTasks(tbTask.getTwelveViewsTasks());
                }else if(type == 2) {
                    tbMobileVo.setMonthTasks(tbTask.getTwelveQuestionnaireTasks());
                }else{
                    tbMobileVo.setMonthTasks(tbTask.getTwelveAddTasks());
                }
                break;
        }
    }
}

