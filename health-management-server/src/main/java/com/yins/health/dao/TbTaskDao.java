package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yins.health.entity.TbTask;
import com.yins.health.entity.dto.TbTaskDto;
import com.yins.health.entity.vo.TbTaskPageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 任务表;(TbTask)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
public interface TbTaskDao extends BaseMapper<TbTask> {
    @Select({
            "<script>",
            "SELECT r.id, r.name, r.CREATED_TIME,",
            "  (SELECT IFNULL(COUNT(m.id), 0) FROM tb_task_user m WHERE r.id = m.task_id) AS num ",
            "FROM tb_task r ",
            "WHERE r.del = 0",
            "<if test='dto.name != null and dto.name != \"\"'>",
            "  AND r.name LIKE CONCAT('%', #{dto.name}, '%')",
            "</if>",
            "<if test='dto.beginTime != null and dto.beginTime != \"\"'>",
            "  AND r.CREATED_TIME &gt;= #{dto.beginTime}",
            "</if>",
            "<if test='dto.endTime != null and dto.endTime != \"\"'>",
            "  AND r.CREATED_TIME &lt;= #{dto.endTime}",
            "</if>",
            " order by r.CREATED_TIME",
            "</script>"
    })
    IPage<TbTaskPageVo> selectByPage(IPage<TbTaskPageVo> page, @Param("dto") TbTaskDto tbTaskDto);
}

