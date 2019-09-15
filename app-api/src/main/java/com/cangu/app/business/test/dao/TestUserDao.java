package com.cangu.app.business.test.dao;

import com.cangu.app.persistence.dao.CrudDao;
import org.springframework.format.annotation.DateTimeFormat;
import com.cangu.app.business.test.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
/**
 * 系统用户Dao
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:48
 */
@Mapper
public interface TestUserDao extends CrudDao<TestUser>{

}
