package com.fleet.uflo2.dao;

import com.fleet.uflo2.entity.Uflo2File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Uflo2FileDao {

    /**
     * 保存报表
     */
    int insertUfol2File(Uflo2File uflo2File);

    /**
     * 根据报表名称删除报表
     */
    int deleteUfol2FileByName(String name);

    /**
     * 更新报表
     */
    int updateUfol2File(Uflo2File uflo2File);

    /**
     * 根据报表名称查询报表
     */
    Uflo2File getUfol2FileByName(String name);

    /**
     * 查询全部报表
     */
    List<Uflo2File> getUfol2FileList();

}
