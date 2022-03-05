package com.fleet.file.service.impl;

import com.fleet.file.config.FileConfig;
import com.fleet.file.entity.MultipartFileParam;
import com.fleet.file.service.StorageService;
import com.fleet.file.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author April Han
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final static Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    FileConfig fileConfig;

    @Override
    public void uploadFileByRandomAccessFile(MultipartFileParam param) throws Exception {
        String path = fileConfig.getBigFilePath() + param.getMd5();
        if (!FileUtil.mkdirs(path)) {
            return;
        }

        File tempFile = new File(path, param.getName() + "_tmp");

        RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
        long offset = param.getChunk() * param.getChunkSize();
        byte[] fileData = param.getFile().getBytes();
        // 定位到该分片的偏移量
        raf.seek(offset);
        // 写入该分片数据
        raf.write(fileData);
        // 释放
        raf.close();

        rwStatus(param);

        boolean completed = checkStatus(param);
        if (completed) {
            rename(tempFile, param.getName());
        }
    }

    /**
     * 分块上传
     * 第一步：获取 RandomAccessFile ，随机访问文件类的对象
     * 第二步：调用 RandomAccessFile 的 getChannel()方法，打开文件通道 FileChannel
     * 第三步：获取当前是第几个分块，计算文件的最后偏移量
     * 第四步：获取当前文件分块的字节数组，用于获取文件字节长度
     * 第五步：使用文件通道 FileChannel 类的 map() 方法创建直接字节缓冲器 MappedByteBuffer
     * 第六步：将分块的字节数组放入到当前位置的缓冲区内 mappedByteBuffer.put(byte[] b);
     * 第七步：释放缓冲区
     * 第八步：记录上传进度
     * 第九步：检查进度文件，判断是否完成上传
     */
    @Override
    public void uploadFileByMappedByteBuffer(MultipartFileParam param) throws Exception {
        String path = fileConfig.getBigFilePath() + param.getMd5();
        if (!FileUtil.mkdirs(path)) {
            return;
        }

        File tempFile = new File(path, param.getName() + "_tmp");

        // 第一步
        RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
        // 第二步
        FileChannel fileChannel = raf.getChannel();
        // 第三步
        long offset = param.getChunk() * param.getChunkSize();
        // 第四步
        byte[] fileData = param.getFile().getBytes();
        // 第五步
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, fileData.length);
        // 第六步
        mappedByteBuffer.put(fileData);
        // 第七步
        mappedByteBuffer(mappedByteBuffer);
        fileChannel.close();
        raf.close();
        // 第八步
        rwStatus(param);
        // 第九步
        boolean completed = checkStatus(param);
        if (completed) {
            rename(tempFile, param.getName());
        }
    }

    /**
     * 读取进度文件，分片写入上传进度
     */
    public void rwStatus(MultipartFileParam param) throws Exception {
        File status = new File(fileConfig.getBigFilePath() + param.getMd5(), "status");
        RandomAccessFile raf = new RandomAccessFile(status, "rw");
        raf.setLength(param.getChunks());
        raf.seek(param.getChunk());
        raf.write(Byte.MAX_VALUE);
        raf.close();
    }

    /**
     * 读取进度文件，分片检查，判断是否完成全部上传
     */
    public boolean checkStatus(MultipartFileParam param) throws Exception {
        File status = new File(fileConfig.getBigFilePath() + param.getMd5(), "status");
        byte[] bytes = FileUtils.readFileToByteArray(status);
        for (byte b : bytes) {
            if (b != Byte.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    /**
     * 文件重命名
     *
     * @param file        将要修改名字的文件
     * @param newFilename 新文件名
     * @return
     */
    private boolean rename(File file, String newFilename) {
        if (!file.exists() || file.isDirectory()) {
            logger.info("file does not exist: " + file.getName());
            return false;
        }
        String parent = file.getParent();
        File newFile = new File(parent + File.separatorChar + newFilename);
        // 修改文件名
        return file.renameTo(newFile);
    }

    /**
     * 在 MappedByteBuffer 释放后再对它进行读操作的话就会引发 jvm crash，在并发情况下很容易发生
     * 正在释放时另一个线程正开始读取，于是 crash 就发生了。所以为了系统稳定性释放前一般需要检查是否还有线程在读或写
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
