package com.yins.health.entity.vo;

import com.yins.health.entity.TbTask;
import lombok.Data;

import java.util.List;

@Data
public class TbTaskVo extends TbTask {
    private List<Integer> userIds;
}
