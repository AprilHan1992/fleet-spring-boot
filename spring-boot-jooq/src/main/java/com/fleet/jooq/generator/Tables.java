/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator;


import com.fleet.jooq.generator.tables.Author;
import com.fleet.jooq.generator.tables.Book;
import com.fleet.jooq.generator.tables.CheckContent;
import com.fleet.jooq.generator.tables.CheckItem;
import com.fleet.jooq.generator.tables.Content;
import com.fleet.jooq.generator.tables.Dept;
import com.fleet.jooq.generator.tables.DeptCopy;
import com.fleet.jooq.generator.tables.Dict;
import com.fleet.jooq.generator.tables.HibernateSequence;
import com.fleet.jooq.generator.tables.Job;
import com.fleet.jooq.generator.tables.Log;
import com.fleet.jooq.generator.tables.MailGroup;
import com.fleet.jooq.generator.tables.Menu;
import com.fleet.jooq.generator.tables.MenuCopy;
import com.fleet.jooq.generator.tables.Money;
import com.fleet.jooq.generator.tables.Msg;
import com.fleet.jooq.generator.tables.OauthClientDetails;
import com.fleet.jooq.generator.tables.PdtItem;
import com.fleet.jooq.generator.tables.Permission;
import com.fleet.jooq.generator.tables.QuartzJob;
import com.fleet.jooq.generator.tables.QuartzJobLog;
import com.fleet.jooq.generator.tables.Recommendsps;
import com.fleet.jooq.generator.tables.Role;
import com.fleet.jooq.generator.tables.RoleMenu;
import com.fleet.jooq.generator.tables.To;
import com.fleet.jooq.generator.tables.User;
import com.fleet.jooq.generator.tables.UserCopy;
import com.fleet.jooq.generator.tables.UserCopy1;
import com.fleet.jooq.generator.tables.UserCopy1Copy;
import com.fleet.jooq.generator.tables.UserCopyCopy;
import com.fleet.jooq.generator.tables.UserDept;
import com.fleet.jooq.generator.tables.UserRole;
import com.fleet.jooq.generator.tables.Value;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in fleet-test
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>fleet-test.author</code>.
     */
    public static final Author AUTHOR = com.fleet.jooq.generator.tables.Author.AUTHOR;

    /**
     * The table <code>fleet-test.book</code>.
     */
    public static final Book BOOK = com.fleet.jooq.generator.tables.Book.BOOK;

    /**
     * The table <code>fleet-test.check_content</code>.
     */
    public static final CheckContent CHECK_CONTENT = com.fleet.jooq.generator.tables.CheckContent.CHECK_CONTENT;

    /**
     * The table <code>fleet-test.check_item</code>.
     */
    public static final CheckItem CHECK_ITEM = com.fleet.jooq.generator.tables.CheckItem.CHECK_ITEM;

    /**
     * The table <code>fleet-test.content</code>.
     */
    public static final Content CONTENT = com.fleet.jooq.generator.tables.Content.CONTENT;

    /**
     * 部门管理
     */
    public static final Dept DEPT = com.fleet.jooq.generator.tables.Dept.DEPT;

    /**
     * 部门管理
     */
    public static final DeptCopy DEPT_COPY = com.fleet.jooq.generator.tables.DeptCopy.DEPT_COPY;

    /**
     * The table <code>fleet-test.dict</code>.
     */
    public static final Dict DICT = com.fleet.jooq.generator.tables.Dict.DICT;

    /**
     * The table <code>fleet-test.hibernate_sequence</code>.
     */
    public static final HibernateSequence HIBERNATE_SEQUENCE = com.fleet.jooq.generator.tables.HibernateSequence.HIBERNATE_SEQUENCE;

    /**
     * 部门管理
     */
    public static final Job JOB = com.fleet.jooq.generator.tables.Job.JOB;

    /**
     * 系统日志
     */
    public static final Log LOG = com.fleet.jooq.generator.tables.Log.LOG;

    /**
     * 邮箱组信息
     */
    public static final MailGroup MAIL_GROUP = com.fleet.jooq.generator.tables.MailGroup.MAIL_GROUP;

    /**
     * 菜单
     */
    public static final Menu MENU = com.fleet.jooq.generator.tables.Menu.MENU;

    /**
     * 菜单管理
     */
    public static final MenuCopy MENU_COPY = com.fleet.jooq.generator.tables.MenuCopy.MENU_COPY;

    /**
     * The table <code>fleet-test.money</code>.
     */
    public static final Money MONEY = com.fleet.jooq.generator.tables.Money.MONEY;

    /**
     * 角色
     */
    public static final Msg MSG = com.fleet.jooq.generator.tables.Msg.MSG;

    /**
     * The table <code>fleet-test.oauth_client_details</code>.
     */
    public static final OauthClientDetails OAUTH_CLIENT_DETAILS = com.fleet.jooq.generator.tables.OauthClientDetails.OAUTH_CLIENT_DETAILS;

    /**
     * The table <code>fleet-test.pdt_item</code>.
     */
    public static final PdtItem PDT_ITEM = com.fleet.jooq.generator.tables.PdtItem.PDT_ITEM;

    /**
     * 权限管理
     */
    public static final Permission PERMISSION = com.fleet.jooq.generator.tables.Permission.PERMISSION;

    /**
     * The table <code>fleet-test.quartz_job</code>.
     */
    public static final QuartzJob QUARTZ_JOB = com.fleet.jooq.generator.tables.QuartzJob.QUARTZ_JOB;

    /**
     * The table <code>fleet-test.quartz_job_log</code>.
     */
    public static final QuartzJobLog QUARTZ_JOB_LOG = com.fleet.jooq.generator.tables.QuartzJobLog.QUARTZ_JOB_LOG;

    /**
     * The table <code>fleet-test.recommendsps</code>.
     */
    public static final Recommendsps RECOMMENDSPS = com.fleet.jooq.generator.tables.Recommendsps.RECOMMENDSPS;

    /**
     * 角色信息
     */
    public static final Role ROLE = com.fleet.jooq.generator.tables.Role.ROLE;

    /**
     * 用户与角色对应关系
     */
    public static final RoleMenu ROLE_MENU = com.fleet.jooq.generator.tables.RoleMenu.ROLE_MENU;

    /**
     * 角色
     */
    public static final To TO = com.fleet.jooq.generator.tables.To.TO;

    /**
     * 用户信息
     */
    public static final User USER = com.fleet.jooq.generator.tables.User.USER;

    /**
     * 用户信息
     */
    public static final UserCopy USER_COPY = com.fleet.jooq.generator.tables.UserCopy.USER_COPY;

    /**
     * The table <code>fleet-test.user_copy1</code>.
     */
    public static final UserCopy1 USER_COPY1 = com.fleet.jooq.generator.tables.UserCopy1.USER_COPY1;

    /**
     * The table <code>fleet-test.user_copy1_copy</code>.
     */
    public static final UserCopy1Copy USER_COPY1_COPY = com.fleet.jooq.generator.tables.UserCopy1Copy.USER_COPY1_COPY;

    /**
     * The table <code>fleet-test.user_copy_copy</code>.
     */
    public static final UserCopyCopy USER_COPY_COPY = com.fleet.jooq.generator.tables.UserCopyCopy.USER_COPY_COPY;

    /**
     * 用户与角色对应关系
     */
    public static final UserDept USER_DEPT = com.fleet.jooq.generator.tables.UserDept.USER_DEPT;

    /**
     * 用户与角色对应信息
     */
    public static final UserRole USER_ROLE = com.fleet.jooq.generator.tables.UserRole.USER_ROLE;

    /**
     * The table <code>fleet-test.value</code>.
     */
    public static final Value VALUE = com.fleet.jooq.generator.tables.Value.VALUE;
}
