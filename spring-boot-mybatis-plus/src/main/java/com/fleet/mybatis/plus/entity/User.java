package com.fleet.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fleet.mybatis.plus.enums.SexEnum;
import com.fleet.mybatis.plus.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author April Han
 */
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * IdType 类型
     * AUTO：数据表 ID 自增
     * NONE：未设置
     * INPUT：手动输入设置 ID
     * ASSIGN_ID：分配 ID (类型为 number 或 string），默认实现类 DefaultIdentifierGenerator（雪花算法）；或使用自定义 ID 生成器（实现类继承 IdentifierGenerator）
     * ASSIGN_UUID：分配 UUID（不含中划线）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Long id;

    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    private SexEnum sex;

    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Long updaterId;

    @TableField(fill = FieldFill.UPDATE)
    private String updaterName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private StatusEnum status;

    @JsonIgnore
    @TableLogic
    private Integer deleted;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
