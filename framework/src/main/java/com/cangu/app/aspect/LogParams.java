package com.cangu.app.aspect;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhengFeiFei on 2018/6/4.
 */
@Getter
@Setter
public class LogParams {
    /** 描述 */
    private String description;
    /** 日志类型（1增加2修改3删除4=查询） */
    private Integer logType;
    /** 请求url */
    private String url;
}
