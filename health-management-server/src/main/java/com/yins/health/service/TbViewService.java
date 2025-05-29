package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.dto.TbViewDto;

import java.util.List;

/**
 * 面见管理表;(TbView)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
public interface TbViewService extends IService<TbView> {

    IPage<TbView> pageByTbAdd(TbViewDto tbViewDto);

    void saveTbView(TbView tbView);

    List<TbStatisticsItemVDto> findTbStatisticsItemVDto(Integer userId,String beginTime);
}

