package com.yins.health.util;

import cn.hutool.core.stream.SimpleCollector;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 类型转换器
 *
 * @author ruoyi
 */
public class Convert extends cn.hutool.core.convert.Convert {

    static <R> Collector<R, ?, Page<R>> toPageCollector() {
        return new SimpleCollector<>((Supplier<Page<R>>) Page::new, Page::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collections.emptySet());
    }

    /**
     * 同名类型转换
     * T-->R，同时保留分页信息
     */
    public static <T, R> Page<R> toPage(List<T> sources, Class<R> elementType) {
        Page<R> targetPage = (Page<R>) toCollection(Page.class, elementType, sources);

        withPageInfo(new PageInfo<>(sources), targetPage);
        return targetPage;
    }

    /**
     * 自定义类型转换转换：手动传入的转换方法
     * T-->R，同时保留分页信息
     */
    public static <T, R> Page<R> toPage(List<T> sources, Function<T, R> covertor) {
        // Page<R> targetPage = sources.stream().map(covertor).collect((Supplier<Page<R>>) Page::new, Page::add, Page::addAll);
        Page<R> targetPage = sources.stream().map(covertor).collect(Convert.toPageCollector());

        withPageInfo(new PageInfo<>(sources), targetPage);
        return targetPage;
    }

    public static <T, R> Page<R> withPageInfo(PageInfo<T> pageInfo, Page<R> targetPage) {
        targetPage.setPageNum(pageInfo.getPageNum());
        targetPage.setPageSize(pageInfo.getPageSize());
        targetPage.setTotal(pageInfo.getTotal());
        return targetPage;
    }

    /**
     * 无需做转换，直接取pageInfo的page信息
     * 兼容需不需要分页的场景
     *
     * @return 如果toPage是true，返回的实际类是Page，否则返回正常的List
     */
    public static <T, R> List<R> toListNoConvert(PageInfo<T> pageInfo, List<R> target, Boolean toPage) {
        if (toPage) {
            Page<R> targetPage = target.stream().collect(Convert.toPageCollector());
            return withPageInfo(pageInfo, targetPage);
        }

        return target;
    }

    /**
     * 同名类型转换
     * 兼容需不需要分页的场景
     *
     * @return 如果toPage是true，返回的实际类是Page，否则返回正常的List
     */
    public static <T, R> List<R> toList(List<T> sources, Class<R> elementType, Boolean toPage) {
        if (toPage) {
            return toPage(sources, elementType);
        }
        return toList(elementType, sources);
    }

    /**
     * 自定义类型转换转换：手动传入的转换方法
     * 兼容需不需要分页的场景
     *
     * @return 如果toPage是true，返回的实际类是Page，否则返回正常的List
     */
    public static <T, R> List<R> toList(List<T> sources, Function<T, R> covertor, Boolean toPage) {
        if (toPage) {
            return toPage(sources, covertor);
        }
        return toList(sources, covertor);
    }


    /**
     * 转换list：通过手动传入的转换方法
     * 用stream.map也是一样的
     *
     * @return
     */
    public static <T, R> List<R> toList(List<T> sources, Function<T, R> covertor) {
        return sources.stream().filter(Objects::nonNull).map(covertor).collect(Collectors.toList());
    }

    /**
     * 转换实体：通过手动传入的转换方法
     *
     * @return
     */
    public static <T, R> R convert(Class<R> elementType, T source, Function<T, R> covertor) {
        R target = covertor.apply(source);
        return target;
    }

    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String utf8Str(Object obj) {
        return str(obj, StandardCharsets.UTF_8);
    }

    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
     *
     * @param obj     对象
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[] || obj instanceof Byte[]) {
            if (obj instanceof byte[]) {
                return str((byte[]) obj, charset);
            } else {
                Byte[] bytes = (Byte[]) obj;
                int length = bytes.length;
                byte[] dest = new byte[length];
                for (int i = 0; i < length; i++) {
                    dest[i] = bytes[i];
                }
                return str(dest, charset);
            }
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        }
        return obj.toString();
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }


    /**
     * 将编码的byteBuffer数据转换为字符串
     *
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     */
    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

}
