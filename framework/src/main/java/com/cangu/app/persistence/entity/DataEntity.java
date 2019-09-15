package com.cangu.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;

/**
 * @author ZhengFeiFei on 2018/9/6.
 */
@ApiModel(value = "DataEntity")
@Getter
@Setter
public abstract class DataEntity<T,V> extends BaseEntity<T> {

    protected V v;

    protected T t;

    private static final long serialVersionUID = 1L;
    /** 备注 */
    @ApiModelProperty(value = "备注")
    protected String remark = "";

    /** 创建者 */
    @ApiModelProperty(value = "创建者id")
    @JsonIgnore
    protected String createUserId;

    /** 创建日期 */
    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    /** 更新者 */
    @ApiModelProperty(value = "更新者id")
    protected String updateUserId;

    /** 更新日期 */
    @ApiModelProperty(value = "更新日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateTime;

    /** 删除者 */
    @ApiModelProperty(value = "删除者id")
    protected String deleteUserId;

    /** 删除日期 */
    @ApiModelProperty(value = "删除日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime deleteTime;

    /** 删除标记（1：正常；2：删除） */
    @ApiModelProperty(value = "删除标记（1：正常；2：删除）")
    protected Long isDeleted;

    /** 排序条件 */
    @ApiModelProperty(value = "排序条件")
    protected String orderBy;

    public String getOrderBy() {
        String orderBy = "";
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            Field[] fields = clazz.getDeclaredFields();
            // 通过反射创建model的实例
            t = clazz.newInstance();
            if (StringUtils.isNotBlank(this.orderBy)) {
                String[] s = this.orderBy.split(" ");
                if (s.length == 1) {
                    String colume = s[0];
                    if (isFiled(fields, colume)) {
                        orderBy = "a." + colume + " ASC";
                    }
                } else if (s.length >= 2) {
                    String colume = s[0];
                    if (isFiled(fields, colume)) {
                        String order = s[1];
                        if (StringUtils.isBlank(order)) {
                            orderBy = "a." + colume + " ASC";
                        } else {
                            if ("ASC".equals(order.toUpperCase()) || "DESC".equals(order.toUpperCase())) {
                                orderBy = "a." + colume + " " + order.toUpperCase();
                            } else {
                                orderBy = "a." + colume + " ASC";
                            }
                        }
                    }
                }
            }
        }finally {
            return orderBy;
        }
    }

    private boolean isFiled(Field[] fields, String colume) {
        boolean isFiled = false;
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(colume)) {
                isFiled = true;
                break;
            }
        }
        return isFiled;
    }

    public DataEntity() {
        super();
        this.isDeleted = DEL_FLAG_NORMAL;
    }
    public DataEntity(Long id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(){
        setCreateTime(LocalDateTime.now());
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除之前执行方法，子类实现
     */
    @Override
    public void preDelete(){
        setDeleteTime(LocalDateTime.now());
    }


    public V toVo(){
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<V> clazz = (Class<V>) pt.getActualTypeArguments()[1];
            // 通过反射创建model的实例
            v = clazz.newInstance();
            if (null == this || null == this.getId()) {
                v = null;
            } else {
                BeanUtils.copyProperties(this, v);
            }
        }finally {
            return v;
        }
    }
}
