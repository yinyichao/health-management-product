package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbStatisticsItemDao;
import com.yins.health.dao.TbTaskDao;
import com.yins.health.entity.*;
import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.vo.TbStatisticsItemMonthVo;
import com.yins.health.entity.vo.TbStatisticsItemMonthVoOld;
import com.yins.health.entity.vo.TbStatisticsItemVo;
import com.yins.health.entity.vo.TbStatisticsItemYearVo;
import com.yins.health.service.*;
import com.yins.health.util.CommonUtil;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (TbStatisticsItem)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
@Service("tbStatisticsItemService")
public class TbStatisticsItemServiceImpl extends ServiceImpl<TbStatisticsItemDao, TbStatisticsItem> implements TbStatisticsItemService {
    @Autowired
    private TbQuestionnaireItemService tbQuestionnaireItemService;
    @Autowired
    private TbViewService tbViewService;
    @Autowired
    private TbAddService tbAddService;
    @Autowired
    private TbStatisticsService tbStatisticsService;
    @Autowired
    private TbTaskDao tbTaskDao;

    @Override
    public void month() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        // 如果想要以 "YYYY-MM" 的格式输出，可以使用格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = currentDate.format(formatter);
        List<TbStatistics> list = tbStatisticsService.list(new LambdaQueryWrapper<TbStatistics>()
                .eq(TbStatistics::getYear, currentYear).eq(TbStatistics::getDel,0));
        TbStatisticsItem item;
        List<TbStatisticsItem> saveList = new ArrayList<>();
        int counts = 0;
        for (TbStatistics tbStatistics : list) {
            TbTask tbTask = tbTaskDao.selectById(tbStatistics.getTaskId());
            item = new TbStatisticsItem();
            item.setUserId(tbStatistics.getUserId());
            item.setUserName(tbStatistics.getUserName());
            //0、日；1、周；2、月
            item.setType(2);
            item.setCycle(formattedDate);
            TbRuleModelUtil.getMonth(currentMonth,item,tbTask);
            counts = tbQuestionnaireItemService.count(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                    .eq(TbQuestionnaireItem::getCreatedUser,tbStatistics.getUserId()).ge(TbQuestionnaireItem::getUpdatedTime, TbRuleModelUtil.month()));
            item.setQuestionnaireWorks(counts);
            counts = tbAddService.count(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0).eq(TbAdd::getState, "有效")
                    .eq(TbAdd::getCreatedUser,tbStatistics.getUserId()).ge(TbAdd::getUpdatedTime, TbRuleModelUtil.month()));
            item.setAddWorks(counts);
            counts = tbViewService.count(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                    .and(wrapper -> wrapper
                            .eq(TbView::getCreatedUser, tbStatistics.getUserId())
                            .or()
                            .eq(TbView::getVisitorId, tbStatistics.getUserId())
                    )
                    .ge(TbView::getUpdatedTime, TbRuleModelUtil.month()));
            item.setViewsWorks(counts);
            item.setYear(currentYear);
            item.setStatisticsId(tbStatistics.getId());
            saveList.add(item);
        }
        this.saveBatch(saveList);
    }

    @Override
    public void day_week() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        // 如果想要以 "YYYY-MM" 的格式输出，可以使用格式化器
        // 获取当前年份
        LocalDate firstDayOfYear = LocalDate.now().withDayOfYear(1);

        LocalDate endDayOfYear = currentDate.withMonth(12).withDayOfMonth(31);
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 转换为字符串
        String beginTime = firstDayOfYear.format(formatter);
        String endTime = endDayOfYear.format(formatter);

        List<TbStatistics> list = tbStatisticsService.list(new LambdaQueryWrapper<TbStatistics>()
                .eq(TbStatistics::getYear, currentYear).eq(TbStatistics::getDel,0));
        TbStatisticsItem item;
        List<TbStatisticsItem> saveList = new ArrayList<>();
        Map<String,TbStatisticsItem> maps;
        for (TbStatistics tbStatistics : list) {
            TbTask tbTask = tbTaskDao.selectById(tbStatistics.getTaskId());
            List<TbStatisticsItemVDto> itemList1 = tbQuestionnaireItemService.findTbStatisticsItemVDto(tbStatistics.getUserId(),beginTime,endTime);
            List<TbStatisticsItemVDto> itemList2 = tbViewService.findTbStatisticsItemVDto(tbStatistics.getUserId(),beginTime,endTime);
            List<TbStatisticsItemVDto> itemList3 = tbAddService.findTbStatisticsItemVDto(tbStatistics.getUserId(),beginTime,endTime);
            maps = new HashMap<>();
            extracted(itemList1, maps, itemList2, itemList3);
            // 按键升序排序
            Map<String, TbStatisticsItem> sortedMap = maps.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())  // 默认按键升序排序
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,  // 处理重复键（不会发生，因为键是唯一的）
                            LinkedHashMap::new // 保持插入顺序
                    ));
            int weekAddWorks = 0;
            int weekViewWorks = 0;
            int weekQuestionnaireWorks = 0;
            LocalDate beginWeek = null;
            LocalDate endWeek = null;
            String beginWeekStr = "";
            // 使用迭代器
            Iterator<Map.Entry<String, TbStatisticsItem>> iterator = sortedMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, TbStatisticsItem> entry = iterator.next();
                String key = entry.getKey();
                item = entry.getValue();
                day(currentYear,tbStatistics, key, item, tbTask, saveList);
                // 将字符串转换为 LocalDate
                LocalDate givenDate = LocalDate.parse(key, formatter);
                if(beginWeek == null){
                    // 获取本周的周一
                    beginWeek = givenDate.with(DayOfWeek.MONDAY);
                    // 获取本周的周日
                    endWeek = givenDate.with(DayOfWeek.SUNDAY);
                    if(beginWeek.getYear()<givenDate.getYear()){
                        beginWeek = givenDate.withDayOfYear(1);
                    }
                    if(endWeek.getYear()>givenDate.getYear()){
                        endWeek = givenDate.withMonth(12).withDayOfMonth(31);
                    }
                }
                boolean isInRange = (givenDate.isAfter(beginWeek) || givenDate.isEqual(beginWeek)) && (givenDate.isBefore(endWeek) || givenDate.isEqual(endWeek));
                item = entry.getValue();
                if (isInRange) {
                    weekAddWorks += item.getAddWorks() == null ? 0 : item.getAddWorks();
                    weekViewWorks += item.getViewsWorks() == null ? 0 : item.getViewsWorks();
                    weekQuestionnaireWorks += item.getQuestionnaireWorks() == null ? 0 : item.getQuestionnaireWorks();
                    beginWeekStr = beginWeek +"_"+ endWeek;
                }else{
                    TbStatisticsItem itemWeek = new TbStatisticsItem();
                    TbStatisticsItem oldItemWeek = baseMapper.selectOne(new LambdaQueryWrapper<TbStatisticsItem>().eq(TbStatisticsItem::getUserId, tbStatistics.getUserId())
                            .eq(TbStatisticsItem::getCycle, beginWeekStr).eq(TbStatisticsItem::getType,1));
                    if(oldItemWeek!=null){
                        itemWeek.setId(oldItemWeek.getId());
                    }else{
                        itemWeek.setUserId(tbStatistics.getUserId());
                        itemWeek.setUserName(tbStatistics.getUserName());
                        itemWeek.setType(1);
                        itemWeek.setCycle(beginWeekStr);
                    }
                    itemWeek.setYear(currentYear);
                    itemWeek.setQuestionnaireTasks(tbTask.getWeekQuestionnaireTasks());
                    itemWeek.setAddTasks(tbTask.getWeekAddTasks());
                    itemWeek.setViewsTasks(tbTask.getWeekViewsTasks());
                    itemWeek.setAddWorks(weekAddWorks);
                    itemWeek.setViewsWorks(weekViewWorks);
                    itemWeek.setQuestionnaireWorks(weekQuestionnaireWorks);
                    itemWeek.setStatisticsId(tbStatistics.getId());
                    saveList.add(itemWeek);
                    // 获取本周的周一
                    beginWeek = givenDate.with(DayOfWeek.MONDAY);
                    // 获取本周的周日
                    endWeek = givenDate.with(DayOfWeek.SUNDAY);
                    if(beginWeek.getYear()<givenDate.getYear()){
                        beginWeek = givenDate.withDayOfYear(1);
                    }
                    if(endWeek.getYear()>givenDate.getYear()){
                        endWeek = givenDate.withMonth(12).withDayOfMonth(31);
                    }
                    weekAddWorks = item.getAddWorks() == null ? 0 : item.getAddWorks();
                    weekViewWorks = item.getViewsWorks() == null ? 0 : item.getViewsWorks();
                    weekQuestionnaireWorks = item.getQuestionnaireWorks() == null ? 0 : item.getQuestionnaireWorks();
                    beginWeekStr = beginWeek +"_"+ endWeek;
                }

                // 判断是否是最后一次循环
                if (!iterator.hasNext()) {
                    if (isInRange){
                        TbStatisticsItem itemWeek = new TbStatisticsItem();
                        TbStatisticsItem oldItemWeek = baseMapper.selectOne(new LambdaQueryWrapper<TbStatisticsItem>().eq(TbStatisticsItem::getUserId, tbStatistics.getUserId())
                                .eq(TbStatisticsItem::getCycle, beginWeekStr).eq(TbStatisticsItem::getType,1));
                        if(oldItemWeek!=null){
                            itemWeek.setId(oldItemWeek.getId());
                        }else{
                            itemWeek.setUserId(tbStatistics.getUserId());
                            itemWeek.setUserName(tbStatistics.getUserName());
                            itemWeek.setType(1);
                            itemWeek.setCycle(beginWeekStr);
                        }
                        itemWeek.setYear(currentYear);
                        itemWeek.setQuestionnaireTasks(tbTask.getWeekQuestionnaireTasks());
                        itemWeek.setAddTasks(tbTask.getWeekAddTasks());
                        itemWeek.setViewsTasks(tbTask.getWeekViewsTasks());
                        itemWeek.setAddWorks(weekAddWorks);
                        itemWeek.setViewsWorks(weekViewWorks);
                        itemWeek.setQuestionnaireWorks(weekQuestionnaireWorks);
                        itemWeek.setStatisticsId(tbStatistics.getId());
                        saveList.add(itemWeek);
                    }
                }
            }
        }
        this.saveOrUpdateBatch(saveList);
    }

    private void day(int currentYear,TbStatistics tbStatistics, String key, TbStatisticsItem item, TbTask tbTask, List<TbStatisticsItem> saveList) {
        TbStatisticsItem oldItem = baseMapper.selectOne(new LambdaQueryWrapper<TbStatisticsItem>().eq(TbStatisticsItem::getUserId, tbStatistics.getUserId())
                .eq(TbStatisticsItem::getCycle, key).eq(TbStatisticsItem::getType,0));
        if(oldItem!=null){
            item.setId(oldItem.getId());
        }else{
            item.setUserId(tbStatistics.getUserId());
            item.setUserName(tbStatistics.getUserName());
            item.setType(0);
            item.setCycle(key);
        }
        item.setStatisticsId(tbStatistics.getId());
        item.setYear(currentYear);
        item.setQuestionnaireTasks(tbTask.getDayQuestionnaireTasks());
        item.setAddTasks(tbTask.getDayAddTasks());
        item.setViewsTasks(tbTask.getDayViewsTasks());
        saveList.add(item);
    }

    private static void extracted(List<TbStatisticsItemVDto> itemList1, Map<String, TbStatisticsItem> maps, List<TbStatisticsItemVDto> itemList2, List<TbStatisticsItemVDto> itemList3) {
        if(itemList1 != null && !itemList1.isEmpty()){
            for (TbStatisticsItemVDto item1 : itemList1) {
                // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                maps.compute(item1.getTime(), (key, value) -> {
                    // 如果 key 存在，更新其值；如果不存在，创建新值
                    if (value == null) {
                        value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                    }
                    // 更新现有的 value 对象
                    value.setQuestionnaireWorks(item1.getWorks());
                    return value; // 返回更新后的 value 对象
                });
                for (TbStatisticsItemVDto item2 : itemList2) {
                    // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                    maps.compute(item2.getTime(), (key, value) -> {
                        // 如果 key 存在，更新其值；如果不存在，创建新值
                        if (value == null) {
                            value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                        }
                        // 更新现有的 value 对象
                        value.setViewsWorks(item2.getWorks());
                        return value; // 返回更新后的 value 对象
                    });
                    for (TbStatisticsItemVDto item3 : itemList3) {
                        // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                        maps.compute(item3.getTime(), (key, value) -> {
                            // 如果 key 存在，更新其值；如果不存在，创建新值
                            if (value == null) {
                                value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                            }
                            // 更新现有的 value 对象
                            value.setAddWorks(item3.getWorks());
                            return value; // 返回更新后的 value 对象
                        });
                    }
                }
            }
        }else if(itemList2 != null && !itemList2.isEmpty()){
            for (TbStatisticsItemVDto item2 : itemList2) {
                // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                maps.compute(item2.getTime(), (key, value) -> {
                    // 如果 key 存在，更新其值；如果不存在，创建新值
                    if (value == null) {
                        value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                    }
                    // 更新现有的 value 对象
                    value.setViewsWorks(item2.getWorks());
                    return value; // 返回更新后的 value 对象
                });
                for (TbStatisticsItemVDto item3 : itemList3) {
                    // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                    maps.compute(item3.getTime(), (key, value) -> {
                        // 如果 key 存在，更新其值；如果不存在，创建新值
                        if (value == null) {
                            value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                        }
                        // 更新现有的 value 对象
                        value.setAddWorks(item3.getWorks());
                        return value; // 返回更新后的 value 对象
                    });
                }
            }
        }else{
            for (TbStatisticsItemVDto item3 : itemList3) {
                // 使用 compute 方法，检查 map 中是否存在对应的 item1.getTime()
                maps.compute(item3.getTime(), (key, value) -> {
                    // 如果 key 存在，更新其值；如果不存在，创建新值
                    if (value == null) {
                        value = new TbStatisticsItem(); // 如果该时间不存在，初始化一个新的 TbStatisticsItem
                    }
                    // 更新现有的 value 对象
                    value.setAddWorks(item3.getWorks());
                    return value; // 返回更新后的 value 对象
                });
            }
        }
    }

    @Override
    public List<TbStatisticsItemMonthVo> selectMonthAll(TbStatisticsItemDto tbStatisticsItemDto) {
        /*List<TbStatisticsItem> tbStatisticsItems = baseMapper.selectList(new LambdaQueryWrapper<TbStatisticsItem>()
                .eq(StringUtils.isNotEmpty(tbStatisticsItemDto.getUserName()), TbStatisticsItem::getUserName, tbStatisticsItemDto.getUserName())
                .eq(StringUtils.isNotEmpty(tbStatisticsItemDto.getYear()), TbStatisticsItem::getYear, tbStatisticsItemDto.getYear())
                .eq(TbStatisticsItem::getType,2)
                //.ge(StringUtils.isNotEmpty(tbStatisticsItemDto.getBeginTime()), TbStatisticsItem::getCycle, tbStatisticsItemDto.getBeginTime())
                //.le(StringUtils.isNotEmpty(tbStatisticsItemDto.getEndTime()), TbStatisticsItem::getCycle, tbStatisticsItemDto.getEndTime())
                .orderByAsc(TbStatisticsItem::getUserId,TbStatisticsItem::getCycle));*/
        List<TbStatisticsItemVo> tbStatisticsItems = baseMapper.selectMonthAll(tbStatisticsItemDto);

        List<TbStatisticsItemMonthVoOld> list = CommonUtil.convert(tbStatisticsItems, TbStatisticsItemMonthVoOld.class);
        return change(list,tbStatisticsItemDto.getType());
    }
    public List<TbStatisticsItemMonthVo> change(List<TbStatisticsItemMonthVoOld> list,Integer type) {
        TbStatisticsItemMonthVo vo;
        Map<String,TbStatisticsItemMonthVo> map = new LinkedHashMap<String,TbStatisticsItemMonthVo>();
        for (TbStatisticsItemMonthVoOld item : list) {
            map.putIfAbsent(item.getDeptName(), new TbStatisticsItemMonthVo().setUserName(item.getDeptName()));
            if(map.containsKey(item.getDeptName()+item.getUserName())){
                vo = map.get(item.getDeptName()+item.getUserName());
            }else {
                TbStatisticsItemMonthVo tbStatisticsItemMonthVo = new TbStatisticsItemMonthVo();
                TbTask tbTask = tbTaskDao.selectById(item.getTaskId());
                TbStatisticsItemMonthVo.change(tbStatisticsItemMonthVo,tbTask,type);
                vo = tbStatisticsItemMonthVo;
            }
            //vo = map.getOrDefault(item.getDeptName()+item.getUserName(), new TbStatisticsItemMonthVo());
            vo.setUserName(item.getUserName());
            item.setViewsWorksRate();
            item.setAddWorksRate();
            item.setQuestionnaireWorksRate();
            TbStatisticsItemMonthVoOld.month(vo,item,type);
            map.put(item.getDeptName()+item.getUserName(), vo);
        }
        return new ArrayList<>(map.values());
    }
    @Override
    public List<TbStatisticsItemYearVo> selectYearAll(TbStatisticsItemDto tbStatisticsItemDto) {
        List<TbStatisticsItemYearVo> list = baseMapper.selectYearAll(tbStatisticsItemDto);
        list = TbStatisticsItemYearVo.change(list);
        return list;
    }

}

