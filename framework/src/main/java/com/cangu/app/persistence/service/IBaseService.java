package com.cangu.app.persistence.service;

import com.cangu.app.persistence.entity.DataEntity;
import com.cangu.app.persistence.entity.PageVo;

import java.util.List;

/**
 * @author sai
 * @date 2019/4/9
 */
public interface IBaseService<T extends DataEntity<T,V>,V> {

    /**
     * 根据id查询单条数据
     *
     * @param id
     * @return
     */
    V get(Long id);

    /**
     * 根据entity查询单条数据
     *
     * @param entity
     * @return
     */
    V get(T entity);

    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    List<V> list(T entity);

    /**
     * 查询分页
     *
     * @param pageNo
     * @param pageSize
     * @param entity
     * @return
     */
    PageVo<T,V> page(Integer pageNo, Integer pageSize, T entity);

    /**
     * 添加
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 自定义唯一属性查询单条数据
     *
     * @param propertyName
     * @param value
     * @return
     */
    V findUniqueByProperty(String propertyName, Object value);

}
