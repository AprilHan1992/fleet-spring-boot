package com.fleet.ureport2.dao;


import com.fleet.ureport2.entity.Ureport2File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Ureport2FileDao {

    /**
     * 保存报表
     */
    int insertUreport2File(Ureport2File ureport2File);

    /**
     * 根据报表名称删除报表
     */
    int deleteUreport2FileByName(String name);

    /**
     * 更新报表
     */
    int updateUreport2File(Ureport2File ureport2File);

    /**
     * 根据报表名称查询报表
     */
    Ureport2File getUreport2FileByName(String name);

    /**
     * 查询全部报表
     */
    List<Ureport2File> getUreport2FileList();
}
