package com.fleet.excel.entity;

import com.fleet.excel.annotation.ExcelColumn;
import com.fleet.excel.annotation.ExcelSheet;
import com.fleet.excel.util.value.SexValues;

import java.io.Serializable;
import java.util.Date;

@ExcelSheet(template = "/template/User.xls")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelColumn(name = "用户id", column = "a")
    private Long id;

    @ExcelColumn(name = "姓名", column = "b")
    private String name;

    @ExcelColumn(name = "性别", column = "c", values = SexValues.class)
    private Integer sex;

    @ExcelColumn(name = "出生日期", column = "d", dateFormat = "yyyy/mm/dd")
    private Date birth;

    @ExcelColumn(name = "身份证号", column = "e", isExport = false)
    private String idNo;

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
}
