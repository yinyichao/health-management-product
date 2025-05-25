package com.yins.health.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.io.Serializable;
import java.util.List;

public class ValueUtil {
    public ValueUtil() {
    }

    public static <T extends Serializable> List<T> copyFieldValues(List<? extends Serializable> sources, Class<T> clazz) {
        TypeReference<List<T>> typeReference = new TypeReference<List<T>>(clazz) {
        };
        return (List) JSON.parseObject(JSON.toJSONString(sources), typeReference);
    }

    public static <T> T copyFieldValue(Serializable source, Class<T> targetClazz) {
        return ValidationUtil.isEmpty(source) ? null : JSON.parseObject(JSON.toJSONString(source), targetClazz);
    }
}
