package com.fleet.qywx.service;

import java.util.List;
import java.util.Map;

/**
 * 消息发送
 *
 * @author April Han
 */
public interface MsgService {

    /**
     * 文本消息
     *
     * @param touser  指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty 指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag   指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param content 消息内容，最长不超过2048个字节，超过将截断（支持id转译）
     */
    boolean sendTextMsg(String touser, String toparty, String totag, String content);

    /**
     * 文本消息
     *
     * @param touser       指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty      指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag        指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param imageMediaId 图片媒体文件id，可以调用上传临时素材接口获取
     */
    boolean sendImageMsg(String touser, String toparty, String totag, String imageMediaId);

    /**
     * 语音消息
     *
     * @param touser       指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty      指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag        指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param voiceMediaId 语音文件id，可以调用上传临时素材接口获取
     */
    boolean sendVoiceMsg(String touser, String toparty, String totag, String voiceMediaId);

    /**
     * 视频消息
     *
     * @param touser       指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty      指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag        指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param videoMediaId 视频媒体文件id，可以调用上传临时素材接口获取
     * @param title        视频消息的标题，不超过128个字节，超过会自动截断
     * @param description  视频消息的描述，不超过512个字节，超过会自动截断
     */
    boolean sendVideoMsg(String touser, String toparty, String totag, String videoMediaId, String title, String description);

    /**
     * 文件消息
     *
     * @param touser  指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty 指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag   指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param mediaId 图片媒体文件id，可以调用上传临时素材接口获取
     */
    boolean sendFileMsg(String touser, String toparty, String totag, String mediaId);

    /**
     * 文本卡片消息
     *
     * @param touser      指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty     指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag       指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param title       标题，不超过128个字节，超过会自动截断（支持id转译）
     * @param description 描述，不超过512个字节，超过会自动截断（支持id转译）
     * @param url         点击后跳转的链接。最长2048字节，请确保包含了协议头(http/https)
     */
    boolean sendTextCardMsg(String touser, String toparty, String totag, String title, String description, String url);

    /**
     * 图文消息
     *
     * @param touser   指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty  指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag    指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param articles 图文消息，一个图文消息支持1到8条图文
     */
    boolean sendNewsMsg(String touser, String toparty, String totag, List<Map<String, Object>> articles);

    /**
     * markdown消息
     *
     * @param touser  指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty 指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag   指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param content markdown内容，最长不超过2048个字节，必须是utf8编码
     */
    boolean sendMarkdownMsg(String touser, String toparty, String totag, String content);

    /**
     * 任务卡片消息（未测试）
     *
     * @param touser      指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为“@all”，则向该企业应用的全部成员发送
     * @param toparty     指定接收消息的部门，部门ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param totag       指定接收消息的标签，标签ID列表（多个接收者用‘|’分隔，最多支持100个）。当touser为“@all”时忽略本参数
     * @param title       标题，不超过128个字节，超过会自动截断（支持id转译）
     * @param description 描述，不超过512个字节，超过会自动截断（支持id转译）
     * @param url         点击后跳转的链接。最长2048字节，请确保包含了协议头(http/https)
     * @param taskId      任务id，同一个应用发送的任务卡片消息的任务id不能重复，只能由数字、字母和“_-@”组成，最长支持128字节
     * @param btn         按钮列表，按钮个数为1~2个
     */
    boolean sendInteractiveTaskCardMsg(String touser, String toparty, String totag, String title, String description, String url, String taskId, List<Map<String, Object>> btn);
}
