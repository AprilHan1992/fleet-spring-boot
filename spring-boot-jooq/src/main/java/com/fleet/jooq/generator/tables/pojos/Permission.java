/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * 权限管理
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Permission implements Serializable {

    private static final long serialVersionUID = 1017843949;

    private UInteger permissionId;
    private String   permissionName;
    private String   permissionCode;
    private UInteger permissionOrder;
    private UInteger upperPermissionId;

    public Permission() {}

    public Permission(Permission value) {
        this.permissionId = value.permissionId;
        this.permissionName = value.permissionName;
        this.permissionCode = value.permissionCode;
        this.permissionOrder = value.permissionOrder;
        this.upperPermissionId = value.upperPermissionId;
    }

    public Permission(
        UInteger permissionId,
        String   permissionName,
        String   permissionCode,
        UInteger permissionOrder,
        UInteger upperPermissionId
    ) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionCode = permissionCode;
        this.permissionOrder = permissionOrder;
        this.upperPermissionId = upperPermissionId;
    }

    public UInteger getPermissionId() {
        return this.permissionId;
    }

    public void setPermissionId(UInteger permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public UInteger getPermissionOrder() {
        return this.permissionOrder;
    }

    public void setPermissionOrder(UInteger permissionOrder) {
        this.permissionOrder = permissionOrder;
    }

    public UInteger getUpperPermissionId() {
        return this.upperPermissionId;
    }

    public void setUpperPermissionId(UInteger upperPermissionId) {
        this.upperPermissionId = upperPermissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Permission (");

        sb.append(permissionId);
        sb.append(", ").append(permissionName);
        sb.append(", ").append(permissionCode);
        sb.append(", ").append(permissionOrder);
        sb.append(", ").append(upperPermissionId);

        sb.append(")");
        return sb.toString();
    }
}
