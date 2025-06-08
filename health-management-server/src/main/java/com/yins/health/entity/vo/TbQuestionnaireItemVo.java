package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yins.health.entity.TbQuestionnaireItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TbQuestionnaireItemVo extends TbQuestionnaireItem {
    @ApiModelProperty(value = "问卷名称")
    @ExcelProperty(value = "问卷名称")
    private String questionnaireName;
}
