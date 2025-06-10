package com.yins.health.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AgentConfigParam {
    private String corpid;
    private String nonceStr;
    private String timestamp;
    private String signature;
}
