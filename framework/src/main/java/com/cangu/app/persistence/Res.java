package com.cangu.app.persistence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 返回结果
 * @author: ZhengFeiFei
 * @create: 2019-06-25 22:14
 */
@ApiModel(value = "Res")
@ToString
public class Res<T> implements Serializable {
    private static final long serialVersionUID = 4554254256817878472L;
    private final int okCode = 0;
    private final int errCode = 500;
    private final String okMsg = "success";
    private final String errorMsg = "未知异常，请联系系统管理员！";
    @Getter
    @ApiModelProperty(value = "code(0=成功，其他=失败)")
    private int code;
    @Getter
    @ApiModelProperty(value = "msg")
    private String msg;
    @Getter
    @ApiModelProperty(value = "content")
    private T content;

    public static Res ok(Object content) {
        return new Res().okRes(content);
    }

    private Res okRes(T content) {
        this.code = okCode;
        this.msg = okMsg;
        this.content = content;
        return this;
    }

    public static Res error(int code, String msg) {
        return new Res().errorRes(code, msg);
    }

    private Res errorRes(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static Res error(String msg) {
        return new Res().errorRes(msg);
    }

    private Res errorRes(String msg) {
        this.code = errCode;
        this.msg = msg;
        return this;
    }

    public static Res error() {
        return new Res().errorRes();
    }

    private Res errorRes() {
        this.code = errCode;
        this.msg = errorMsg;
        return this;
    }
}
