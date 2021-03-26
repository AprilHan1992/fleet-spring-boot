package com.fleet.zip.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Zip 工具类
 *
 * @author April Han
 */
public class ZipUtil {

    private final static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 读取 .zip 文件内容
     *
     * @param zipFile 压缩文件
     */
    public static List<String> read(File zipFile) throws IOException {
        List<String> list = new ArrayList<>();
        InputStream is = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is), Charset.forName("GBK"));
        java.util.zip.ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            list.add(zipEntry.getName());
        }
        zis.closeEntry();
        is.close();
        return list;
    }

    /**
     * 压缩成 .zip 文件
     *
     * @param zipFile     压缩的目标文件
     * @param srcFileList 压缩的源文件
     */
    public static void zip(String zipFile, List<File> srcFileList) throws IOException {
        if (!zipFile.endsWith(".zip") && !zipFile.endsWith(".ZIP")) {
            logger.error("file [" + zipFile + "] is not .zip type file");
            return;
        }
        zip(new File(zipFile), srcFileList);
    }

    /**
     * 压缩成 .zip 文件
     *
     * @param zipFile     压缩的目标文件
     * @param srcFileList 压缩的源文件
     */
    public static void zip(File zipFile, List<File> srcFileList) throws IOException {
        if (!zipFile.getName().endsWith(".zip") && !zipFile.getName().endsWith(".ZIP")) {
            logger.error("file [" + zipFile + "] is not .zip type file");
            return;
        }
        OutputStream os = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(os);
        zos.setEncoding("GBK");
        for (File srcFile : srcFileList) {
            if (!zipFile.getPath().equals(srcFile.getPath())) {
                zipFile(zos, srcFile, "");
            }
        }
        zos.finish();
        zos.close();
        os.flush();
        os.close();
    }

    /**
     * @param zos     文件流
     * @param srcFile 压缩的源文件
     * @param path    在 .zip 中的相对路径
     * @throws IOException
     */
    private static void zipFile(ZipOutputStream zos, File srcFile, String path) throws IOException {
        if (!"".equals(path) && !path.endsWith(File.separator)) {
            path += File.separator;
        }

        if (srcFile.isDirectory()) {
            zos.putNextEntry(new ZipEntry(path + srcFile.getName() + File.separator));
            zos.closeEntry();

            File[] files = srcFile.listFiles();
            if (files != null && files.length != 0) {
                for (File f : files) {
                    zipFile(zos, f, path + srcFile.getName());
                }
            }
        } else {
            InputStream is = new FileInputStream(srcFile);
            ZipEntry zipEntry = new ZipEntry(path + srcFile.getName());
            zos.putNextEntry(zipEntry);

            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) > 0) {
                zos.write(b, 0, len);
            }
            zos.closeEntry();
            is.close();
        }
    }

    /**
     * 解压缩 .zip 文件
     *
     * @param zipFile   压缩文件地址
     * @param targetDir 解压文件输出的目录
     */
    public static void unzip(String zipFile, String targetDir) throws IOException {
        if (!zipFile.endsWith(".zip") && !zipFile.endsWith(".ZIP")) {
            logger.error("file [" + zipFile + "] is not .zip type file");
            return;
        }
        unzip(new File(zipFile), targetDir);
    }

    /**
     * 解压缩 .zip 文件
     *
     * @param zipFile   压缩文件
     * @param targetDir 解压文件输出的目录
     */
    public static void unzip(File zipFile, String targetDir) throws IOException {
        unzip(new ZipFile(zipFile, "GBK"), targetDir);
    }

    /**
     * 解压缩 .zip 文件
     *
     * @param zipFile   压缩文件
     * @param targetDir 解压文件输出的目录
     */
    public static void unzip(ZipFile zipFile, String targetDir) throws IOException {
        if (!mkdirs(targetDir)) {
            return;
        }

        if (!targetDir.endsWith(File.separator)) {
            targetDir += File.separator;
        }

        Enumeration<ZipEntry> zipEntries = zipFile.getEntries();
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = zipEntries.nextElement();

            if (zipEntry.isDirectory()) {
                mkdirs(targetDir + zipEntry.getName());
            } else {
                File file = new File(targetDir + zipEntry.getName());
                File parentFile = file.getParentFile();
                mkdirs(parentFile);

                InputStream is = zipFile.getInputStream(zipEntry);
                OutputStream os = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int len;
                while ((len = is.read(b)) > 0) {
                    os.write(b, 0, len);
                }
                is.close();
                os.flush();
                os.close();
            }
        }
    }

    public static boolean mkdirs(String dirs) {
        File fileDirs = new File(dirs);
        return mkdirs(fileDirs);
    }

    public static boolean mkdirs(File fileDirs) {
        if (fileDirs.exists()) {
            return fileDirs.isDirectory();
        } else {
            return fileDirs.mkdirs();
        }
    }
}
