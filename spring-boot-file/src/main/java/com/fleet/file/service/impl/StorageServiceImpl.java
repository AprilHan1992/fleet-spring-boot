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
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

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
        String filePath = fileConfig.getBigFilePath() + param.getMd5();
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String filename = param.getName();
        String tempFilename = filename + "_tmp";


        File tempFile = new File(filePath, tempFilename);


        RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
        long offset = param.getChunk() * param.getChunkSize();
        //定位到该分片的偏移量
        raf.seek(offset);
        //写入该分片数据
        raf.write(param.getFile().getBytes());
        // 释放
        raf.close();

        boolean completed = checkStatus(param);
        if (completed) {
            rename(tempFile, filename);
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
     * 第八步：检查文件是否全部完成上传
     */
    @Override
    public void uploadFileByMappedByteBuffer(MultipartFileParam param) throws Exception {
        String filePath = fileConfig.getBigFilePath() + param.getMd5();
        String filename = param.getName();
        String tempFilename = filename + "_tmp";

        File fileDir = new File(filePath);
        File tempFile = new File(filePath, tempFilename);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

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
        FileUtil.mappedByteBuffer(mappedByteBuffer);
        fileChannel.close();
        raf.close();
        // 第八步
        boolean completed = checkStatus(param);
        if (completed) {
            rename(tempFile, filename);
        }
    }

    /**
     * 检查文件上传是否完成
     */
    public boolean checkStatus(MultipartFileParam param) throws Exception {
        File status = new File(fileConfig.getBigFilePath() + param.getMd5(), "status");
        RandomAccessFile raf = new RandomAccessFile(status, "rw");
        raf.setLength(param.getChunks());
        raf.seek(param.getChunk());
        raf.write(Byte.MAX_VALUE);
        byte[] bytes = FileUtils.readFileToByteArray(status);
        byte completed = Byte.MAX_VALUE;
        for (int i = 0; i < bytes.length && completed == Byte.MAX_VALUE; i++) {
            completed = (byte) (completed & bytes[i]);
        }
        raf.close();
        return completed == Byte.MAX_VALUE;
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
}
