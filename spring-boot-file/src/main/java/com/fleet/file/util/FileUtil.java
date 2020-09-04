package com.fleet.file.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author April Han
 */
public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 文件上传
     *
     * @param file        上传文件
     * @param dirs        上传目录
     * @param newFilename 新文件名
     */
    public static void upload(MultipartFile file, String dirs, String newFilename) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        FileOutputStream fos = new FileOutputStream(dirs + newFilename);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
    }

    /**
     * 文件上传
     *
     * @param bytes       上传文件 byte[]
     * @param dirs        上传目录
     * @param newFilename 新文件名
     */
    public static void upload(byte[] bytes, String dirs, String newFilename) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        FileOutputStream fos = new FileOutputStream(dirs + newFilename);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    /**
     * 文件上传
     *
     * @param is          上传文件流
     * @param dirs        上传目录
     * @param newFilename 新文件名
     */
    public static void upload(InputStream is, String dirs, String newFilename) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        FileOutputStream fos = new FileOutputStream(dirs + newFilename);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            fos.write(b, 0, len);
        }
        is.close();
        fos.flush();
        fos.close();
    }

    /**
     * 文件下载
     *
     * @param dirs             下载目录
     * @param originalFilename 原文件名
     * @param response         响应
     */
    public static void download(String dirs, String originalFilename, HttpServletResponse response) throws Exception {
        download(dirs, originalFilename, null, response);
    }

    /**
     * 文件下载
     *
     * @param dirs             下载目录
     * @param originalFilename 原文件名
     * @param newFilename      新文件名
     * @param response         响应
     */
    public static void download(String dirs, String originalFilename, String newFilename, HttpServletResponse response) throws Exception {
        InputStream is = new FileInputStream(dirs + originalFilename);

        response.reset();
        response.setContentType("bin");
        if (StringUtils.isBlank(newFilename)) {
            newFilename = originalFilename;
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newFilename.getBytes(), StandardCharsets.ISO_8859_1));
        OutputStream os = response.getOutputStream();
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
     * resource 文件下载
     *
     * @param dirs             下载目录
     * @param originalFilename 原文件名
     * @param response         响应
     */
    public static void downloadResource(String dirs, String originalFilename, HttpServletResponse response) throws Exception {
        downloadResource(dirs, originalFilename, null, response);
    }

    /**
     * resource 文件下载
     *
     * @param dirs             下载目录
     * @param originalFilename 原文件名
     * @param newFilename      新文件名
     * @param response         响应
     */
    public static void downloadResource(String dirs, String originalFilename, String newFilename, HttpServletResponse response) throws Exception {
        InputStream is = FileUtil.class.getResourceAsStream(dirs + originalFilename);

        response.reset();
        response.setContentType("bin");
        if (StringUtils.isBlank(newFilename)) {
            newFilename = originalFilename;
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newFilename.getBytes(), StandardCharsets.ISO_8859_1));
        OutputStream os = response.getOutputStream();
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
     * 批量文件打包下载
     *
     * @param dirs        下载目录
     * @param files       文件
     * @param newFilename 新文件名
     * @param response    响应
     */
    public static void download(String dirs, File[] files, String newFilename, HttpServletResponse response) throws Exception {
        String zipFilename = UUIDUtil.getUUID() + ".zip";
        File zipFile = new File(dirs + zipFilename);
        if (!zipFile.exists()) {
            if (!zipFile.createNewFile()) {
                return;
            }
        }
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        zipFile(files, zos);
        zos.finish();
        zos.close();
        fos.flush();
        fos.close();
        download(dirs, zipFilename, newFilename, response);
    }

    /**
     * 图片在线查看
     *
     * @param dirs             下载目录
     * @param originalFilename 原文件名
     * @param response         响应
     */
    public static void image(String dirs, String originalFilename, HttpServletResponse response) throws Exception {
        InputStream is = new FileInputStream(dirs + originalFilename);

        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

    public static String rename(String filename) {
        return UUIDUtil.getUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);
    }

    public static boolean mkdirs(String dirs) {
        File fileDirs = new File(dirs);
        if (!fileDirs.exists() && !fileDirs.isDirectory()) {
            return fileDirs.mkdirs();
        }
        return true;
    }

    /**
     * 全部文件打包
     */
    private static void zipFile(File[] files, ZipOutputStream zos) {
        if (files != null) {
            for (File file : files) {
                zipFile(file, zos);
            }
        }
    }

    /**
     * 对文件打包
     */
    private static void zipFile(File file, ZipOutputStream zos) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    // 向压缩文件中输出数据
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = fis.read(b)) > 0) {
                        zos.write(b, 0, len);
                    }
                    zos.closeEntry();
                    // 关闭创建的流对象
                    fis.close();
                } else {
                    try {
                        File[] files = file.listFiles();
                        if (files != null) {
                            for (File f : files) {
                                zipFile(f, zos);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在MappedByteBuffer释放后再对它进行读操作的话就会引发jvm crash，在并发情况下很容易发生
     * 正在释放时另一个线程正开始读取，于是crash就发生了。所以为了系统稳定性释放前一般需要检 查是否还有线程在读或写
     */
    public static void mappedByteBuffer(final MappedByteBuffer mappedByteBuffer) {
        try {
            if (mappedByteBuffer == null) {
                return;
            }

            mappedByteBuffer.force();
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                try {
                    Method cleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner");
                    cleanerMethod.setAccessible(true);
                    Object cleaner = cleanerMethod.invoke(mappedByteBuffer);
                    Method cleanMethod = cleaner.getClass().getMethod("clean");
                    cleanMethod.setAccessible(true);
                    cleanMethod.invoke(cleaner);
                } catch (Exception e) {
                    logger.error("clean MappedByteBuffer error!!!", e);
                }
                logger.info("clean MappedByteBuffer completed!!!");
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
