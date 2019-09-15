package com.cangu.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZhengFeiFei on 2018/9/6.
 */
@ApiModel(value = "BaseEntity")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public abstract class BaseEntity<T>implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected Long id;

    public BaseEntity() {

    }
    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate();

    /**
     * 删除之前执行方法，子类实现
     */
    public abstract void preDelete();

    /**
     * 删除标记（1：正常；2：删除）
     */
    public static final Long DEL_FLAG_NORMAL = 1L;
    public static final Long DEL_FLAG_DELETE = 2L;
}
