package com.yins.health.entity.vo;

import com.yins.health.entity.TbTask;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TbTaskVo extends TbTask {
    @ApiModelProperty(value = "用户ID集合")
    private List<String> userIds;
}
