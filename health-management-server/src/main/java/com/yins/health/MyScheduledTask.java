package com.yins.health;

import com.yins.health.service.TbStatisticsItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
@Slf4j
public class MyScheduledTask {
    @Resource
    private TbStatisticsItemService tbStatisticsItemService;
    // 每天凌晨 2 点执行一次
    @Scheduled(cron = "0 0 22 * * ?")
    public void runAt22PM() {
        LocalDate now = LocalDate.now();
        System.out.println(now+"[每天凌晨22点执行任务:开始]");
        log.info(now+"[每天凌晨22点执行任务:开始]");
        tbStatisticsItemService.day_week();
        System.out.println(now+"[每天凌晨22点执行任务:结束]");
        log.info(now+"[每天凌晨22点执行任务:结束]");
    }

    // 每月1号凌晨0点执行
    @Scheduled(cron = "0 0 0 1 * ?")
    public void runOnFirstDayOfMonth() {
        LocalDate now = LocalDate.now();
        System.out.println(now+"[每月1号凌晨执行任务:开始]");
        log.info(now+"[每月1号凌晨执行任务:开始]");
        tbStatisticsItemService.month();
        System.out.println(now+"[每月1号凌晨执行任务:结束]");
        log.info(now+"[每月1号凌晨执行任务:结束]");
    }
}