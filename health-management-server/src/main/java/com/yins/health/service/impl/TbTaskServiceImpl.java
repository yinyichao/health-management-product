package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbTaskDao;
import com.yins.health.entity.TbTask;
import com.yins.health.entity.TbTaskUser;
import com.yins.health.entity.dto.TbTaskDto;
import com.yins.health.entity.vo.TbTaskPageVo;
import com.yins.health.entity.vo.TbTaskVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbTaskService;
import com.yins.health.service.TbTaskUserService;
import com.yins.health.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
    @Override
    public void removeTbTask(Integer id) {
        TbTaskVo tbTaskVo = new TbTaskVo();
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbTaskVo.setUpdatedUser(String.valueOf(userid));
        tbTaskVo.setId(id);
        tbTaskVo.setDel(1);
        baseMapper.updateById(tbTaskVo);
        tbTaskUserService.remove(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId,id));
    }

    @Override
    public void updateTbTask(TbTaskVo tbTaskVo) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbTaskVo.setUpdatedUser(String.valueOf(userid));
        baseMapper.updateById(tbTaskVo);
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
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbTaskVo.setCreatedUser(String.valueOf(userid));
        baseMapper.insert(tbTaskVo);
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
    public TbTaskVo getTbTaskById(Serializable id) {
        TbTask tbTask = baseMapper.selectById(id);
        TbTaskVo tbTaskVo = CommonUtil.convert(tbTask, TbTaskVo.class);
        List<TbTaskUser> taskUserList = tbTaskUserService.list(new LambdaQueryWrapper<TbTaskUser>().eq(TbTaskUser::getTaskId, id));
        List<Integer> userIdList = taskUserList.stream()
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
        return baseMapper.selectByPage(page,tbTaskDto);
    }
}

