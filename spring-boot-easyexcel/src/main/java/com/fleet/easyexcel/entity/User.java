package com.fleet.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.converters.string.StringImageConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fleet.easyexcel.converter.SexConverter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author April Han
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"用户id"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"姓名"}, index = 1)
    private String name;

    @ExcelProperty(value = {"性别"}, index = 2, converter = SexConverter.class)
    private Integer sex;

    @ExcelProperty(value = {"头像"}, index = 3, converter = StringImageConverter.class)
    private String avatar;

    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty(value = {"出生日期"}, index = 4)
    private Date birth;

    @ExcelIgnore
    private String idNo;

    @NumberFormat("#.##")
    @ExcelProperty(value = {"分数"}, index = 5)
    private Double score;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
