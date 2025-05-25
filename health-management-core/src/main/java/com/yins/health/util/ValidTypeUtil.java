package com.yins.health.util;


import com.yins.health.common.CustomServiceException;
import com.yins.health.common.ErrorMessageConstant;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @className: ValidTypeUtil
 * @description: 校验参数分组类
 * @author: wy
 * @date: 2022年09月02日 0002
 **/
public class ValidTypeUtil {

    public interface ValidInsert{};
    public interface ValidDelete{};
    public interface ValidUpdate{};
    public interface ValidQuery{};

    public interface ValidDeleteById{};
    public interface ValidUpdateById{};
    public interface ValidQueryByMainTableId{};
    public interface ValidQueryByRelationTableId{};

    public interface ValidAdd{};
    public interface ValidEditById{};
    public interface ValidQueryA{};
    public interface ValidQueryB{};
    public interface ValidQueryC{};
    public interface ValidInsertOrUpdate{};
    public interface ValidQueryByUniqueKey{};

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验所有参数
     * @param t
     * @param var2
     * @param <T>
     */
    public static <T> void validated(T t,Class<?>... var2){
        Set<ConstraintViolation<T>> validate = validator.validate(t, var2);
        if (validate.size() > 0) {
            List<String> errorMessages = new ArrayList<>();
            Iterator<ConstraintViolation<T>> iterator = validate.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> violation = iterator.next();
                violation.getMessage();
                errorMessages.add(violation.getMessage());
            }
            String error = StringUtils.join(errorMessages, "\n");
            throw new CustomServiceException(ErrorMessageConstant.ERROR_PARAMETER,error);
        }
    }

    /**
     *校验所有参数
     * @param list
     * @param var2
     * @param <T>
     */
    public static <T> void validated(List<T> list,Class<?>... var2){
        for (T t : list) {
            validated(t,var2);
        }
    }

}
