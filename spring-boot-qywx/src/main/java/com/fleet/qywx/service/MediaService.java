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
     * 素材上传得到media_id，该media_id仅三天内有效
     *
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件（file）
     * @param media 临时素材文件
     */
    String upload(String type, File media);

    /**
     * 上传图片
     * 上传图片得到图片URL，该URL永久有效
     *
     * @param img 图片文件
     */
    String uploadImg(File img);
}
