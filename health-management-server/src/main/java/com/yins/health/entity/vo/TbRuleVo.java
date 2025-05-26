package com.yins.health.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TbRuleVo {
    private Integer id;
    private String name;
    private String content;
    private Integer state;
    private String ruleType;
    private Date createdTime;
    private Integer controlNum;
}
