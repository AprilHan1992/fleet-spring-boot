package com.fleet.qywx.service;

import java.io.File;

/**
 * 消息发送
 *
 * @author April Han
 */
public interface MediaService {

    /**
     * 上传临时素材
     *
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件（file）
     * @param media 消息内容，最长不超过2048个字节，超过将截断（支持id转译）
     */
    String upload(String type, File media);
}
