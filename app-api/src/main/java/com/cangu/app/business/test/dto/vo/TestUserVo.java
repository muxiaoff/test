package com.cangu.app.business.test.dto.vo;

import com.cangu.app.persistence.dto.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 系统用户Vo
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:48
 */
@Getter
@Setter
@ApiModel(value = "TestUserVo")
public class TestUserVo extends BaseVo {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginErrorTime;
    /** 极光推送Id */
    @ApiModelProperty(value = "极光推送Id")
    private String registrationIds = "";

}
