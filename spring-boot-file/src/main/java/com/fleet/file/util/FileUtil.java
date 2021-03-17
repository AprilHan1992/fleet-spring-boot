package com.fleet.file.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
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
     * @param file    上传文件
     * @param dirs    上传目录
     * @param newname 新文件名
     */
    public static void upload(MultipartFile file, String dirs, String newname) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        OutputStream os = new FileOutputStream(dirs + newname);
        os.write(file.getBytes());
        os.flush();
        os.close();
    }

    /**
     * 文件上传
     *
     * @param bytes   上传文件 byte[]
     * @param dirs    上传目录
     * @param newname 新文件名
     */
    public static void upload(byte[] bytes, String dirs, String newname) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        OutputStream os = new FileOutputStream(dirs + newname);
        os.write(bytes);
        os.flush();
        os.close();
    }

    /**
     * 文件上传
     *
     * @param is      上传文件流
     * @param dirs    上传目录
     * @param newname 新文件名
     */
    public static void upload(InputStream is, String dirs, String newname) throws Exception {
        if (!mkdirs(dirs)) {
            return;
        }

        OutputStream os = new FileOutputStream(dirs + newname);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            os.write(b, 0, len);
        }
        is.close();
        os.flush();
        os.close();
    }

    /**
     * 文件下载
     *
     * @param dirs     下载目录
     * @param origname 原文件名
     * @param response 响应
     */
    public static void download(String dirs, String origname, HttpServletResponse response) throws Exception {
        download(dirs, origname, null, response);
    }

    /**
     * 文件下载
     *
     * @param dirs     下载目录
     * @param origname 原文件名
     * @param newname  新文件名
     * @param response 响应
     */
    public static void download(String dirs, String origname, String newname, HttpServletResponse response) throws Exception {
        InputStream is = new FileInputStream(dirs + origname);

        response.reset();
        response.setContentType("bin");
        if (StringUtils.isBlank(newname)) {
            newname = origname;
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newname.getBytes(), StandardCharsets.ISO_8859_1));
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
     * @param dirs     下载目录
     * @param origname 原文件名
     * @param response 响应
     */
    public static void downloadResource(String dirs, String origname, HttpServletResponse response) throws Exception {
        downloadResource(dirs, origname, null, response);
    }

    /**
     * resource 文件下载
     *
     * @param dirs     下载目录
     * @param origname 原文件名
     * @param newname  新文件名
     * @param response 响应
     */
    public static void downloadResource(String dirs, String origname, String newname, HttpServletResponse response) throws Exception {
        InputStream is = FileUtil.class.getResourceAsStream(dirs + origname);

        response.reset();
        response.setContentType("bin");
        if (StringUtils.isBlank(newname)) {
            newname = origname;
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newname.getBytes(), StandardCharsets.ISO_8859_1));
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
     * @param dirs     下载目录
     * @param files    文件
     * @param newname  新文件名
     * @param response 响应
     */
    public static void download(String dirs, File[] files, String newname, HttpServletResponse response) throws Exception {
        String zipFilename = UUIDUtil.getUUID() + ".zip";
        File zipFile = new File(dirs + zipFilename);
        if (!zipFile.exists()) {
            if (!zipFile.createNewFile()) {
                return;
            }
        }

        OutputStream os = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(os);
        zos.setEncoding("GBK");
        for (File srcFile : files) {
            if (!zipFile.getPath().equals(srcFile.getPath())) {
                zipFile(zos, srcFile, "");
            }
        }
        zos.finish();
        zos.close();
        os.flush();
        os.close();

        download(dirs, zipFilename, newname, response);
    }

    /**
     * 图片在线查看
     *
     * @param dirs     下载目录
     * @param origname 原文件名
     * @param response 响应
     */
    public static void image(String dirs, String origname, HttpServletResponse response) throws Exception {
        InputStream is = new FileInputStream(dirs + origname);

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
            ZipEntry entry = new ZipEntry(path + srcFile.getName());
            zos.putNextEntry(entry);

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
