package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbStatisticsItem;
import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.entity.vo.TbStatisticsItemMonthVo;
import com.yins.health.entity.vo.TbStatisticsItemYearVo;

import java.util.List;

/**
 * (TbStatisticsItem)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
public interface TbStatisticsItemService extends IService<TbStatisticsItem> {
    void month();
    void day_week();

    List<TbStatisticsItemMonthVo> selectMonthAll(TbStatisticsItemDto tbStatisticsItemDto);

    List<TbStatisticsItemYearVo> selectYearAll(TbStatisticsItemDto tbStatisticsItemDto);
}

