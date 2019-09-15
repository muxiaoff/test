package com.cangu.app.business.test.dto.arg;

import com.cangu.app.persistence.dto.BasePageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import com.cangu.app.business.test.entity.TestUser;
/**
 * 系统用户Search
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:49
 */
@Getter
@Setter
@ApiModel(value = "TestUserSearch")
public class TestUserSearch extends BasePageDto {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    private String userName = "";
    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String name = "";
    /** 头像 */
    @ApiModelProperty(value = "头像")
    private String image = "";
    /** 用户状态(1=启用、2=禁用) */
    @ApiModelProperty(value = "用户状态(1=启用、2=禁用)")
    private int status;
    /** 密码 */
    @ApiModelProperty(value = "密码")
    private String password = "";
    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email = "";
    /** 电话 */
    @ApiModelProperty(value = "电话")
    private String phone = "";
    /** 用户类型(1=老大、2=二营长、3=三营长) */
    @ApiModelProperty(value = "用户类型(1=老大、2=二营长、3=三营长)")
    private int userType;
    /** 登录错误次数 */
    @ApiModelProperty(value = "登录错误次数")
    private int loginErrorCount;
    /** 登录错误时间 */
    @ApiModelProperty(value = "登录错误时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginErrorTime;
    /** 极光推送Id */
    @ApiModelProperty(value = "极光推送Id")
    private String registrationIds = "";

    public TestUser toTestUser() {
        TestUser testUser = new TestUser();
        BeanUtils.copyProperties(this, testUser);
        return testUser;
    }

}
