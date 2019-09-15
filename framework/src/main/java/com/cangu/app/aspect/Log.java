package com.cangu.app.aspect;

import java.lang.annotation.*;

/**
 * @author ZhengFeiFei on 2018/6/4.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /** 描述 */
    String description() default "";
    /** 日志类型（1增加2修改3删除4=查询） */
    int logType() default 4;
    /** 请求url */
    String url() default "";

}
