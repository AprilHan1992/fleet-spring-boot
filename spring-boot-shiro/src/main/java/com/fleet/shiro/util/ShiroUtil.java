package com.fleet.shiro.util;

import com.fleet.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author April Han
 */
public class ShiroUtil {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        return (User) getSubject().getPrincipal();
    }

    public static Long getId() {
        return getUser().getId();
    }

    public static void logout() {
        getSubject().logout();
    }
}
