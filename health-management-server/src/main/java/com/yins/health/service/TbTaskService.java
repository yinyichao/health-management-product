package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbTask;
import com.yins.health.entity.dto.TbTaskDto;
import com.yins.health.entity.vo.TbTaskPageVo;
import com.yins.health.entity.vo.TbTaskVo;

import java.io.Serializable;

/**
 * 任务表;(TbTask)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
public interface TbTaskService extends IService<TbTask> {

    void removeTbTask(Integer id);

    void updateTbTask(TbTaskVo tbTaskVo);

    void saveTbTask(TbTaskVo tbTaskVo);

    TbTaskVo getTbTaskById(Serializable id);

    IPage<TbTaskPageVo> selectByPage(TbTaskDto tbTaskDto);
}

