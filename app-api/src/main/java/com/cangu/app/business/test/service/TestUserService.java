package com.cangu.app.business.test.service;

import com.cangu.app.persistence.Res;
import com.cangu.app.persistence.service.CrudService;
import com.cangu.app.business.test.entity.TestUser;
import com.cangu.app.business.test.dto.vo.TestUserVo;
import com.cangu.app.persistence.service.IBaseService;
/**
 * 系统用户Service
 *
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:49
 */
public interface TestUserService extends IBaseService<TestUser, TestUserVo> {


}
