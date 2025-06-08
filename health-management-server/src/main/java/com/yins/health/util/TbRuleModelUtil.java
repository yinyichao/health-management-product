package com.yins.health.util;

import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.TbStatisticsItem;
import com.yins.health.entity.TbTask;
import com.yins.health.entity.dto.RuleDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class TbRuleModelUtil {
    public static void getString(TbRule tbRule, Integer counts, RuleDto dto, List<TbRuleModel> list) {
        if(counts >= tbRule.getNum()){
            if(StringUtils.isEmpty(dto.getLabel())){
                dto.setLabel(tbRule.getRuleType());
                dto.setContent(tbRule.getContent());
            }else if("高风险".equals(tbRule.getRuleType())){
                dto.setLabel(tbRule.getRuleType());
                dto.setContent(tbRule.getContent());
            }else if("中风险".equals(tbRule.getRuleType()) && !"高风险".equals(dto.getLabel())){
                dto.setLabel(tbRule.getRuleType());
                dto.setContent(tbRule.getContent());
            }
            TbRuleModel tbRuleModel = new TbRuleModel();
            tbRuleModel.setRuleId(tbRule.getId());
            list.add(tbRuleModel);
        }
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
    public static <T> List<T> getDifference(List<T> list1, List<T> list2) {
        // 将list2转换成Set，优化contains操作的性能
        Set<T> set2 = new HashSet<>(list2);

        // 从list1中筛选出不在set2中的元素
        return list1.stream()
                .filter(item -> !set2.contains(item)) // 直接检查Set，性能更优
                .collect(Collectors.toList()); // 收集成新List
    }
    public static void getMonth(Integer month, TbStatisticsItem item,TbTask tbTask) {
        switch (month) {
            case 1:
                item.setAddTasks(tbTask.getOneAddTasks());
                item.setViewsTasks(tbTask.getOneViewsTasks());
                item.setQuestionnaireTasks(tbTask.getOneQuestionnaireTasks());
                break;
            case 2:
                item.setAddTasks(tbTask.getTwoAddTasks());
                item.setViewsTasks(tbTask.getTwoViewsTasks());
                item.setQuestionnaireTasks(tbTask.getTwoQuestionnaireTasks());
                break;
            case 3:
                item.setAddTasks(tbTask.getThreeAddTasks());
                item.setViewsTasks(tbTask.getThreeViewsTasks());
                item.setQuestionnaireTasks(tbTask.getThreeQuestionnaireTasks());
                break;
            case 4:
                item.setAddTasks(tbTask.getFourAddTasks());
                item.setViewsTasks(tbTask.getFourViewsTasks());
                item.setQuestionnaireTasks(tbTask.getFourQuestionnaireTasks());
                break;
            case 5:
                item.setAddTasks(tbTask.getFiveAddTasks());
                item.setViewsTasks(tbTask.getFiveViewsTasks());
                item.setQuestionnaireTasks(tbTask.getFiveQuestionnaireTasks());
                break;
            case 6:
                item.setAddTasks(tbTask.getSixAddTasks());
                item.setViewsTasks(tbTask.getSixViewsTasks());
                item.setQuestionnaireTasks(tbTask.getSixQuestionnaireTasks());
                break;
            case 7:
                item.setAddTasks(tbTask.getSevenAddTasks());
                item.setViewsTasks(tbTask.getSevenViewsTasks());
                item.setQuestionnaireTasks(tbTask.getSevenQuestionnaireTasks());
                break;
            case 8:
                item.setAddTasks(tbTask.getEightAddTasks());
                item.setViewsTasks(tbTask.getEightViewsTasks());
                item.setQuestionnaireTasks(tbTask.getEightQuestionnaireTasks());
                break;
            case 9:
                item.setAddTasks(tbTask.getNineAddTasks());
                item.setViewsTasks(tbTask.getNineViewsTasks());
                item.setQuestionnaireTasks(tbTask.getNineQuestionnaireTasks());
                break;
            case 10:
                item.setAddTasks(tbTask.getTenAddTasks());
                item.setViewsTasks(tbTask.getTenViewsTasks());
                item.setQuestionnaireTasks(tbTask.getTenQuestionnaireTasks());
                break;
            case 11:
                item.setAddTasks(tbTask.getElevenAddTasks());
                item.setViewsTasks(tbTask.getElevenViewsTasks());
                item.setQuestionnaireTasks(tbTask.getElevenQuestionnaireTasks());
                break;
            case 12:
                item.setAddTasks(tbTask.getTwelveAddTasks());
                item.setViewsTasks(tbTask.getTwelveViewsTasks());
                item.setQuestionnaireTasks(tbTask.getTwelveQuestionnaireTasks());
                break;
        }
    }
}
