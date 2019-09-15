package com.cangu.app.util;

import org.apache.commons.lang3.StringUtils;


/**
 * @author ZhengFeiFei on 2018/12/5.
 * mybaties 扩展方法
 */
public abstract class ExpandUtil {

    public static boolean validStr(String o) {
        return StringUtils.isNotBlank(o);
    }

    public static boolean validInt(Object o) {
        boolean b = false;
        if (null == o) {
            return b;
        }
        try {
            int i = Integer.valueOf(o + "");
            if (i == 0) {
                b = false;
            } else {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }finally {
            return b;
        }
    }

}
