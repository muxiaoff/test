package com.cangu.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhengFeiFei on 2018/6/7.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PageVo<T extends DataEntity<T,V>,V> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(hidden = true)
    protected V v;
    @Getter
    @Setter
    @ApiModelProperty(value = "总条数")
    private Long total;
    @Getter
    @Setter
    @ApiModelProperty(value = "内容")
    private List<V> list = Lists.newArrayList();

    /**
     * 将Entity 转换为 VO
     * @param page
     */
    public PageVo(PageInfo<T> page) {
        List<T> source = page.getList();
        for (T t1 : source) {
            list.add(t1.toVo());
        }
        total = page.getTotal();
    }

}
