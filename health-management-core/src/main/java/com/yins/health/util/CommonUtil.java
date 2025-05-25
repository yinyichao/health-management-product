package com.yins.health.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author hankun
 * @Description 类型转换
 * @Date 2021/6/23
 * @Version 1.0
 */
@Slf4j
public class CommonUtil {

    private CommonUtil() {
    }
    /**
     * 将pojo转vo
     * @param list po
     * @param modelClass vo
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<T> convert(List<K> list, Class<T> modelClass) {
        List<T> valueList = new ArrayList<>();
        if (CollectionUtil.isEmpty(list)) return valueList;

        for (K item : list) {
            T bean = null;
            if(item instanceof Map){
                bean=BeanUtil.mapToBean((Map<String,Object>) item,modelClass,true, CopyOptions.create());
            }else{
                bean = convert(item, modelClass);
            }

            valueList.add(bean);
        }

        return valueList;
    }

    /**
     * 将pojo转vo
     * @param set po
     * @param modelClass vo
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<T> convert(Set<K> set, Class<T> modelClass) {
        List<T> valueList = new ArrayList<>();
        if (CollectionUtil.isEmpty(set)) return valueList;

        for (K item : set) {
            T bean = null;
            if (item instanceof Map) {
                bean = BeanUtil.mapToBean((Map<String, Object>) item, modelClass, true, CopyOptions.create());
            } else {
                bean = convert(item, modelClass);
            }

            valueList.add(bean);
        }

        return valueList;
    }

    public static <T, K> T convert(K bean, Class<T> modelClass) {
        try {
            T model = modelClass.newInstance();
            BeanUtils.copyProperties(bean, model);
            return model;
        } catch (Exception e) {
            log.error("convert", e);
            return null;
        }
    }


    public static List convertMap(List list,Class obj){
        //生成集合
        ArrayList ary = new ArrayList();
        //遍历集合中的所有数据
        for(int i = 0; i<list.size(); i++){
            try {
                ////生成对象实历 将MAP中的所有参数封装到对象中
                Object o = CommonUtil.addProperty( (Map)list.get(i),obj.newInstance() );
                //把对象加入到集合中
                ary.add(o);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //返回封装好的集合
        return list;
    }

    public static Object addProperty(Map map,Object obj){
        //遍历所有名称
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            //取得名称
            String name = it.next().toString();
            //取得值
            String value = map.get(name).toString();

            try{
                //取得值的类形
                Class type = PropertyUtils.getPropertyType(obj, name);

                if(type!=null){
                    //设置参数
                    PropertyUtils.setProperty(obj, name, ConvertUtils.convert(value, type));

                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
        return obj;

    }

    /**
     * bean转map
     * @param o
     * @param ignoreNullValue 是否忽略null值
     * @param ignoreEmptyValue 是否忽略 " "，[] 等情况
     * @return
     */
    public static Map<String, Object> beanToMap(Object o, Boolean ignoreNullValue, Boolean ignoreEmptyValue){
        Map<String, Object> temp =  BeanUtil.beanToMap(o, false, ignoreNullValue);
        Map<String, Object> remove = new HashMap<>();

        //ignoreNullValue和ignoreEmptyValue要分开
        if(ignoreEmptyValue){
            for (String k : temp.keySet()) {
                Object v = temp.get(k);
                if(v instanceof String){
                    if(v !=null && StringUtils.isBlank((String) v)){
                        remove.put(k, v);
                    }
                }
                if(v instanceof Collection){
                    if(v !=null && CollectionUtil.isEmpty((Collection) v)){
                        remove.put(k, v);
                    }
                }
            }

            temp = temp.entrySet().stream().filter(i->!remove.containsKey(i.getKey())).collect(Collectors.toMap(i->i.getKey(), i->i.getValue()));
        }

        return temp;
    }

    /**
     * 返回严格有属性值的Map
     * （排除null，” “， [ ]等）
     * @param o
     * @return
     */
    public static Map<String, Object> toAvailableParame(Object o){
        return beanToMap(o, true, true);
    }

    /**
     * 返回严格有属性值的List
     * （排除null，” “， [ ]等）
     * @param source
     * @return
     */
    public static List<String> toAvailableList(List<String> source){
        if(CollectionUtil.isEmpty(source)){
            return Collections.emptyList();
        }
        return source.stream().filter(StringUtils::isNotBlank).map(StringUtils::trim).collect(Collectors.toList());
    }

    /**
     * 驼峰转下划线
     * @param field 驼峰字段
     * @return 下划线字段
     */
    public static String humpToUnderline(String field) {
        if (StringUtils.isBlank(field)) {
            return field;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static byte[] read(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    /**
     * 响应json数据给前端
     *
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) {

        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONUtil.toJsonStr(obj));
            response.flushBuffer();

        } catch (IOException e) {
            log.warn("响应json数据给前端异常:{}", e);
        }
    }

    /**
     * 根据文件名称获取文件后缀
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * 根据文件后缀，生成文件存储：年/月/日/uuid.suffix 格式
     */
    public static String getFilePath(String fileName) {
        String suffix = getFileSuffix(fileName);
        // 生成文件在存储桶中的唯一键
        return StrUtil.format("{}/{}/{}/{}.{}", DateUtil.thisYear(), DateUtil.thisMonth() + 1,DateUtil.thisDayOfMonth(), IdUtil.randomUUID(), suffix);
    }


    public static String getBaseFilePath(String fileName,Long accountId,String baseId) {
        String suffix = getFileSuffix(fileName);
        // 生成文件在存储桶中的唯一键
        return StrUtil.format("{}/{}/{}.{}", accountId, baseId, IdUtil.randomUUID(), suffix);
    }
}
