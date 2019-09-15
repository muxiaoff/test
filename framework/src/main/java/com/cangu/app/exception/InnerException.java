package com.cangu.app.exception;


import com.cangu.app.persistence.Res;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhengFeiFei
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class InnerException {

    private final String bodyMissint = "Required request body is missing";
    private final String ERROR_MESSAGE = "内部异常，请联系管理员";
    private final String SERVER_ERROR_MESSAGE = "服务异常，请联系管理员";
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Res exceptionHandler(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();


        String message = ex.getMessage();
        if (StringUtils.isBlank(message)) {
            return Res.error(ERROR_MESSAGE);
        }
        if(ex instanceof MismatchedInputException
                || ex instanceof HttpMessageNotReadableException
                || ex.getMessage().contains(bodyMissint)
                ){
            if (message.contains(bodyMissint)) {
                return Res.error("json数据为null");
            }
            return Res.error("JSON格式错误");
        }

        return Res.error(ERROR_MESSAGE);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res exceptionHandler1(HttpServletRequest request,
                                        HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();

        if (ex instanceof ParameterMissException) {
            return Res.error(ex.getMessage());
        }
        return Res.error(ex.getMessage());
    }
}
