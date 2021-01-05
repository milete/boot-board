package com.demo.utils;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description：注解字段提取
 *
 * @author jiac
 * @date 2021/1/5 9:31
 */
public class AnnotationUtil {

    /**
     * 提取swagger注解的字段
     *
     * @param clazz Class<?>
     * @return Map<字段名, swagger描述>
     */
    public static Map<String, String> getSwaggerAnnotationData(Class<?> clazz) {
        List<Field> fieldList = getAllFields(clazz);
        Map<ApiModelProperty, Field> dataMap = new HashMap<>(fieldList.size());
        fieldList.forEach(c -> {
            ApiModelProperty annotation = c.getAnnotation(ApiModelProperty.class);
            if (annotation == null || annotation.hidden() || StringUtils.isBlank(annotation.value())) {
                return;
            }
            dataMap.put(annotation, c);
        });
        List<ApiModelProperty> titleList = dataMap.keySet()
                .stream()
                .sorted(Comparator.comparingInt(ApiModelProperty::position))
                .collect(Collectors.toList());
        Map<String, String> titleMap = new LinkedHashMap<>(titleList.size());
        titleList.forEach(c -> {
            titleMap.put(dataMap.get(c).getName(), c.value());
        });
        return titleMap;
    }

    /**
     * 获取类的所有属性
     *
     * @param clazz Class<?>
     * @return Field[]
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            //得到父类，赋给自己
            clazz = clazz.getSuperclass();
        }
        return list;
    }
}
