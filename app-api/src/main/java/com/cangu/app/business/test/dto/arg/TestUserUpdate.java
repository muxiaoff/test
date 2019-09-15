package com.cangu.app.business.test.dto.arg;

import com.cangu.app.persistence.dto.BaseDto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import com.cangu.app.business.test.entity.TestUser;
/**
 * 系统用户Update
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:49
 */
@Getter
@Setter
@ApiModel(value = "TestUserUpdate")
public class TestUserUpdate extends BaseDto{

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @Length(max = 64, message = "用户名长度不能大于64")
    private String userName = "";
    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    @Length(max = 64, message = "姓名长度不能大于64")
    private String name = "";
    /** 头像 */
    @ApiModelProperty(value = "头像")
    @Length(max = 25, message = "头像长度不能大于25")
    private String image = "";
    /** 用户状态(1=启用、2=禁用) */
    @ApiModelProperty(value = "用户状态(1=启用、2=禁用)")
    private int status;
    /** 密码 */
    @ApiModelProperty(value = "密码")
    @Length(max = 64, message = "密码长度不能大于64")
    private String password = "";
    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @Length(max = 64, message = "邮箱长度不能大于64")
    private String email = "";
    /** 电话 */
    @ApiModelProperty(value = "电话")
    @Length(max = 64, message = "电话长度不能大于64")
    private String phone = "";
    /** 用户类型(1=老大、2=二营长、3=三营长) */
    @ApiModelProperty(value = "用户类型(1=老大、2=二营长、3=三营长)")
    private int userType;
    /** 登录错误次数 */
    @ApiModelProperty(value = "登录错误次数")
    private int loginErrorCount;
    /** 登录错误时间 */
    @ApiModelProperty(value = "登录错误时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginErrorTime;
    /** 极光推送Id */
    @ApiModelProperty(value = "极光推送Id")
    @Length(max = 64, message = "极光推送Id长度不能大于64")
    private String registrationIds = "";

    public TestUser toTestUser() {
        TestUser testUser = new TestUser();
        BeanUtils.copyProperties(this, testUser);
        return testUser;
    }

}
