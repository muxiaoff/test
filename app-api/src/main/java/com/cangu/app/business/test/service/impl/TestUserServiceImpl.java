package com.cangu.app.business.test.service.impl;

import com.cangu.app.persistence.Res;
import com.cangu.app.persistence.service.CrudService;
import com.cangu.app.business.test.dao.TestUserDao;
import com.cangu.app.business.test.entity.TestUser;
import com.cangu.app.business.test.dto.vo.TestUserVo;
import com.cangu.app.business.test.service.TestUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户Service
 *
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:49
 */
@Service
public class TestUserServiceImpl extends CrudService<TestUserDao, TestUser, TestUserVo> implements TestUserService{


}
