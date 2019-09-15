package com.cangu.app.persistence.service;

import com.cangu.app.persistence.dao.CrudDao;
import com.cangu.app.persistence.entity.DataEntity;
import com.cangu.app.persistence.entity.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service基类
 * @author ZhengFeiFei
 * @version 2017-11-6
 */
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T,V>, V> extends BaseService {

	protected V v;
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	protected T t;

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public V get(Long id) {
		return dao.get(id).toVo();
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public V get(T entity) {
		return dao.get(entity).toVo();
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<V> list(T entity) {
		List<T> source = dao.list(entity);
		List<V> target = Lists.newArrayList();
		for (T t1 : source) {
			target.add(t1.toVo());
		}
		return target;
	}
	
	/**
	 * 查询分页数据
	 * @param pageNo pageSize分页对象
	 * @param entity
	 * @return
	 */
	public PageVo page(Integer pageNo, Integer pageSize, T entity) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo,pageSize);
		PageInfo<T> page = new PageInfo(dao.list(entity));
		PageVo<T,V> result = new PageVo(page);
		return result;
	}

//	public V toVo(T t1){
//		try {
//			// 通过反射获取model的真实类型
//			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//			Class<V> clazz = (Class<V>) pt.getActualTypeArguments()[2];
//			// 通过反射创建model的实例
//			v = clazz.newInstance();
//			if (null == t1 || null == t1.getId()) {
//				v = null;
//			} else {
//				BeanUtils.copyProperties(t1, v);
//			}
//		}finally {
//			return v;
//		}
//	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public int insert(T entity) {
		entity.preInsert();
		return dao.insert(entity);
	}
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public int update(T entity) {
		return dao.update(entity);
	}
	/**
	 * 根据id修改实体中不为null的列
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public int updateExistById(T entity) {
		return dao.updateExistById(entity);
	}

	/**
	 * 删除数据
	 * @param ids
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public int delete(List<Long> ids) {
		return dao.delete(ids);
	}


	/**
	 * 获取单条数据
	 * @param
	 * @return
	 */
	public V findUniqueByProperty(String propertyName, Object value){
		return dao.findUniqueByProperty(propertyName, value).toVo();
	}
	
}
