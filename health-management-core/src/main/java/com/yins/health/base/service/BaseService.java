package com.yins.health.base.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;


/**
 * BaseService默认都是关联一个表的
 * @param <M>
 * @param <T>
 */
@Slf4j
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements FastPageInterface {

    /**
     * 返回当前行对象的主键值
     * @param entity（当前行对象）
     * @return entity.getId之类的，不是DDL
     */
    public Object returnPrimaryValue(T entity){
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
        return idVal;
    }

    /**
     * 根据id更新，并返回最新的完整实体
     * @param entity
     * @return
     */
    public T updateByIdAndReturn(T entity) {
        Object idVal = returnPrimaryValue(entity);
        if( StringUtils.checkValNull(idVal)|| Objects.isNull(getById((Serializable) idVal))){
            throw new RuntimeException("update主键不能为空");
        }

        updateById(entity);
        return getById((Serializable) idVal);
    }

}
