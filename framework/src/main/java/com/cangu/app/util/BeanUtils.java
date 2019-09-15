package com.cangu.app.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ZhengFeiFei on 2018/10/22.
 */
public class BeanUtils<R,T> extends org.springframework.beans.BeanUtils {

    public List<T> copyList(List<R> from, Class to) {
        List<T> list = Lists.newArrayList();
        try {
            for (R o : from) {
                T t = (T)to.newInstance();
                copyProperties(o, t);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }



}
