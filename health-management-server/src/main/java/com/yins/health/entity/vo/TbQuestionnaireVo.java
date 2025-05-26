package com.yins.health.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TbQuestionnaireVo {
    private Integer id;
    private String name;
    private String ref;
    private Integer state;
    private Date createdTime;
    private Integer num;
}
