/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.RoleMenu;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * 用户与角色对应关系
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleMenuRecord extends UpdatableRecordImpl<RoleMenuRecord> implements Record4<UInteger, UInteger, UInteger, UInteger> {

    private static final long serialVersionUID = -1801306278;

    /**
     * Setter for <code>fleet-test.role_menu.id</code>. 角色对应菜单id
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.role_menu.id</code>. 角色对应菜单id
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.role_menu.role_id</code>. 角色id
     */
    public void setRoleId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.role_menu.role_id</code>. 角色id
     */
    public UInteger getRoleId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>fleet-test.role_menu.menu_id</code>. 菜单id
     */
    public void setMenuId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.role_menu.menu_id</code>. 菜单id
     */
    public UInteger getMenuId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>fleet-test.role_menu.deleted</code>. 是否删除（1：是，0：否）
     */
    public void setDeleted(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>fleet-test.role_menu.deleted</code>. 是否删除（1：是，0：否）
     */
    public UInteger getDeleted() {
        return (UInteger) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, UInteger, UInteger, UInteger> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, UInteger, UInteger, UInteger> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return RoleMenu.ROLE_MENU.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return RoleMenu.ROLE_MENU.ROLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return RoleMenu.ROLE_MENU.MENU_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return RoleMenu.ROLE_MENU.DELETED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component3() {
        return getMenuId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component4() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getMenuId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleMenuRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleMenuRecord value2(UInteger value) {
        setRoleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleMenuRecord value3(UInteger value) {
        setMenuId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleMenuRecord value4(UInteger value) {
        setDeleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleMenuRecord values(UInteger value1, UInteger value2, UInteger value3, UInteger value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RoleMenuRecord
     */
    public RoleMenuRecord() {
        super(RoleMenu.ROLE_MENU);
    }

    /**
     * Create a detached, initialised RoleMenuRecord
     */
    public RoleMenuRecord(UInteger id, UInteger roleId, UInteger menuId, UInteger deleted) {
        super(RoleMenu.ROLE_MENU);

        set(0, id);
        set(1, roleId);
        set(2, menuId);
        set(3, deleted);
    }
}