package com.yins.health.util;

public class NumberToChineseConverter {

    private static final String[] CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNITS = {"", "十", "百", "千"};
    private static final String[] MIDDLE_UNITS = {"零亿", "零万", "零千", "零百", "零十"};
    private static final char ZERO = '零';

    public static String integerToChinese(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }

        if (number == 0) {
            return CHINESE_NUMBERS[0];
        }

        StringBuilder chineseNum = new StringBuilder();
        int unitIndex = 0;

        while (number > 0) {
            int currentDigit = number % 10;
            chineseNum.insert(0, CHINESE_NUMBERS[currentDigit] + UNITS[unitIndex]);
            number /= 10;
            unitIndex++;
            // Skip '万' and '亿' for simplicity
            if (unitIndex % 4 == 0 && unitIndex != 0) {
                chineseNum.insert(0, "万");
                unitIndex = 0;
                if (number > 9999) {
                    chineseNum.insert(0, "亿");
                }
            }
        }

        System.out.println("1:"+chineseNum);
        // 移除结尾的零
        if (chineseNum.length() > 1 && chineseNum.charAt(chineseNum.length() - 1) == '零') {
            chineseNum.deleteCharAt(chineseNum.length() - 1);
        }
        System.out.println("2:"+chineseNum);
        //移除首位万
        if (chineseNum.charAt(0) == '万') {
            chineseNum.deleteCharAt(0);
        }
        System.out.println(chineseNum);
        // 特殊处理 "一百" 和 "一千"
        while (chineseNum.length() > 2 && chineseNum.charAt(chineseNum.length() - 2) == '零' &&
                (chineseNum.charAt(chineseNum.length() - 1) == '十'
                        || chineseNum.charAt(chineseNum.length() - 1) == '百'
                        || chineseNum.charAt(chineseNum.length() - 1) == '千')) {
            chineseNum.delete(chineseNum.length() - 2, chineseNum.length());
        }
        System.out.println("3:"+chineseNum);
        String returnStr = chineseNum.toString();
        //处理中间零+单位
        int num = 0;
        for (String unit : MIDDLE_UNITS) {
            if (returnStr.contains(unit)) {
                if (num == 0) {
                    returnStr = returnStr.replace(unit, unit.charAt(0) + "");
                } else {
                    returnStr = returnStr.replace(unit, "零");
                }
                num++;
            }
        }
        System.out.println("4:"+chineseNum);
        //return returnStr;

        StringBuilder result = new StringBuilder();
        // 合并连续多个零
        char prevChar = returnStr.charAt(0);
        result.append(prevChar);
        for (int i = 1; i < returnStr.length(); i++) {
            char currentChar = returnStr.charAt(i);
            if (!(currentChar == prevChar && currentChar == ZERO)) {
                result.append(currentChar);
                prevChar = currentChar;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(integerToChinese(34567)); // 输出: 壹亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖
    }


}
