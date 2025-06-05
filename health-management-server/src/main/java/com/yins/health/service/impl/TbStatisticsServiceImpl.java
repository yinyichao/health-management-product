package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbStatisticsDao;
import com.yins.health.dao.TbUserDao;
import com.yins.health.entity.TbStatistics;
import com.yins.health.entity.TbTaskUser;
import com.yins.health.entity.TbUser;
import com.yins.health.entity.vo.TbTaskVo;
import com.yins.health.service.TbStatisticsService;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (TbStatistics)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
@Service("tbStatisticsService")
public class TbStatisticsServiceImpl extends ServiceImpl<TbStatisticsDao, TbStatistics> implements TbStatisticsService {
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public void deleteTbStatistics(List<TbTaskUser> list,String year) {
        TbStatistics tbStatistics;
        for (TbTaskUser tbTaskUser : list) {
            tbStatistics = baseMapper.selectOne(new LambdaQueryWrapper<TbStatistics>().eq(TbStatistics::getYear,year)
                    .eq(TbStatistics::getUserId,tbTaskUser.getUserId())
                    .eq(TbStatistics::getTaskId,tbTaskUser.getTaskId()).eq(TbStatistics::getDel,0));
            if(tbStatistics!=null){
                tbStatistics.setDel(1);
                baseMapper.updateById(tbStatistics);
            }
        }
    }

    @Override
    public void saveTbStatistics(TbTaskVo tbTaskVo) {
        List<String> userIds = tbTaskVo.getUserIds();
        TbUser tbUser;
        TbStatistics tbStatistics;
        List<TbStatistics> list = new ArrayList<>();
        for (String userId : userIds) {
            tbStatistics = baseMapper.selectOne(new LambdaQueryWrapper<TbStatistics>().eq(TbStatistics::getUserId, userId)
                    .eq(TbStatistics::getYear, tbTaskVo.getYear()).eq(TbStatistics::getDel,0));
            if(tbStatistics != null) {
                tbStatistics.setDel(1);
                baseMapper.updateById(tbStatistics);
            }
            tbUser = tbUserDao.selectById(userId);
            tbStatistics = new TbStatistics();
            tbStatistics.setUserId(tbUser.getId());
            tbStatistics.setUserName(tbUser.getUsername());
            tbStatistics.setYear(tbTaskVo.getYear());
            tbStatistics.setTaskId(tbTaskVo.getId());
            list.add(tbStatistics);
        }
        this.saveBatch(list);
    }

    @Override
    public void updateTbStatistics(TbTaskVo tbTaskVo, List<TbTaskUser> deleteList) {
        TbUser tbUser;
        TbStatistics tbStatistics;
        List<String> collect = deleteList.stream()
                .map(TbTaskUser::getUserId)  // 提取每个对象的id
                .collect(Collectors.toList());// 收集成List
        List<String> userIds = tbTaskVo.getUserIds();
        List<String> difference = TbRuleModelUtil.getDifference(collect, userIds);
        for (String id : difference) {
            tbStatistics = baseMapper.selectOne(new LambdaQueryWrapper<TbStatistics>().eq(TbStatistics::getUserId, id)
                    .eq(TbStatistics::getYear, tbTaskVo.getYear())
                    .eq(TbStatistics::getTaskId,tbTaskVo.getId()).eq(TbStatistics::getDel,0));
            tbStatistics.setDel(1);
            baseMapper.updateById(tbStatistics);
        }
        List<TbStatistics> list = new ArrayList<>();
        for (String userId : userIds) {
            tbStatistics = baseMapper.selectOne(new LambdaQueryWrapper<TbStatistics>().eq(TbStatistics::getUserId, userId)
                    .eq(TbStatistics::getYear, tbTaskVo.getYear()).eq(TbStatistics::getDel,0));
            if(tbStatistics == null) {
                tbUser = tbUserDao.selectById(userId);
                tbStatistics = new TbStatistics();
                tbStatistics.setUserId(tbUser.getId());
                tbStatistics.setUserName(tbUser.getUsername());
                tbStatistics.setYear(tbTaskVo.getYear());
                tbStatistics.setTaskId(tbTaskVo.getId());
                list.add(tbStatistics);
            }
        }
        this.saveBatch(list);
    }
}

