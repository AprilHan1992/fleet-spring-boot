package com.fleet.shiro.online.config.shiro;

import com.fleet.shiro.online.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import java.util.HashSet;
import java.util.Set;

/**
 * 实现AuthorizingRealm接口进行用户认证
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获得该用户角色
        String role = "ADMIN";
        Set<String> roles = new HashSet<>();
        // 需要将 role 封装到 Set 作为 info.setRoles() 的参数
        roles.add(role);
        // 设置该用户拥有的角色
        info.setRoles(roles);
        Set<String> permissions = new HashSet<>();
        permissions.add("USER:GET");
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = new String(token.getPassword());

        // 此处可做用户登录判断
        User user = new User();
        user.setId(1L);
        user.setName(name);

        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
