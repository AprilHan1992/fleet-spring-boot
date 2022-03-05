package com.fleet.validator.entity;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author April Han
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 账户
     */
    @NotBlank(message = "账户不能为空")
//    @Size(max = 20, message = "账户不能多于20个字符")
//    @Size(min = 25, message = "账户不能少于5个字符")
    @Size(min = 5, max = 20, message = "账户长度必须是5-20个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "账户只能为字母、数字中的一种或多种")
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String pwd;

    /**
     * 加盐
     */
    private String pwdSalt;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 用户状态（0：禁用，1：正常，2：锁定）
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
