package com.yins.health.util;

import cn.hutool.core.util.ObjectUtil;
import com.yins.health.constant.Common;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 百分比计算工具类
 */
public class PercentUtil {

    /**
     * 计算百分比
     * 含差值
     *
     * @param num
     * @param numSec
     * @return
     */
    public static String getPercent(int num, int numSec) {
        if (num == 0 || num <= 0 || numSec == 0 || numSec <= 0) {
            return "-";
        }
        double d = num * 1.0;
        double d2 = numSec * 1.0;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        // 设置保留几位小数，这里设置的是保留两位小数
        percentInstance.setMinimumFractionDigits(2);
        String percent = percentInstance.format(d / d2);
        return percent;
    }

    public static Double percent(Double num, Double numSec) {
        if (numSec == 0) {
            return 0.00D;
        }
        int decimalPlaces = 2; // 保留两位小数
        // 四舍五入到指定小数位数
        double roundedNum = Math.round((num/numSec) * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        return roundedNum;
    }

    /**
     * 占比 first / last
     */
    public static BigDecimal computeOcc(BigDecimal first, BigDecimal last) {
        if (checkNotNullAndZero(first)) {
            return BigDecimal.ZERO;
        }
        if (checkNotNullAndZero(last) || ObjectUtil.equals(first, last)) {
            return BigDecimal.ONE;
        }
        return first.divide(last, Common.NUMBER_FOUR, BigDecimal.ROUND_DOWN);
    }

    /**
     * 增速 (first - last) / last
     */
    public static BigDecimal computeRate(BigDecimal first, BigDecimal last) {
        if (checkNotNullAndZero(first)) {
            return BigDecimal.ZERO;
        }
        if (checkNotNullAndZero(last)) {
            return BigDecimal.ONE;
        }
        if (first.equals(last)) {
            return BigDecimal.ZERO;
        }
        return (first.subtract(last)).divide(last, Common.NUMBER_FOUR, BigDecimal.ROUND_DOWN);
    }

    private static boolean checkNotNullAndZero(BigDecimal bigDecimal) {
        return !(bigDecimal != null && ObjectUtil.notEqual(bigDecimal, BigDecimal.ZERO));
    }

}
