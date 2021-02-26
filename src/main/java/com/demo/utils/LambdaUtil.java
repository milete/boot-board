package com.demo.utils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * description：Lambda工具类
 *
 * @author jiac
 * @date 2021/2/26 16:49
 */
public class LambdaUtil {

    /**
     * 为forEach提供索引
     * demo：LambdaUtil.forEach(list, (index, item) -> {});
     *
     * @param elements 集合
     * @param action   BiConsumer<索引，元素>
     * @param <T>
     */
    public static <T> void forEach(Iterable<? extends T> elements, BiConsumer<Integer, ? super T> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        int index = 0;
        for (T t : elements) {
            action.accept(index++, t);
        }
    }

    /**
     * 按对象属性过滤重复数据，返回Predicate
     * demo：list.stream().filter(LambdaUtil.distinctByKey())
     *
     * @param keyExtractor Function
     * @param <T>
     * @return Predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> Objects.isNull(seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE));
    }
}
