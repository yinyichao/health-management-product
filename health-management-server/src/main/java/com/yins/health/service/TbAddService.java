package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbAdd;
import com.yins.health.entity.dto.TbAddDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;

import java.util.List;

/**
 * 增员管理表;(TbAdd)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public interface TbAddService extends IService<TbAdd> {

    IPage<TbAdd> pageByTbAdd(TbAddDto tbAddDto);

    void saveTbAdd(TbAdd tbAdd);
    List<TbStatisticsItemVDto> findTbStatisticsItemVDto(String userId,String beginTime,String endTime);

    List<TbAdd> listByTbAdd(TbAddDto tbAddDto);
}

