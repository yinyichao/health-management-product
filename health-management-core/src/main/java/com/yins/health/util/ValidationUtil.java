package com.yins.health.util;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName: ValidationUtil
 * @Author: liujinyan
 * @Date: 2024/9/18 10:44
 */
public class ValidationUtil {

    /**
     * Check whether the Object has value or not.
     *
     * @param aObj
     * @return if the obj is empty
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object aObj) {
        if (aObj instanceof String) {
            return isEmpty((String) aObj);
        } else if (aObj instanceof StringBuilder) {
            return isEmpty((StringBuilder) aObj);
        } else if (aObj instanceof Long) {
            return isEmpty((Long) aObj);
        } else if (aObj instanceof java.util.Date) {
            return isEmpty((java.util.Date) aObj);
        } else if (aObj instanceof Collection) {
            return isEmpty((Collection) aObj);
        } else if (aObj instanceof Map) {
            return isEmpty((Map) aObj);
        } else if (aObj != null && aObj.getClass().isArray()) {
            return isEmptyArray(aObj);
        } else {
            return isNull(aObj);
        }
    }

    /**
     * Check whether the String has value or not.
     *
     * @param aStr
     * @return if the string is empty
     */
    public static boolean isEmpty(String aStr) {
        if (aStr == null || aStr.trim().isEmpty() || aStr.trim().equals("undefined")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(StringBuilder sb) {
        if (sb == null || sb.length() == 0 || sb.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Check whether the Long has value or not.
     *
     * @param aLong
     * @return if the Long is null
     */
    public static boolean isEmpty(Long aLong) {
        if (aLong == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether a Collection object is empty.
     *
     * @param c: a java.util.Collection object
     * @return if the Map is empty
     */
    public static boolean isEmpty(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check whether a Map object is empty.
     *
     * @param m: a java.util.Map object
     * @return if the Map is empty
     */
    public static boolean isEmpty(Map<?, ?> m) {
        if (m == null || m.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check whether the Date has value or not.
     *
     * @param aDate
     * @return if the date is null
     */
    public static boolean isEmpty(java.util.Date aDate) {
        if (aDate == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Trim the specified String.
     *
     * @param aStr
     * @return the result string,"" return if string is NULL
     */
    public static String trim(String aStr) {
        if (aStr == null) {
            return "";
        } else {
            return aStr.trim();
        }
    }

    /**
     * Check whether the array is empty or not.
     *
     * @param array
     * @return
     */
    private static boolean isEmptyArray(Object array) {
        int length = 0;
        if (array instanceof int[]) {
            length = ((int[]) array).length;
        } else if (array instanceof byte[]) {
            length = ((byte[]) array).length;
        } else if (array instanceof short[]) {
            length = ((short[]) array).length;
        } else if (array instanceof char[]) {
            length = ((char[]) array).length;
        } else if (array instanceof float[]) {
            length = ((float[]) array).length;
        } else if (array instanceof double[]) {
            length = ((double[]) array).length;
        } else if (array instanceof long[]) {
            length = ((long[]) array).length;
        } else if (array instanceof boolean[]) {
            length = ((boolean[]) array).length;
        } else {
            length = ((Object[]) array).length;
        }
        if (length == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check whether the Object is null or not.
     *
     * @param oStr
     * @return if the object is NULL
     */
    public static boolean isNull(Object oStr) {
        if (oStr == null) {
            return true;
        } else {
            return false;
        }
    }

    //----------------- Add by Anderson Begin:---------------------

    /**
     * Validation method for time. The support time format is "hh:mm" or
     * "hh:mm:ss".
     *
     * @param text Input time value to be validated.
     * @return Return true if validation ok, otherwise return false.
     */

    /**
     * Validation method for Currency format.
     *
     * @param text Input currency value to be validated.
     * @return Return true if validation ok, otherwise return false.
     */
    public static boolean isCurrency(String text) {
        Pattern p = Pattern.compile("([+]|[-])?(\\s)*(\\d){1,}([.](\\d){1,2})?");
        return p.matcher(text).matches();
    }

    /**
     * Validation method for Number Format (decimal is also included)
     */
    public static boolean isNumber(String text) {
        if (isEmpty(text)) {
            return false;
        }
        // ----BUG ID 5473 ,Rocket.He, begin---
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        // ----BUG ID 5473 ,Rocket.He, end---
    }

    public static boolean isPercent(String text) {
        if (!isNumber(text)) {
            return false;
        }

        Pattern p = Pattern.compile("^(\\d{1,2})(\\.\\d{1,2})?$|^100$");
        return p.matcher(text.trim()).matches();
    }

    /**
     * Validation method for Number Format (decimal is excluded)
     *
     * @param text
     * @return Return true if validation ok, otherwise return false.
     */
    public static boolean isNumeric(String text) {
        if (isEmpty(text)) {
            return false;
        }

        Pattern p = Pattern.compile("[0-9]*");
        return p.matcher(text.trim()).matches();
    }

    /**
     * Validation method for date. It is a very strict date validator.
     *
     * @param text    Input date to be validated.
     * @param pattern Inputed date pattern.
     * @return Return true if validation ok, otherwise return false.
     */
    public static boolean isDate(String text, String pattern) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(false);
            formatter.parse(text);
        } catch (Exception e) {
            return false;
        }
        try {
            //date pattern: MM/dd/yyyy
            int mPos, dPos, yPos, mVal, dVal, yVal;
            String _pattern = pattern.toUpperCase();
            String separator = String.valueOf(getSeparator(_pattern));
            String[] _patternArr = _pattern.split(separator);
            mPos = getPosition(_patternArr, "[M]{1,}");
            dPos = getPosition(_patternArr, "[D]{1,}");
            yPos = getPosition(_patternArr, "[Y]{1,}");

            String[] dateVals = text.trim().split(separator);
            mVal = Integer.parseInt(dateVals[mPos]);
            dVal = Integer.parseInt(dateVals[dPos]);
            yVal = Integer.parseInt(dateVals[yPos]);

            if (mVal < 1 || mVal > 12) {
                return false;
            }
            if (dVal < 1 || dVal > 31) {
                return false;
            }
            if (yVal < 0 || yVal > 9999) {
                return false;
            }
            //FixBug 28507:
            //The input year's length should be the same as the year's format.
            if (dateVals[yPos].length() != _patternArr[yPos].length()) {
                return false;
            }
            int[] num_of_days_in_monthes = getNumberOfDaysInMonthes(yVal);
            if (dVal > num_of_days_in_monthes[mVal - 1]) {
                return false;
            }
        } catch (Exception eg) {
            return false;
        }
        return true;
    }

    private static int getPosition(String[] patternArr, String regex) {
        for (int i = 0; i < patternArr.length; i++) {
            if (Pattern.compile(regex).matcher(patternArr[i]).matches()) {
                return i;
            }
        }
        throw new IllegalStateException("Cannot get the position by the RegExp");
    }

    private static char getSeparator(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c < 65 || c > 90) {
                return c;
            }
        }
        throw new IllegalArgumentException("Inputted date pattern is fatal wrong.");
    }

    private static int[] getNumberOfDaysInMonthes(int theyear) {
        return new int[]{31, getLeapYear(theyear), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }

    private static int getLeapYear(int theyear) {
        return ((theyear % 4 == 0 && theyear % 100 != 0) || theyear % 400 == 0) ? 29 : 28;
    }
    //----------------- Add by Anderson End:---------------------

    /**
     * retrieve not null value.
     *
     * @param valueA
     * @param valueB
     * @return
     */
    public static String getNotNullValue(String valueA, String valueB) {
        if (isEmpty(valueA) && isEmpty(valueB)) {
            return "";
        } else if (isEmpty(valueA)) {
            return valueB;
        } else {
            return valueA;
        }
    }

    /**
     * make length of doc type value in 250
     *
     * @param docType
     * @return
     */
    public static String getValidDocType(String docType) {
        String herder = "application/";
        if (docType != null && docType.length() > 250) {
            //if doc type start with "application/", cut it first
            if (docType.startsWith(herder)) {
                docType = docType.substring(herder.length());
            }

            if (docType.length() > 250) {
                docType = docType.substring(0, 250);
            }
        }
        return docType;
    }

    public static boolean equals(String str1, String str2) {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }

    /**
     * Check if object1 equals object2.
     *
     * @param obj1 object1
     * @param obj2 object2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        boolean result;
        if (obj1 != null) {
            result = obj1.equals(obj2);
        } else {
            result = (obj2 == null);
        }
        return result;
    }

    /**
     * Check if trim(str1) equals trim(str2). Null is parsed to ""(empty string).
     *
     * @param str1 str1
     * @param str2 str2
     * @return
     */
    public static boolean equalsWithTrim(String str1, String str2) {
        return ValidationUtil.trim(str1).equals(ValidationUtil.trim(str2));
    }

    /**
     * Check if trim(str1) equalsIgnoreCase trim(str2). Null is parsed to ""(empty string).
     *
     * @param str1 str1
     * @param str2 str2
     * @return
     */
    public static boolean equalsIgnoreCaseWithTrim(String str1, String str2) {
        return ValidationUtil.trim(str1).equalsIgnoreCase(ValidationUtil.trim(str2));
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
    }

    public static boolean equals(Long L1, Long L2) {
        return L1 != null ? L1.equals(L2) : L2 == null;
    }



    /**
     * this method is moved from ValidatorUtil
     *
     * @param inputObject
     * @return
     * @author rocket.he
     */
    public static String validateString(Object inputObject) {
        if (inputObject == null) {
            return "";
        } else {
            if (inputObject instanceof String) {
                StringBuilder buf = new StringBuilder();
                for (int i = 0, len = inputObject.toString().length(); i < len; i++) {
                    char c = inputObject.toString().charAt(i);

                    if (c == '\'') {
                        buf.append('\\').append("'");
                    } else if (c == '\r') {
                        buf.append('\\').append("r");
                    } else if (c == '\n') {
                        buf.append('\\').append("n");
                    } else if (c == '\t') {
                        buf.append('\\').append("t");
                    } else if (c == '\\') {
                        buf.append('\\').append("\\");
                    } else if (c == '"') {
                        buf.append('\\').append("\"");
                    } else {
                        buf.append(c);
                    }
                }
                return buf.toString();
            } else {
                return inputObject.toString();
            }
        }
    }

    /**
     * Comparison Operator Array
     */
    static String[] operators = new String[]{"<", ">", "=", "<=", ">=", "!=", "<>"};

    /**
     * is Comparison Operator
     *
     * @param str String
     * @return
     */
    public static boolean isComparisonOperator(String string) {
        List<String> opList = Arrays.asList(operators);
        if (string != null) {
            return opList.contains(string.trim());
        }
        return false;
    }

    /**
     * 所有参数不为空
     *
     * @param values
     * @return
     */
    public static boolean allNotEmpty(Object... values) {
        boolean flag = true;
        if (values == null || values.length == 0) {
            flag = false;
        } else {
            for (Object value : values) {
                flag &= !isEmpty(value);
                if (flag == false) {
                    return flag;
                }
            }
        }
        return flag;
    }

    /**
     * 所有参数为空
     *
     * @param values
     * @return
     */
    public static boolean allEmpty(Object... values) {
        boolean flag = true;
        if (values == null || values.length == 0) {
            flag = true;
        } else {
            for (Object value : values) {
                flag &= isEmpty(value);
                if (flag == false) {
                    return flag;
                }
            }
        }
        return flag;
    }

    /**
     * 任意一个为空 返回 false,否则 返回 true
     *
     * @param values
     * @return
     */
    public static boolean everyEmpty(Object... values) {
        boolean flag = true;
        if (values == null || values.length == 0) {
            flag = true;
        } else {
            for (Object value : values) {
                flag = isEmpty(value);
                if (flag) {
                    return flag;
                }
            }
        }
        return flag;
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }
}
