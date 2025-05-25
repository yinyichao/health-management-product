package com.yins.health.base.mvc;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 实现这个接口可以让VO不序列化固定字段
 * 排除：createTime, updateTime，delFlag
 * @author wvp
 * @date 2023/6/16 15:57
 */
public interface IgnoreCommSerializable {

    @JsonIgnore
    @JSONField(serialize = false)
    Integer getDelFlag();

    @JsonIgnore
    @JSONField(serialize = false)
    void setDelFlag(Integer delFlag);

    @JsonIgnore
    @JSONField(serialize = false)
    Date getCreateTime();

    @JsonIgnore
    @JSONField(serialize = false)
    void setCreateTime(Date createTime);

    @JsonIgnore
    @JSONField(serialize = false)
    Date getUpdateTime();

    @JsonIgnore
    @JSONField(serialize = false)
    void setUpdateTime(Date updateTime);
}
