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
     * 提取swagger注解的字段，按position排序
     *
     * @param clazz Class<?>
     * @return Map<字段名, 字段的swagger描述>
     */
    public static Map<String, String> getSwaggerAnnotationData(Class<?> clazz) {
        List<Field> fieldList = getAllFields(clazz);
        //dataMap<ApiModelProperty，字段名>
        Map<ApiModelProperty, String> dataMap = new HashMap<>(fieldList.size());
        fieldList.forEach(filed -> {
            ApiModelProperty annotation = filed.getAnnotation(ApiModelProperty.class);
            if (Objects.isNull(annotation) || annotation.hidden() || StringUtils.isBlank(annotation.value())) {
                return;
            }
            dataMap.put(annotation, filed.getName());
        });
        return dataMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getKey().position()))
                .collect(Collectors.toMap(Map.Entry::getValue, c -> c.getKey().value(), (c1, c2) -> c1, LinkedHashMap::new));
    }

    /**
     * 获取类的所有属性
     *
     * @param clazz Class<?>
     * @return Field[]
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        while (Objects.nonNull(clazz) && clazz != Object.class) {
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            //得到父类，赋给自己
            clazz = clazz.getSuperclass();
        }
        return list;
    }
}
