package com.ax.utils;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: BeanCopyUtil
 * description: bean拷贝
 *
 * @author: axiang
 * date: 2023/4/9 12:08
 */
public class BeanCopyUtil {

    private BeanCopyUtil() {
    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            // 实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

    /*public static <V> List<V> copyBeanList(List<Object> list, Class<V> clazz) {
        List<V> result = new ArrayList<>();
        for (Object o : list) {
            V v = copyBean(o, clazz);
            result.add(v);
        }
        return result;
    }*/
}
