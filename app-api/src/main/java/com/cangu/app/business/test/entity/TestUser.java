package com.cangu.app.business.test.entity;

import com.cangu.app.persistence.entity.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.NoArgsConstructor;
import com.cangu.app.business.test.dto.vo.TestUserVo;
/**
 * 系统用户Entity
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:48
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestUser extends DataEntity<TestUser,TestUserVo> {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String userName = "";
    /** 姓名 */
    private String name = "";
    /** 头像 */
    private String image = "";
    /** 用户状态(1=启用、2=禁用) */
    private int status;
    /** 密码 */
    private String password = "";
    /** 邮箱 */
    private String email = "";
    /** 电话 */
    private String phone = "";
    /** 用户类型(1=老大、2=二营长、3=三营长) */
    private int userType;
    /** 登录错误次数 */
    private int loginErrorCount;
    /** 登录错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginErrorTime;
    /** 极光推送Id */
    private String registrationIds = "";

    public TestUser(Long id) {
        super(id);
    }

}
