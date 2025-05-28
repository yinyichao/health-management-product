package com.yins.health.util;

import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class TbRuleModelUtil {
    public static String getString(TbRule tbRule, Integer counts, String ruleType, List<TbRuleModel> list) {
        if(counts >= tbRule.getNum()){
            if("".equals(ruleType)){
                ruleType = tbRule.getRuleType();
            }else if("高风险".equals(tbRule.getRuleType())){
                ruleType = tbRule.getRuleType();
            }else if("中风险".equals(tbRule.getRuleType()) && !"高风险".equals(ruleType)){
                ruleType = tbRule.getRuleType();
            }
            TbRuleModel tbRuleModel = new TbRuleModel();
            tbRuleModel.setRuleId(tbRule.getId());
            list.add(tbRuleModel);
        }
        return ruleType;
    }

    public static String month() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalTime time = LocalTime.of(0,0,0);
        LocalDateTime localDateTime = LocalDateTime.of(firstDayOfMonth, time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter); // 输出如"2025-05-21 14:30:00"
    }

    public static String week() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalTime time = LocalTime.of(0,0,0);
        LocalDateTime localDateTime = LocalDateTime.of(firstDayOfMonth, time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter); // 输出如"2025-05-21 14:30:00"
    }
    public static String day() {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.of(0,0,0);
        LocalDateTime localDateTime = LocalDateTime.of(today, time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter); // 输出如"2025-05-21 14:30:00"
    }
    public static String hours(int hour) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 减去小时
        LocalDateTime ago = now.minusHours(hour);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ago.format(formatter); // 输出如"2025-05-21 14:30:00"
    }
}
