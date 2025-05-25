package com.yins.health.util;


import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @Auther: pxz
 * @Date: 2023/9/8 13:49
 * @Description: 计算相关工具类
 */
@Slf4j
public class MathUtils {

    /**
     * 各指标权重值
     */
    static final Map<String, Object> weight = ImmutableMap.of(
            "entNum", 0.1255,
            "regisCapNum", 0.1920,
            "totalRveNum", 0.1565,
            "patentNum", 0.1141,
            "insuredNum", 0.0718
    );
    /**
     * 百强企业指标权重值
     */
    static final double topWeight = 0.3401;
    /**
     *
     * 功能描述: 计算目标占比
     *
     * @param:
     * @return:
     * @auther: pxz
     * @date: 2023/9/6 17:01
     */
    public static String calculatePercentage(double numerator, double denominator) {
        if (denominator == 0) {
            return "0.00";
        }

        double percentage = (numerator / denominator);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(percentage);
    }
    /**
     *
     * 功能描述: 计算目标百分比占比
     *
     * @param:
     * @return:
     * @auther: pxz
     * @date: 2023/9/6 17:01
     */
    public static String formattedPercentage(double numerator, double denominator) {
        if (denominator == 0) {
            return "0.00";
        }

        double percentage = (numerator / denominator) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(percentage);
    }

    /**
     *
     * 功能描述: 获取平均值
     *
     * @param:
     * @return:
     * @auther: pxz
     * @date: 2023/10/9 15:05
     */
    public static Double getMean(List<Double> nums){
        if (CollectionUtil.isEmpty(nums)){
            return null;
        }
        double[] numds = nums.stream().mapToDouble(Double::doubleValue).toArray();
        Mean mean = new Mean();
        return mean.evaluate(numds);
    }

    /**
     *
     * 功能描述: 获取标准差
     *
     * @param:
     * @return:
     * @auther: pxz
     * @date: 2023/10/9 15:05
     */
    public static Double getStandard(List<Double> nums){
        if (CollectionUtil.isEmpty(nums)){
            return null;
        }
        double[] numds = nums.stream().mapToDouble(Double::doubleValue).toArray();

        StandardDeviation standardDeviation = new StandardDeviation();
        double stdevX = standardDeviation.evaluate(numds);
        return stdevX;
    }
    public static double getStandard(long count, double sum, double average) {
        double sumOfSquares = sum * sum / count;
        double standardDeviation = Math.sqrt(sumOfSquares - (average * average));
        return standardDeviation;
    }


}
