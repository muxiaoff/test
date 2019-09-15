package com.cangu.app.context;

import com.cangu.app.constans.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class FilterContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID() {
        Object value = get(CommonConstants.USER_ID);
        return returnObjectValue(value);
    }

    public static String getUsername() {
        Object value = get(CommonConstants.USER_NAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(CommonConstants.USER_NAME);
        return returnObjectValue(value);
    }

    public static String getToken() {
        Object value = get(CommonConstants.TOKEN);
        return returnObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.TOKEN, token);
    }

    public static void setName(String name) {
        set(CommonConstants.USER_NAME, name);
    }

    public static void setUserID(String userID) {
        set(CommonConstants.USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(CommonConstants.USER_NAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
