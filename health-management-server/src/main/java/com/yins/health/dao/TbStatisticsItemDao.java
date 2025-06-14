package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yins.health.entity.TbStatisticsItem;
import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.entity.vo.TbStatisticsItemVo;
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
            "select a.user_name,a.dept_name,a.year,IFNULL(b.day_num,0) as day_num,IFNULL(b.week_num,0) as week_num from (select i.user_name,i.user_id,i.year,d.name as dept_name from tb_statistics i left join tb_user_dept u on i.user_id = u.user_id left join tb_dept d on u.dept_id = d.id ",
                    "<if test='dto.userName != null and dto.userName != \"\"'>",
                    "  WHERE (i.user_name LIKE CONCAT('%', #{dto.userName}, '%') or d.name LIKE CONCAT('%', #{dto.userName}, '%') )",
                    "</if>",
                    ") a left join ",
                    "(SELECT i.user_name,i.user_id,d.name as dept_name,MAX(i.year) AS year",
            "<if test='dto.type == 1'>",
                    ",SUM(CASE WHEN i.type = 0 AND i.views_works > i.views_tasks THEN 1 ELSE 0 END) AS day_num,",
                    "SUM(CASE WHEN i.type = 1 AND i.views_works > i.views_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "<if test='dto.type == 2'>",
            ",SUM(CASE WHEN i.type = 0 AND i.add_works > i.add_tasks THEN 1 ELSE 0 END) AS day_num,",
            "SUM(CASE WHEN i.type = 1 AND add_works > i.add_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "<if test='dto.type == 3'>",
            ",SUM(CASE WHEN i.type = 0 AND i.questionnaire_works > i.questionnaire_tasks THEN 1 ELSE 0 END) AS day_num,",
            "SUM(CASE WHEN i.type = 1 AND i.questionnaire_works > i.questionnaire_tasks THEN 1 ELSE 0 END) AS week_num ",
            "</if>",
            "FROM tb_statistics_item i left join tb_user_dept u on i.user_id = u.user_id left join tb_dept d on u.dept_id = d.id ",
            "WHERE i.type != 2 ",
            "<if test='dto.year != null and dto.year != \"\"'>",
            "  AND i.year = #{dto.year}",
            "</if>",
            " group by i.user_name,d.name ) b on a.user_id = b.user_id order by dept_name",
            "</script>"
    })
    List<TbStatisticsItemYearVo> selectYearAll(@Param("dto") TbStatisticsItemDto tbStatisticsItemDto);

    @Select({
            "<script>",
            "select (select task_id from tb_statistics where id = i.statistics_id) as task_id, d.id as dept_id,d.name as dept_name,i.* from tb_statistics_item i left join tb_user_dept u on i.user_id = u.user_id left join tb_dept d on u.dept_id = d.id ",
            "where i.type = 2",
            "<if test='dto.year != null and dto.year != \"\"'>",
            "  AND i.year = #{dto.year}",
            "</if>",
            "<if test='dto.userName != null and dto.userName != \"\"'>",
            "  AND (i.user_name LIKE CONCAT('%', #{dto.userName}, '%') or d.name LIKE CONCAT('%', #{dto.userName}, '%') )",
            "</if>",
            "order by d.id",
            "</script>"
    })
    List<TbStatisticsItemVo> selectMonthAll(@Param("dto") TbStatisticsItemDto tbStatisticsItemDto);
}

