package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.vo.TbQuestionnaireItemVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷收集表;(TbQuestionnaireItem)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-28 14:06:23
 */
public interface TbQuestionnaireItemDao extends BaseMapper<TbQuestionnaireItem> {

    @Select("select i.*,q.name as questionnaire_name from tb_questionnaire_item i inner join tb_questionnaire q on i.questionnaire_id = q.id " +
            "where i.id = #{id}")
    TbQuestionnaireItemVo getTbQuestionnaireItemVoById(@Param("id") Serializable id);

    @Select({
            "<script>",
            "select q.name as questionnaire_name,i.* from tb_questionnaire_item i inner join tb_questionnaire q on i.questionnaire_id = q.id ",
            "WHERE i.del = 0",
            "<if test='dto.name != null and dto.name != \"\"'>",
            "  AND i.name LIKE CONCAT('%', #{dto.name}, '%')",
            "</if>",
            "<if test='dto.pushName != null and dto.pushName != \"\"'>",
            "  AND i.push_name LIKE CONCAT('%', #{dto.pushName}, '%')",
            "</if>",
            "<if test='dto.label != null and dto.label != \"\"'>",
            "  AND i.label = #{dto.label}",
            "</if>",
            "<if test='dto.questionnaireName != null and dto.questionnaireName != \"\"'>",
            "  AND q.name LIKE CONCAT('%', #{dto.questionnaireName}, '%')",
            "</if>",
            "<if test='dto.beginTime != null and dto.beginTime != \"\"'>",
            "  AND i.CREATED_TIME &gt;= #{dto.beginTime}",
            "</if>",
            "<if test='dto.endTime != null and dto.endTime != \"\"'>",
            "  AND i.CREATED_TIME &lt;= #{dto.endTime}",
            "</if>",
            " order by i.id",
            "</script>"
    })
    IPage<TbQuestionnaireItemVo> selectByPage(IPage<TbQuestionnaireItemVo> page,@Param("dto") TbQuestionnaireItemDto tbQuestionnaireItemDto);

    @Select({
            "<script>",
            "select q.name as questionnaire_name,i.* from tb_questionnaire_item i inner join tb_questionnaire q on i.questionnaire_id = q.id ",
            "WHERE i.del = 0",
            "<if test='dto.name != null and dto.name != \"\"'>",
            "  AND i.name LIKE CONCAT('%', #{dto.name}, '%')",
            "</if>",
            "<if test='dto.pushName != null and dto.pushName != \"\"'>",
            "  AND i.push_name LIKE CONCAT('%', #{dto.pushName}, '%')",
            "</if>",
            "<if test='dto.label != null and dto.label != \"\"'>",
            "  AND i.label = #{dto.label}",
            "</if>",
            "<if test='dto.questionnaireName != null and dto.questionnaireName != \"\"'>",
            "  AND q.name LIKE CONCAT('%', #{dto.questionnaireName}, '%')",
            "</if>",
            "<if test='dto.beginTime != null and dto.beginTime != \"\"'>",
            "  AND i.CREATED_TIME &gt;= #{dto.beginTime}",
            "</if>",
            "<if test='dto.endTime != null and dto.endTime != \"\"'>",
            "  AND i.CREATED_TIME &lt;= #{dto.endTime}",
            "</if>",
            " order by i.id",
            "</script>"
    })
    List<TbQuestionnaireItemVo> selectByList(@Param("dto") TbQuestionnaireItemDto tbQuestionnaireItemDto);

    @Select("select count(*) as works,DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') as time from tb_questionnaire_item " +
            "where CREATED_user = #{userId} and del = 0 and state = '有效' and DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') >= #{beginTime} " +
            "and DATE_FORMAT(CREATED_TIME, '%Y-%m-%d') <= #{endTime} group by DATE_FORMAT(CREATED_TIME, '%Y-%m-%d')")
    List<TbStatisticsItemVDto> findTbStatisticsItemVDto(@Param("userId")String userId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}

