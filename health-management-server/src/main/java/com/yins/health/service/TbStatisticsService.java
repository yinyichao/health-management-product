package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbStatistics;
import com.yins.health.entity.TbTaskUser;
import com.yins.health.entity.vo.TbTaskVo;

import java.util.List;

/**
 * (TbStatistics)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
public interface TbStatisticsService extends IService<TbStatistics> {
    void deleteTbStatistics(List<TbTaskUser> list,String year);
    void saveTbStatistics(TbTaskVo tbTaskVo);
    void updateTbStatistics(TbTaskVo tbTaskVo,List<TbTaskUser> deleteList);
}

