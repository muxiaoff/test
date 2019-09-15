package com.cangu.app.exception;

/**
 * @author ZhengFeiFei on 14-8-26.
 */
public class ParameterMissException extends Exception {

    public ParameterMissException() {
        super();
    }

    public ParameterMissException(String s) {
        super(s);
    }

    public ParameterMissException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ParameterMissException(Throwable throwable) {
        super(throwable);
    }
}
