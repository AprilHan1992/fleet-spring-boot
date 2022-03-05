package com.fleet.excel.util;

import com.fleet.excel.annotation.ExcelColumn;
import com.fleet.excel.annotation.ExcelSheet;
import com.fleet.excel.util.value.Values;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel工具类
 */
public class ExcelUtil<T> {

    public Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    private FormulaEvaluator formulaEvaluator;

    /**
     * 导入 excel 文件，读取数据
     */
    public List<T> read(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        return read(is);
    }

    /**
     * 导入 excel 文件，读取数据
     */
    public List<T> read(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        return read(fis);
    }

    /**
     * 导入 excel 文件，读取数据
     */
    public List<T> read(File file, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        return read(fis, sheetName);
    }

    /**
     * 导入 excel 文件，读取数据
     */
    public List<T> read(InputStream is) throws Exception {
        int sheetAt = 0;
        int startWith = 1;
        if (clazz.isAnnotationPresent(ExcelSheet.class)) {
            ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);
            sheetAt = excelSheet.sheetAt();
            startWith = excelSheet.startWith();
        }

        Workbook workbook = getWorkbook(is);
        formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = getSheet(workbook, sheetAt);
        Map<Integer, Field> fields = getFields();
        List<T> list = getList(sheet, fields, startWith);
        workbook.close();
        is.close();
        return list;
    }

    /**
     * 导入 excel 文件，读取数据
     */
    public List<T> read(InputStream is, String sheetName) throws Exception {
        int startWith = 1;
        if (clazz.isAnnotationPresent(ExcelSheet.class)) {
            ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);
            startWith = excelSheet.startWith();
        }

        Workbook workbook = getWorkbook(is);
        formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = getSheet(workbook, sheetName);
        Map<Integer, Field> fields = getFields();
        List<T> list = getList(sheet, fields, startWith);
        workbook.close();
        is.close();
        return list;
    }

    private Workbook getWorkbook(InputStream is) throws Exception {
        return WorkbookFactory.create(is);
    }

    private Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet;
        if (StringUtils.isNotEmpty(sheetName)) {
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = getSheet(workbook, 0);
            }
        } else {
            sheet = getSheet(workbook, 0);
        }
        return sheet;
    }

    private Sheet getSheet(Workbook workbook, Integer sheetAt) {
        return workbook.getSheetAt(sheetAt);
    }

    private List<T> getList(Sheet sheet, Map<Integer, Field> fields, Integer startWith) throws Exception {
        List<T> list = new ArrayList<>();
        Map<String, String> mergedRegionValues = mergedRegionValues(sheet);
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            // 从第 startWith 行开始取数据
            if (rowNum < startWith) {
                continue;
            }
            T t = clazz.newInstance();

            // 检查所有字段是否都为空
            boolean isNull = true;
            for (Integer cellNum : fields.keySet()) {
                Field field = fields.get(cellNum);
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }

                // 单元格中的内容
                String value = null;
                if (mergedRegionValues.containsKey(rowNum + "" + cellNum)) {
                    value = mergedRegionValues.get(rowNum + "" + cellNum);
                } else {
                    value = getValue(cell);
                }
                if (StringUtils.isEmpty(value)) {
                    continue;
                } else {
                    if (isNull) {
                        isNull = false;
                    }
                }

                if (Values.class.isAssignableFrom(excelColumn.values()) && !excelColumn.values().isAssignableFrom(Values.class)) {
                    Class<? extends Values> valueClazz = excelColumn.values();
                    value = valueClazz.newInstance().getKey(value);
                }

                Class<?> fieldType = field.getType();
                if (fieldType == Integer.TYPE || fieldType == Integer.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Integer intVal = Integer.parseInt(value);
                        field.set(t, intVal);
                    }
                } else if (fieldType == String.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        field.set(t, value);
                    }
                } else if (fieldType == Short.TYPE || fieldType == Short.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Short shortVal = Short.parseShort(value);
                        field.set(t, shortVal);
                    }
                } else if (fieldType == Long.TYPE || fieldType == Long.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Long longVal = Long.parseLong(value);
                        field.set(t, longVal);
                    }
                } else if (fieldType == Float.TYPE || fieldType == Float.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Float floatVal = Float.parseFloat(value);
                        field.set(t, floatVal);
                    }
                } else if (fieldType == Double.TYPE || fieldType == Double.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Double doubleVal = Double.parseDouble(value);
                        field.set(t, doubleVal);
                    }
                } else if (fieldType == BigDecimal.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        BigDecimal bigDecimalVal = new BigDecimal(value);
                        field.set(t, bigDecimalVal);
                    }
                } else if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        Boolean booleanVal = Boolean.parseBoolean(value);
                        field.set(t, booleanVal);
                    }
                } else if (fieldType == Date.class) {
                    if (StringUtils.isNotEmpty(value)) {
                        SimpleDateFormat sdf = new SimpleDateFormat();
                        if (StringUtils.isNotEmpty(excelColumn.dateFormat().trim())) {
                            sdf = new SimpleDateFormat(excelColumn.dateFormat());
                        }
                        Date dateVal = sdf.parse(value);
                        field.set(t, dateVal);
                    }
                } else {
                    if (StringUtils.isNotEmpty(value)) {
                        field.set(t, value);
                    }
                }
            }
            if (!isNull) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 获取合并单元格中所有单元格数据
     */
    private Map<String, String> mergedRegionValues(Sheet sheet) {
        Map<String, String> mergedRegionValues = new HashMap<>();
        int numMergedRegions = sheet.getNumMergedRegions();
        for (int i = 0; i < numMergedRegions; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstRowNum = range.getFirstRow();
            int lastRowNum = range.getLastRow();
            int firstCellNum = range.getFirstColumn();
            int lastCellNum = range.getLastColumn();
            // 在所有合并单元格中默认取第一个单元格中的数值
            Row row = sheet.getRow(firstRowNum);
            Cell cell = row.getCell(firstCellNum);
            String value = getValue(cell);
            for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                for (int cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) {
                    mergedRegionValues.put(rowNum + "" + cellNum, value);
                }
            }
        }
        return mergedRegionValues;
    }

    /**
     * 导出 excel 模板（表头）文件
     */
    public void exportTemplate(String sheetName, OutputStream os) throws Exception {
        export(sheetName, new ArrayList<>(), os);
    }

    /**
     * 导出 excel 模板（表头、数据）文件
     */
    public void exportTemplate(String sheetName, List<T> list, OutputStream os) throws Exception {
        export(sheetName, list, os);
    }

    /**
     * 导出 excel 文件
     */
    public void export(List<T> list, OutputStream os) throws Exception {
        int startWith = 1;
        if (clazz.isAnnotationPresent(ExcelSheet.class)) {
            ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);
            startWith = excelSheet.startWith();
        }

        // 产生工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 产生工作表对象
        HSSFSheet sheet = createSheet(workbook);

        Map<Integer, Field> fields = getFields();

        createHead(workbook, sheet, fields);
        createBody(workbook, sheet, fields, list, startWith);

        workbook.write(os);
        workbook.close();
        os.flush();
        os.close();
    }

    /**
     * 导出 excel 文件
     */
    public void export(String sheetName, List<T> list, OutputStream os) throws Exception {
        int startWith = 1;
        if (clazz.isAnnotationPresent(ExcelSheet.class)) {
            ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);
            startWith = excelSheet.startWith();
        }

        // 产生工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 产生工作表对象
        HSSFSheet sheet = createSheet(workbook, sheetName);

        Map<Integer, Field> fields = getFields();

        createHead(workbook, sheet, fields);
        createBody(workbook, sheet, fields, list, startWith);

        workbook.write(os);
        workbook.close();
        os.flush();
        os.close();
    }

    private HSSFSheet createSheet(HSSFWorkbook workbook) {
        // 产生工作表对象
        HSSFSheet sheet = workbook.createSheet();
        // 设置默认列宽
        sheet.setDefaultColumnWidth(15);
        // 设置默认行高
        sheet.setDefaultRowHeight((short) (2 * 256));
        return sheet;
    }

    private HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName) {
        // 产生工作表对象
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置默认列宽
        sheet.setDefaultColumnWidth(15);
        // 设置默认行高
        sheet.setDefaultRowHeight((short) (2 * 256));
        return sheet;
    }

    private void createHead(HSSFWorkbook workbook, HSSFSheet sheet, Map<Integer, Field> fields) {
        // 创建表头行
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置标题样式
        setHeadCellStyle(workbook, cellStyle);
        for (Integer cellNum : fields.keySet()) {
            Field field = fields.get(cellNum);
            // 设置实体类私有属性可访问
            field.setAccessible(true);
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            // 创建列
            HSSFCell cell = row.createCell(cellNum);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(excelColumn.name());
        }
    }

    private void createBody(HSSFWorkbook workbook, HSSFSheet sheet, Map<Integer, Field> fields, List<T> list, Integer startWith) throws Exception {
        // 写入各条记录，每条记录对应excel表中的一行
        if (list != null) {
            // 生成一个样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            // 设置内容样式
            setBodyCellStyle(workbook, cellStyle);
            for (int i = 0; i < list.size(); i++) {
                // 创建行
                HSSFRow row = sheet.createRow(i + startWith);
                T t = (T) list.get(i);
                for (Integer cellNum : fields.keySet()) {
                    Field field = fields.get(cellNum);
                    // 设置实体类私有属性可访问
                    field.setAccessible(true);
                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);

                    // 创建列
                    HSSFCell cell = row.createCell(cellNum);
                    cell.setCellStyle(cellStyle);

                    // 根据ExcelVOAttribute中设置情况决定是否导出，有些情况需要保持为空
                    if (excelColumn.isExport()) {
                        Object o = field.get(t);
                        if (o == null) {
                            continue;
                        }

                        Class<?> fieldType = field.getType();

                        // 时间格式转换
                        if (StringUtils.isNotEmpty(excelColumn.dateFormat().trim())) {
                            String dateVal;
                            if (fieldType == Date.class) {
                                SimpleDateFormat sdf = new SimpleDateFormat(excelColumn.dateFormat());
                                dateVal = sdf.format((Date) o);
                            } else {
                                dateVal = String.valueOf(o);
                            }
                            // 生成一个data样式（转换结果可能显示错误，需要手动转换成需要的格式）
                            // HSSFCellStyle dataFormatCellStyle = workbook.createCellStyle();
                            // dataFormatCellStyle.cloneStyleFrom(cellStyle);
                            // CreationHelper createHelper = workbook.getCreationHelper();
                            // short dateFormat = createHelper.createDataFormat().getFormat(excelColumn.dateFormat());
                            // dataFormatCellStyle.setDataFormat(dateFormat);
                            // cell.setCellStyle(dataFormatCellStyle);
                            cell.setCellValue(dateVal);
                            continue;
                        }

                        if (Values.class.isAssignableFrom(excelColumn.values()) && !excelColumn.values().isAssignableFrom(Values.class)) {
                            Class<? extends Values> valueClazz = excelColumn.values();
                            String value = valueClazz.newInstance().get(String.valueOf(o));
                            cell.setCellValue(value);
                            continue;
                        }

                        if (fieldType == Integer.TYPE || fieldType == Integer.class) {
                            Integer intVal = (Integer) o;
                            cell.setCellValue(intVal);
                        } else if (fieldType == String.class) {
                            String stringVal = (String) o;
                            cell.setCellValue(stringVal);
                        } else if (fieldType == Short.TYPE || fieldType == Short.class) {
                            Short shortVal = (Short) o;
                            cell.setCellValue(shortVal);
                        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
                            Long longVal = (Long) o;
                            cell.setCellValue(longVal);
                        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
                            Float floatVal = (Float) o;
                            cell.setCellValue(floatVal);
                        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
                            Double doubleVal = (Double) o;
                            cell.setCellValue(doubleVal);
                        } else if (fieldType == BigDecimal.class) {
                            BigDecimal bigDecimalVal = (BigDecimal) o;
                            double doubleVal = bigDecimalVal.doubleValue();
                            // 生成一个data样式（转换结果可能显示错误，需要手动转换成需要的格式）
                            // HSSFCellStyle dataFormatCellStyle = workbook.createCellStyle();
                            // dataFormatCellStyle.cloneStyleFrom(cellStyle);
                            // CreationHelper createHelper = workbook.getCreationHelper();
                            // short dateFormat = createHelper.createDataFormat().getFormat("￥#,##0.00");
                            // dataFormatCellStyle.setDataFormat(dateFormat);
                            // cell.setCellStyle(dataFormatCellStyle);
                            cell.setCellValue(doubleVal);
                        } else if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
                            Boolean booleanVal = (Boolean) o;
                            cell.setCellValue(booleanVal);
                        } else if (fieldType == Date.class) {
                            Date dateVal = (Date) o;
                            cell.setCellValue(dateVal);
                        } else {
                            String stringVal = String.valueOf(o);
                            cell.setCellValue(stringVal);
                        }
                    }
                }
            }
        }
    }

    private void setHeadCellStyle(HSSFWorkbook workbook, HSSFCellStyle cellStyle) {
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        setCellStyle(cellStyle);
        // 生成字体
        HSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样样式
        cellStyle.setFont(font);
    }

    private void setBodyCellStyle(HSSFWorkbook workbook, HSSFCellStyle cellStyle) {
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        setCellStyle(cellStyle);
        // 生成字体
        HSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeightInPoints((short) 10);
        // 把字体应用到当前的样样式
        cellStyle.setFont(font);
    }

    private void setCellStyle(HSSFCellStyle style) {
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 导出 excel 文件(按照模板导出)
     */
    public void temp(List<T> list, OutputStream os) throws Exception {
        String template = "";
        int sheetAt = 0;
        int headAt = 0;
        int startWith = 1;
        if (clazz.isAnnotationPresent(ExcelSheet.class)) {
            ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);
            template = excelSheet.template();
            sheetAt = excelSheet.sheetAt();
            headAt = excelSheet.headAt();
            startWith = excelSheet.startWith();
        }
        if (StringUtils.isEmpty(template.trim())) {
            throw new Exception("模板文件未找到");
        }

        InputStream is = this.getClass().getResourceAsStream(template);
        if (is == null) {
            throw new Exception("模板文件未找到");
        }

        Workbook workbook = getWorkbook(is);
        Sheet sheet = getSheet(workbook, sheetAt);
        Map<Integer, Field> fields = getFields();

        createHead(sheet, headAt, fields);
        createBody(sheet, startWith, fields, list);

        workbook.write(os);
        workbook.close();
        os.flush();
        os.close();
    }

    private void createHead(Sheet sheet, int headAt, Map<Integer, Field> fields) {
        Row row = sheet.getRow(headAt);
        for (Integer cellNum : fields.keySet()) {
            Field field = fields.get(cellNum);
            // 设置实体类私有属性可访问
            field.setAccessible(true);
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            // 创建列
            Cell cell = row.getCell(cellNum);
            if (cell == null) {
                cell = row.createCell(cellNum);
            }
            cell.setCellValue(excelColumn.name());
        }
    }

    private void createBody(Sheet sheet, Integer startWith, Map<Integer, Field> fields, List<T> list) throws Exception {
        // 写入各条记录，每条记录对应excel表中的一行
        if (list != null) {
            Short rowHeight = null;
            Map<Integer, CellStyle> cellStyleMap = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.getRow(i + startWith);
                if (row == null) {
                    row = sheet.createRow(i + startWith);
                    if (rowHeight != null) {
                        row.setHeight(rowHeight);
                    }
                } else {
                    if (i == 0) {
                        rowHeight = row.getHeight();
                    }
                }

                T t = (T) list.get(i);
                for (Integer cellNum : fields.keySet()) {
                    Field field = fields.get(cellNum);
                    // 设置实体类私有属性可访问
                    field.setAccessible(true);
                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);

                    // 创建列
                    Cell cell = row.getCell(cellNum);
                    if (cell == null) {
                        cell = row.createCell(cellNum);
                    }

                    if (i == 0) {
                        CellStyle cellStyle = cell.getCellStyle();
                        cellStyleMap.put(cellNum, cellStyle);
                    } else {
                        CellStyle cellStyle = cellStyleMap.get(cellNum);
                        cell.setCellStyle(cellStyle);
                    }

                    // 根据ExcelVOAttribute中设置情况决定是否导出，有些情况需要保持为空
                    if (excelColumn.isExport()) {
                        Object o = field.get(t);
                        if (o == null) {
                            continue;
                        }

                        Class<?> fieldType = field.getType();

                        // 时间格式转换
                        if (StringUtils.isNotEmpty(excelColumn.dateFormat().trim())) {
                            String dateVal;
                            if (fieldType == Date.class) {
                                SimpleDateFormat sdf = new SimpleDateFormat(excelColumn.dateFormat());
                                dateVal = sdf.format((Date) o);
                            } else {
                                dateVal = String.valueOf(o);
                            }
                            cell.setCellValue(dateVal);
                            continue;
                        }

                        if (Values.class.isAssignableFrom(excelColumn.values()) && !excelColumn.values().isAssignableFrom(Values.class)) {
                            Class<? extends Values> valueClazz = excelColumn.values();
                            String value = valueClazz.newInstance().get(String.valueOf(o));
                            cell.setCellValue(value);
                            continue;
                        }

                        if (fieldType == Integer.TYPE || fieldType == Integer.class) {
                            Integer intVal = (Integer) o;
                            cell.setCellValue(intVal);
                        } else if (fieldType == String.class) {
                            String stringVal = (String) o;
                            cell.setCellValue(stringVal);
                        } else if (fieldType == Short.TYPE || fieldType == Short.class) {
                            Short shortVal = (Short) o;
                            cell.setCellValue(shortVal);
                        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
                            Long longVal = (Long) o;
                            cell.setCellValue(longVal);
                        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
                            Float floatVal = (Float) o;
                            cell.setCellValue(floatVal);
                        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
                            Double doubleVal = (Double) o;
                            cell.setCellValue(doubleVal);
                        } else if (fieldType == BigDecimal.class) {
                            BigDecimal bigDecimalVal = (BigDecimal) o;
                            double doubleVal = bigDecimalVal.doubleValue();
                            cell.setCellValue(doubleVal);
                        } else if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
                            Boolean booleanVal = (Boolean) o;
                            cell.setCellValue(booleanVal);
                        } else if (fieldType == Date.class) {
                            Date dateVal = (Date) o;
                            cell.setCellValue(dateVal);
                        } else {
                            String stringVal = String.valueOf(o);
                            cell.setCellValue(stringVal);
                        }
                    }
                }
            }
        }
    }

    private Map<Integer, Field> getFields() {
        // 得到所有定义字段
        Field[] declaredFields = clazz.getDeclaredFields();
        // field 集合
        Map<Integer, Field> fields = new HashMap<>();
        // 得到所有 field 并存放到一个 Map 中，重复项会被覆盖
        for (Field field : declaredFields) {
            // 设置实体类私有属性可访问
            field.setAccessible(true);
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                int cellNum = getCellNum(excelColumn.column());
                fields.put(cellNum, field);
            }
        }
        return fields;
    }

    /**
     * 将 EXCEL 中 A,B,C,D,E 列映射成 0,1,2,3,4
     */
    private int getCellNum(String column) {
        column = column.toUpperCase();
        int cellNum = -1;
        char[] chars = column.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            cellNum += (chars[i] - 64) * Math.pow(26, chars.length - i - 1);
        }
        return cellNum;
    }

    /**
     * 设置单元格上提示
     */
    private HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint dvConstraint = DVConstraint.createCustomFormulaConstraint("DD1");
        // 设置数据有效性加载在哪个单元格上，四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation hssfDataValidation = new HSSFDataValidation(cellRangeAddressList, dvConstraint);
        hssfDataValidation.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(hssfDataValidation);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据，显示下拉框
     */
    private HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] explicitListValues, int firstRow, int endRow, int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(explicitListValues);
        // 设置数据有效性加载在哪个单元格上，四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation hssfDataValidation = new HSSFDataValidation(cellRangeAddressList, dvConstraint);
        sheet.addValidationData(hssfDataValidation);
        return sheet;
    }

    private String getValue(Cell cell) {
        String value = "";
        switch (cell.getCellType()) {
            // 数值
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d = DateUtil.getJavaDate(cell.getNumericCellValue());
                    value = sdf.format(d);
                } else {
                    value = BigDecimal.valueOf(cell.getNumericCellValue()).stripTrailingZeros().toPlainString();
                }
                break;
            // 字符串
            case STRING:
                value = cell.getStringCellValue();
                break;
            // Boolean
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case FORMULA:
                value = getCellValue(formulaEvaluator.evaluate(cell));
                break;
            // 空值
            case BLANK:
                break;
            // 错误
            case ERROR:
                break;
            default:
                break;
        }
        return value;
    }

    private String getCellValue(CellValue cellValue) {
        String value = "";
        switch (cellValue.getCellType()) {
            // 数值
            case NUMERIC:
                value = BigDecimal.valueOf(cellValue.getNumberValue()).stripTrailingZeros().toPlainString();
                break;
            // 字符串
            case STRING:
                value = cellValue.getStringValue();
                break;
            // Boolean
            case BOOLEAN:
                value = String.valueOf(cellValue.getBooleanValue());
                break;
            // 空值
            case BLANK:
                break;
            // 错误
            case ERROR:
                break;
            default:
                break;
        }
        return value;
    }
}
