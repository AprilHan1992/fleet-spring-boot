package com.fleet.easyexcel.converter;


import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author April Han
 */
public class SexConverter implements Converter<Integer> {

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String sv = cellData.getStringValue();
        if ("男".equals(sv)) {
            return 1;
        } else if ("女".equals(sv)) {
            return 0;
        }
        return null;
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (integer.equals(1)) {
            return new CellData("男");
        } else if (integer.equals(0)) {
            return new CellData("女");
        }
        return null;
    }
}
