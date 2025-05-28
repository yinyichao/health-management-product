package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.dto.TbRuleDto;
import com.yins.health.entity.vo.TbRuleVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 风险规则表;(TbRule)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:35
 */
public interface TbRuleDao extends BaseMapper<TbRule> {

    @Select({
            "<script>",
            "SELECT r.id, r.name, r.content, r.rule_type, r.state, r.CREATED_TIME,",
            "  (SELECT IFNULL(COUNT(m.id), 0) FROM tb_rule_model m WHERE r.id = m.rule_id) AS control_num ",
            "FROM tb_rule r ",
            "WHERE r.del = 0 AND r.type = #{tbRuleDto.type}",
            "<if test='tbRuleDto.name != null and tbRuleDto.name != \"\"'>",
            "  AND r.name LIKE CONCAT('%', #{tbRuleDto.name}, '%')",
            "</if>",
            "<if test='tbRuleDto.ruleType != null and tbRuleDto.ruleType != \"\"'>",
            "  AND r.rule_type = #{tbRuleDto.ruleType}",
            "</if>",
            "<if test='tbRuleDto.beginTime != null and tbRuleDto.beginTime != \"\"'>",
            "  AND r.CREATED_TIME &gt;= #{tbRuleDto.beginTime}",
            "</if>",
            "<if test='tbRuleDto.endTime != null and tbRuleDto.endTime != \"\"'>",
            "  AND r.CREATED_TIME &lt;= #{tbRuleDto.endTime}",
            "</if>",
            " order by r.id",
            "</script>"
    })
    IPage<TbRuleVo> selectByPage(IPage<TbRuleVo> page, @Param("tbRuleDto") TbRuleDto tbRuleDto);
}

