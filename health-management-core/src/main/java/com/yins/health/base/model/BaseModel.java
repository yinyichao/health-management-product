package com.yins.health.base.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author hankun
 * @Description
 * @Date 2021/6/22
 * @Version 1.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseModel extends PageModel implements Serializable {
//
//    @ApiModelProperty(value = "自增主键")
//    @TableId(value = "id", type = IdType.AUTO)
//    protected Integer id;

//    /**
//     * 创建人
//     */
//    @TableField(fill = FieldFill.INSERT)
//    @ApiModelProperty(hidden = true , value = "创建人")
//    protected String createBy;
//
//    /**
//     * 创建时间
//     */
//    @TableField(fill = FieldFill.INSERT)
//    @ApiModelProperty(hidden = true, value = "创建时间")
//    protected Date createTime;
//
//    /**
//     * 修改人
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    @ApiModelProperty(hidden = true, value = "修改人")
//    protected String updateBy;
//
//    /**
//     * 修改时间
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    @ApiModelProperty(hidden = true, value = "修改时间")
//    protected Date updateTime;
//
//    /**
//     * 状态 -1:已删除 0:未启用 1:已启用
//     */
////    @TableLogic//逻辑删除辨识
//    @ApiModelProperty(hidden = true, value = "状态：-1:已删除 0:未启用 1:已启用")
//    protected Integer status;

}
