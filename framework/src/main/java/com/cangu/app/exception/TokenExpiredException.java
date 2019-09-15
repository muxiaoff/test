package com.cangu.app.exception;

/**
 * @author ZhengFeiFei on 30/10/2016.
 */
public class TokenExpiredException extends Exception {
    public TokenExpiredException() {
        super("Token is expired.");
    }
}
