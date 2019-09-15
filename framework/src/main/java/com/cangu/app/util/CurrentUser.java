package com.cangu.app.util;

import com.cangu.app.persistence.entity.SessionUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author ZhengFeiFei on 2018/12/5.
 * 当前用户
 */
public abstract class CurrentUser {

    private static SessionUser currentUser = new SessionUser();

    /**
     * 获取登录的用户Id
     * @return
     */
//    public static Long userId() {
//        return currentUser.getId();
//    }



    /**
     * 获取登录的用户名
     * @return
     */
//    public static String userName() {
//        return currentUser.getName();
//    }

    public static Long userId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return user().getId();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (null == request) {
            return user().getId();
        }
        currentUser = (SessionUser) request.getAttribute("token");
        if (null == currentUser) {
            return user().getId();
        }
        if (null != currentUser.getId()) {
            return user().getId();
        }
        currentUser.setId(currentUser.getId());
        currentUser.setName(currentUser.getName());
        return currentUser.getId();
    }

    public static String userName() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return user().getName();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (null == request) {
            return user().getName();
        }
        currentUser = (SessionUser) request.getAttribute("token");
        if (null == currentUser) {
            return user().getName();
        }
        if (null != currentUser.getId()) {
            return user().getName();
        }
        currentUser.setId(currentUser.getId());
        currentUser.setName(currentUser.getName());
        return currentUser.getName();
    }

    public static SessionUser user() {
        SessionUser currentUser = new SessionUser();
        currentUser.setId(0L);
        currentUser.setName("哈哈哈");
        return currentUser;
    }
}
