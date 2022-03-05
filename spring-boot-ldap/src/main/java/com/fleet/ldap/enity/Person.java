package com.fleet.ldap.enity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * o – 组织-公司
 * ou – 组织单元-部门
 * c - 国家
 * dc - 域名
 * sn – 真实名称
 * cn - 常用名称
 */
@Data
@Entry(base = "ou=people,dc=fleet,dc=com", objectClasses = "inetOrgPerson")
public class Person {

    @Id
    private Name id;

    @DnAttribute(value = "uid", index = 3)
    private String uid;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String suerName;

    private String userPassword;
}
