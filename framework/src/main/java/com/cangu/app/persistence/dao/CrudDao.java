package com.cangu.app.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO支持类实现
 * @author ZhengFeiFei
 * @version 2017-11-6
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	T get(Long id);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	T get(T entity);

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * @param propertyName
	 * @param value
	 * @return
	 */
	T findUniqueByProperty(@Param(value = "propertyName") String propertyName, @Param(value = "value") Object value);

	/**
	 * 查询数据列表，
	 * @param entity
	 * @return
	 */
	List<T> list(T entity);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insert(T entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	int update(T entity);

	/**
	 * 根据id修改实体中不为null的列
	 * @param entity
	 * @return
	 */
	int updateExistById(T entity);

	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param ids
	 * @return
	 */
	int delete(@Param("ids") List<Long> ids);

}