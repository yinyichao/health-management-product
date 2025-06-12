package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yins.health.entity.TbQuestionnaire;
import com.yins.health.entity.dto.TbQuestionnaireDto;
import com.yins.health.entity.vo.TbQuestionnaireVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 采集问卷管理表;(TbQuestionnaire)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public interface TbQuestionnaireDao extends BaseMapper<TbQuestionnaire> {
    @Select({
            "<script>",
            "select q.id,q.ref,q.name,q.created_time,q.state,(select IFNULL(COUNT(i.id), 0) from tb_questionnaire_item i where q.id = i.questionnaire_id) as num ",
            "from tb_questionnaire q ",
            "WHERE q.del = 0 ",
            "<if test='dto.name != null and dto.name != \"\"'>",
            "  AND q.name LIKE CONCAT('%', #{dto.name}, '%')",
            "</if>",
            "<if test='dto.ref != null and dto.ref != \"\"'>",
            "  AND q.ref LIKE CONCAT('%', #{dto.ref}, '%')",
            "</if>",
            "<if test='dto.state != null and dto.state != \"\"'>",
            "  AND q.state = #{dto.state}",
            "</if>",
            "<if test='dto.beginTime != null and dto.beginTime != \"\"'>",
            "  AND q.CREATED_TIME &gt;= #{dto.beginTime}",
            "</if>",
            "<if test='dto.endTime != null and dto.endTime != \"\"'>",
            "  AND q.CREATED_TIME &lt;= #{dto.endTime}",
            "</if>",
            " order by q.CREATED_TIME desc",
            "</script>"
    })
    IPage<TbQuestionnaireVo> selectByPage(IPage<TbQuestionnaireVo> page, @Param("dto") TbQuestionnaireDto dto);
}

