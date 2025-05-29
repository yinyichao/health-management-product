package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yins.health.entity.TbAdd;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 增员管理表;(TbAdd)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public interface TbAddDao extends BaseMapper<TbAdd> {
    @Select("select count(*) as works,DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') as time from tb_add " +
            "where CREATED_user = #{userId} and del = 0 and state = '有效' and DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') >= #{beginTime} " +
            "and DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') <= #{endTime} group by DATE_FORMAT(CREATED_TIME, '%Y-%m-%d')")
    List<TbStatisticsItemVDto> findTbStatisticsItemVDto(@Param("userId")Integer userId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}

