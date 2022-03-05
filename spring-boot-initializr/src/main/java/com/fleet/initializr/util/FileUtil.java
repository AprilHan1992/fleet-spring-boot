//package com.fleet.initializr.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipOutputStream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
///**
// * 文件工具类
// *
// * @author April Han
// */
//public class FileUtil {
//
//    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
//
//    /**
//     * 文件上传
//     *
//     * @param file    上传文件
//     * @param dirs    上传目录
//     * @param newName 新文件名
//     */
//    public static void upload(MultipartFile file, String dirs, String newName) throws Exception {
//        if (!mkdirs(dirs)) {
//            return;
//        }
//
//        OutputStream os = new FileOutputStream(dirs + newName);
//        os.write(file.getBytes());
//        os.flush();
//        os.close();
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param bytes   上传文件 byte[]
//     * @param dirs    上传目录
//     * @param newName 新文件名
//     */
//    public static void upload(byte[] bytes, String dirs, String newName) throws Exception {
//        if (!mkdirs(dirs)) {
//            return;
//        }
//
//        OutputStream os = new FileOutputStream(dirs + newName);
//        os.write(bytes);
//        os.flush();
//        os.close();
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param is      上传文件流
//     * @param dirs    上传目录
//     * @param newName 新文件名
//     */
//    public static void upload(InputStream is, String dirs, String newName) throws Exception {
//        if (!mkdirs(dirs)) {
//            return;
//        }
//
//        OutputStream os = new FileOutputStream(dirs + newName);
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = is.read(b)) > 0) {
//            os.write(b, 0, len);
//        }
//        is.close();
//        os.flush();
//        os.close();
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param dirs         下载目录
//     * @param originalName 原文件名
//     * @param response     响应
//     */
//    public static void download(String dirs, String originalName, HttpServletResponse response) throws Exception {
//        download(dirs, originalName, null, response);
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param dirs         下载目录
//     * @param originalName 原文件名
//     * @param newName      新文件名
//     * @param response     响应
//     */
//    public static void download(String dirs, String originalName, String newName, HttpServletResponse response) throws Exception {
//        InputStream is = new FileInputStream(dirs + originalName);
//
//        response.reset();
//        response.setContentType("bin");
//        if (StringUtils.isBlank(newName)) {
//            newName = originalName;
//        }
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newName.getBytes(), StandardCharsets.ISO_8859_1));
//        OutputStream os = response.getOutputStream();
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = is.read(b)) > 0) {
//            os.write(b, 0, len);
//        }
//        os.flush();
//        os.close();
//        is.close();
//    }
//
//    /**
//     * resource 文件下载
//     *
//     * @param dirs         下载目录
//     * @param originalName 原文件名
//     * @param response     响应
//     */
//    public static void downloadResource(String dirs, String originalName, HttpServletResponse response) throws Exception {
//        downloadResource(dirs, originalName, null, response);
//    }
//
//    /**
//     * resource 文件下载
//     *
//     * @param dirs         下载目录
//     * @param originalName 原文件名
//     * @param newName      新文件名
//     * @param response     响应
//     */
//    public static void downloadResource(String dirs, String originalName, String newName, HttpServletResponse response) throws Exception {
//        InputStream is = FileUtil.class.getResourceAsStream(dirs + originalName);
//
//        response.reset();
//        response.setContentType("bin");
//        if (StringUtils.isBlank(newName)) {
//            newName = originalName;
//        }
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(newName.getBytes(), StandardCharsets.ISO_8859_1));
//        OutputStream os = response.getOutputStream();
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = is.read(b)) > 0) {
//            os.write(b, 0, len);
//        }
//        os.flush();
//        os.close();
//        is.close();
//    }
//
//    /**
//     * 批量文件打包下载
//     *
//     * @param dirs     下载目录
//     * @param files    文件
//     * @param newName  新文件名
//     * @param response 响应
//     */
//    public static void download(String dirs, File[] files, String newName, HttpServletResponse response) throws Exception {
//        String zipFilename = UUIDUtil.getUUID() + ".zip";
//        File zipFile = new File(dirs + zipFilename);
//        if (!zipFile.exists()) {
//            if (!zipFile.createNewFile()) {
//                return;
//            }
//        }
//
//        OutputStream os = new FileOutputStream(zipFile);
//        ZipOutputStream zos = new ZipOutputStream(os);
//        zos.setEncoding("GBK");
//        for (File srcFile : files) {
//            if (!zipFile.getPath().equals(srcFile.getPath())) {
//                zipFile(zos, srcFile, "");
//            }
//        }
//        zos.finish();
//        zos.close();
//        os.flush();
//        os.close();
//
//        download(dirs, zipFilename, newName, response);
//    }
//
//    /**
//     * 图片在线查看
//     *
//     * @param dirs         下载目录
//     * @param originalName 原文件名
//     * @param response     响应
//     */
//    public static void image(String dirs, String originalName, HttpServletResponse response) throws Exception {
//        InputStream is = new FileInputStream(dirs + originalName);
//
//        OutputStream os = response.getOutputStream();
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = is.read(b)) > 0) {
//            os.write(b, 0, len);
//        }
//        os.flush();
//        os.close();
//        is.close();
//    }
//
//    public static String rename(String filename) {
//        return UUIDUtil.getUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);
//    }
//
//    public static boolean mkdirs(String dirs) {
//        File fileDirs = new File(dirs);
//        return mkdirs(fileDirs);
//    }
//
//    public static boolean mkdirs(File fileDirs) {
//        if (fileDirs.exists()) {
//            return fileDirs.isDirectory();
//        } else {
//            return fileDirs.mkdirs();
//        }
//    }
//
//    /**
//     * @param zos     文件流
//     * @param srcFile 压缩的源文件
//     * @param path    在 .zip 中的相对路径
//     * @throws IOException
//     */
//    private static void zipFile(ZipOutputStream zos, File srcFile, String path) throws IOException {
//        if (!"".equals(path) && !path.endsWith(File.separator)) {
//            path += File.separator;
//        }
//
//        if (srcFile.isDirectory()) {
//            zos.putNextEntry(new ZipEntry(path + srcFile.getName() + File.separator));
//            zos.closeEntry();
//
//            File[] files = srcFile.listFiles();
//            if (files != null && files.length != 0) {
//                for (File f : files) {
//                    zipFile(zos, f, path + srcFile.getName());
//                }
//            }
//        } else {
//            InputStream is = new FileInputStream(srcFile);
//            ZipEntry entry = new ZipEntry(path + srcFile.getName());
//            zos.putNextEntry(entry);
//
//            byte[] b = new byte[1024];
//            int len;
//            while ((len = is.read(b)) > 0) {
//                zos.write(b, 0, len);
//            }
//            zos.closeEntry();
//            is.close();
//        }
//    }
//}
