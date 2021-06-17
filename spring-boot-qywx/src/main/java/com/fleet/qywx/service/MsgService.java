package com.fleet.qywx.service;

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
}
