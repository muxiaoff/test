package com.cangu.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhengFeiFei on 2018/9/6.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionUser implements Serializable {
    /** 用户Id */
    private Long id;
    /** 用户名称 */
    private String name;
    /** 用户账号 */
    private String account;
    /** 用户类型 */
    private Integer type;
    /** 有效期 */
    private Long validTimeMillons;

    public SessionUser() {
    }

    public SessionUser(Long id, String name, String account, Integer type) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.type = type;
    }

    public SessionUser(Long id, String name, String account, Integer type, Long validTimeMillons) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.type = type;
        this.validTimeMillons = validTimeMillons;
    }


    public SessionUser(Long id, String name, String account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }
}
