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
            "SELECT user_name,MAX(year) AS year",
            "<if test='dto.type == 1'>",
                    ",SUM(CASE WHEN type = 0 AND views_works > views_tasks THEN 1 ELSE 0 END) AS day_num,",
                    "SUM(CASE WHEN type = 1 AND views_works > views_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "<if test='dto.type == 2'>",
            ",SUM(CASE WHEN type = 0 AND add_works > add_tasks THEN 1 ELSE 0 END) AS day_num,",
            "SUM(CASE WHEN type = 1 AND add_works > add_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "<if test='dto.type == 3'>",
            ",SUM(CASE WHEN type = 0 AND questionnaire_works > questionnaire_tasks THEN 1 ELSE 0 END) AS day_num,",
            "SUM(CASE WHEN type = 1 AND questionnaire_works > questionnaire_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "FROM tb_statistics_item ",
            "WHERE type != 2 ",
            "<if test='dto.userName != null and dto.userName != \"\"'>",
            "  AND user_name LIKE CONCAT('%', #{dto.userName}, '%')",
            "</if>",
            "<if test='dto.year != null and dto.year != \"\"'>",
            "  AND year = #{dto.year}",
            "</if>",
            " group by user_name order by user_name",
            "</script>"
    })
    List<TbStatisticsItemYearVo> selectYearAll(@Param("dto") TbStatisticsItemDto tbStatisticsItemDto);
}

