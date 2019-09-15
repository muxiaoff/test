package com.cangu.app.config.intercepter;

import com.cangu.app.util.Common;
import com.cangu.app.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author byw
 * 拦截器
 * Created by byw on 2019/28/19.
 */
@Slf4j
public class BootInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Object object = null;
        Boolean flg = true;
        try {
            object = CurrentUser.userId();

        }catch (Exception e) {
            flg = false;
            try {
                Common.responseMessage("获取用户信息异常："+ e.getMessage(), response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return flg;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
