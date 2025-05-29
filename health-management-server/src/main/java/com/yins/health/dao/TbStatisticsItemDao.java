package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yins.health.entity.TbStatisticsItem;
import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.entity.vo.TbStatisticsItemYearVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (TbStatisticsItem)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
public interface TbStatisticsItemDao extends BaseMapper<TbStatisticsItem> {
    @Select({
            "<script>",
            "select user_name,type,max(year) AS year,SUM(CASE WHEN views_works > views_tasks THEN 1 ELSE 0 END) AS views_num," +
                    "SUM(CASE WHEN add_works > add_tasks THEN 1 ELSE 0 END) AS add_num," +
                    "SUM(CASE WHEN questionnaire_works > questionnaire_tasks THEN 1 ELSE 0 END) AS questionnaire_num ",
                    "from tb_statistics_item where type !=2",
            "<if test='dto.userName != null and dto.userName != \"\"'>",
            "  AND user_name LIKE CONCAT('%', #{dto.userName}, '%')",
            "</if>",
            "<if test='dto.year != null and dto.year != \"\"'>",
            "  AND year = #{dto.year}",
            "</if>",
            " group by user_name,type order by user_name",
            "</script>"
    })
    List<TbStatisticsItemYearVo> selectYearAll(@Param("dto") TbStatisticsItemDto tbStatisticsItemDto);
}

