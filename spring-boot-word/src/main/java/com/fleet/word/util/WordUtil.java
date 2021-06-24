package com.fleet.word.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Word 工具类
 *
 * @author April Han
 */
public class WordUtil {

    private Configuration config;

    public WordUtil() {
        config = new Configuration(Configuration.VERSION_2_3_29);
        config.setEncoding(Locale.getDefault(), "UTF-8");
        config.setClassicCompatible(true);
    }

    /**
     * 读取 word 文档中字符串信息
     */
    public String read(File file) {
        String text = "";
        try {
            String fileName = file.getName();
            if (fileName.toLowerCase().endsWith(".doc")) {
                InputStream is = new FileInputStream(file);
                WordExtractor extractor = new WordExtractor(is);
                text = extractor.getText();
                extractor.close();
            } else if (fileName.toLowerCase().endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(file.getPath());
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                text = extractor.getText();
                extractor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 根据 word xml模板生成word文档
     */
    public void export(Map<String, Object> map, String tempPath, String tempName, OutputStream os) throws Exception {
        File file = getTempFile(map, tempPath, tempName);

        InputStream is = new FileInputStream(file);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

    /**
     * 生成 word 文档缓存文件
     *
     * @param map      替换数据
     * @param tempPath 缓存目录
     * @param tempFile resources/template下模板文件
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public File getTempFile(Map<String, Object> map, String tempPath, String tempFile) throws Exception {
        config.setClassForTemplateLoading(WordUtil.class, "/template");
        Template template = config.getTemplate(tempFile);
        File file = new File(tempPath + UUIDUtil.getUUID() + ".doc");
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        template.process(map, writer);
        writer.flush();
        writer.close();
        return file;
    }

    /**
     * 读取文档中表格
     * 返回数据格式（表=>行=>列）
     */
    public Map<Integer, Map<Integer, List<String>>> readExcelInWord(File file) {
        Map<Integer, Map<Integer, List<String>>> map = new HashMap<>();
        try {
            String fileName = file.getName();
            if (fileName.toLowerCase().endsWith(".doc")) {
                InputStream is = new FileInputStream(file);
                POIFSFileSystem pfs = new POIFSFileSystem(is);
                HWPFDocument document = new HWPFDocument(pfs);
                Range range = document.getRange();
                TableIterator iterator = new TableIterator(range);
                int t = 0;
                while (iterator.hasNext()) {
                    Table table = iterator.next();
                    Map<Integer, List<String>> rows = new HashMap<>();
                    for (int i = 0; i < table.numRows(); i++) {
                        TableRow tableRow = table.getRow(i);
                        List<String> cells = new ArrayList<>();
                        for (int j = 0; j < tableRow.numCells(); j++) {
                            TableCell tableCell = tableRow.getCell(j);
                            StringBuilder sb = new StringBuilder();
                            for (int k = 0; k < tableCell.numParagraphs(); k++) {
                                Paragraph paragraph = tableCell.getParagraph(k);
                                sb.append(paragraph.text());
                            }
                            if (StringUtils.isNotEmpty(sb.toString())) {
                                cells.add(sb.substring(0, sb.length() - 1));
                            } else {
                                cells.add("");
                            }
                        }
                        rows.put(i, cells);
                    }
                    map.put(t++, rows);
                }
            } else if (fileName.toLowerCase().endsWith(".docx")) {
                InputStream is = new FileInputStream(file);
                XWPFDocument document = new XWPFDocument(is);
                Iterator<XWPFTable> iterator = document.getTablesIterator();
                int t = 0;
                while (iterator.hasNext()) {
                    XWPFTable table = iterator.next();
                    Map<Integer, List<String>> rows = new HashMap<>();
                    List<XWPFTableRow> tableRows = table.getRows();
                    for (int i = 0; i < tableRows.size(); i++) {
                        XWPFTableRow tableRow = tableRows.get(i);
                        List<String> cells = new ArrayList<>();
                        List<XWPFTableCell> tableCells = tableRow.getTableCells();
                        for (XWPFTableCell tableCell : tableCells) {
                            StringBuilder sb = new StringBuilder();
                            List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                            for (int j = 0; j < paragraphs.size(); j++) {
                                XWPFParagraph paragraph = paragraphs.get(j);
                                sb.append(paragraph.getText());
                                if (j != paragraphs.size() - 1) {
                                    sb.append("\r");
                                }
                            }
                            cells.add(sb.toString());
                        }
                        rows.put(i, cells);
                    }
                    map.put(t++, rows);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
