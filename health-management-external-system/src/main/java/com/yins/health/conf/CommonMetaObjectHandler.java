package com.yins.health.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author houzhenghai
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 判断添加时有没有createTime这条属性
        if (metaObject.hasSetter("createdTime")) {
            this.setFieldValByName("createdTime", new Date(), metaObject);
        }
        if (metaObject.hasSetter("updatedTime"))
            this.setFieldValByName("updatedTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 判断修改时有没有updateTime这条属性
        if (metaObject.hasSetter("updatedTime")) {
            this.setFieldValByName("updatedTime", new Date(), metaObject);
        }
    }
}