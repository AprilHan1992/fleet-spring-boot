package com.fleet.shiro.config;

import com.fleet.shiro.entity.User;
import com.fleet.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 实现AuthorizingRealm接口进行用户认证
 *
 * @author April Han
 */
public class CustomAuthorizingRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    /**
     * 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 用户角色
        if (user.getRoles() != null) {
            authorizationInfo.setRoles(user.getRoles());
        }

        // 用户权限项
        if (user.getPermissions() != null) {
            authorizationInfo.setStringPermissions(user.getPermissions());
        }
        return authorizationInfo;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        if (StringUtils.isEmpty(username)) {
            throw new NullPointerException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new NullPointerException("密码不能为空");
        }

        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("此用户不存在");
        } else {
            if (!user.getPassword().equals(password)) {
                throw new IncorrectCredentialsException("密码错误");
            }
            if (user.getStatus().equals(0)) {
                throw new DisabledAccountException("此账号已被禁用");
            } else if (user.getStatus().equals(2)) {
                throw new LockedAccountException("此账号已被锁定");
            }
        }

        // 模拟登陆次数超限
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new ExcessiveAttemptsException("登录次数超限");
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
