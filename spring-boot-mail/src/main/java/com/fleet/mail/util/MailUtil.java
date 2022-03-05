package com.fleet.mail.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 邮件工具类
 *
 * @author April Han
 */
public class MailUtil {

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private SpringTemplateEngine springTemplateEngine;

    /**
     * 发送文本邮件
     */
    public void textMail(String from, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void textMail(String from, String personal, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        simpleMail(from, personal, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void textMail(String from, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void textMail(String from, String personal, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        simpleMail(from, personal, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void textMail(String from, String[] tos, String subject, String text) throws Exception {
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void textMail(String from, String personal, String[] tos, String subject, String text) throws Exception {
        simpleMail(from, personal, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, String personal, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        simpleMail(from, personal, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, String personal, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        simpleMail(from, personal, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, String[] tos, String subject, String text) throws Exception {
        simpleMail(from, null, tos, subject, text);
    }

    /**
     * 发送文本邮件
     */
    public void simpleMail(String from, String personal, String[] tos, String subject, String text) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (StringUtils.isNotEmpty(personal)) {
            helper.setFrom(from, personal);
        } else {
            helper.setFrom(from);
        }
        helper.setTo(tos);
        helper.setSubject(subject);
        helper.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        htmlMail(from, null, tos, subject, text);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, String personal, String to, String subject, String text) throws Exception {
        String[] tos = {to};
        htmlMail(from, personal, tos, subject, text);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        htmlMail(from, null, tos, subject, text);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, String personal, List<String> toList, String subject, String text) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        htmlMail(from, personal, tos, subject, text);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, String[] tos, String subject, String text) throws Exception {
        htmlMail(from, null, tos, subject, text);
    }

    /**
     * 发送html邮件
     */
    public void htmlMail(String from, String personal, String[] tos, String subject, String text) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (StringUtils.isNotEmpty(personal)) {
            helper.setFrom(from, personal);
        } else {
            helper.setFrom(from);
        }
        helper.setTo(tos);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, String to, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = {to};
        attachmentMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, String personal, String to, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = {to};
        attachmentMail(from, personal, tos, subject, text, files);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, List<String> toList, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        attachmentMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, String personal, List<String> toList, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        attachmentMail(from, personal, tos, subject, text, files);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, String[] tos, String subject, String text, Map<String, File> files) throws Exception {
        attachmentMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送带附件的邮件
     */
    public void attachmentMail(String from, String personal, String[] tos, String subject, String text, Map<String, File> files) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (StringUtils.isNotEmpty(personal)) {
            helper.setFrom(from, personal);
        } else {
            helper.setFrom(from);
        }
        helper.setTo(tos);
        helper.setSubject(subject);
        helper.setText(text, true);
        if (files != null) {
            files.forEach((k, v) -> {
                // 加载文件资源，作为附件
                FileSystemResource fileSystemResource = new FileSystemResource(v);
                try {
                    // 添加附件
                    helper.addAttachment(k, fileSystemResource);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        javaMailSender.send(message);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, String to, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = {to};
        inlineMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, String personal, String to, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = {to};
        inlineMail(from, personal, tos, subject, text, files);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, List<String> toList, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        inlineMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, String personal, List<String> toList, String subject, String text, Map<String, File> files) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        inlineMail(from, personal, tos, subject, text, files);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, String[] tos, String subject, String text, Map<String, File> files) throws Exception {
        inlineMail(from, null, tos, subject, text, files);
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     */
    public void inlineMail(String from, String personal, String[] tos, String subject, String text, Map<String, File> files) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (StringUtils.isNotEmpty(personal)) {
            helper.setFrom(from, personal);
        } else {
            helper.setFrom(from);
        }
        helper.setTo(tos);
        helper.setSubject(subject);
        helper.setText(text, true);
        if (files != null) {
            files.forEach((k, v) -> {
                FileSystemResource fileSystemResource = new FileSystemResource(v);
                try {
                    // 添加静态资源
                    helper.addInline(k, fileSystemResource);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        javaMailSender.send(message);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, String to, String subject, String template, Map<String, Object> variables) throws Exception {
        String[] tos = {to};
        templateMail(from, null, tos, subject, template, variables);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, String personal, String to, String subject, String template, Map<String, Object> variables) throws Exception {
        String[] tos = {to};
        templateMail(from, personal, tos, subject, template, variables);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, List<String> toList, String subject, String template, Map<String, Object> variables) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        templateMail(from, null, tos, subject, template, variables);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, String personal, List<String> toList, String subject, String template, Map<String, Object> variables) throws Exception {
        String[] tos = toList.toArray(new String[0]);
        templateMail(from, personal, tos, subject, template, variables);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, String[] tos, String subject, String template, Map<String, Object> variables) throws Exception {
        templateMail(from, null, tos, subject, template, variables);
    }

    /**
     * 发送模板邮件
     */
    public void templateMail(String from, String personal, String[] tos, String subject, String template, Map<String, Object> variables) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (StringUtils.isNotEmpty(personal)) {
            helper.setFrom(from, personal);
        } else {
            helper.setFrom(from);
        }
        helper.setTo(tos);
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariables(variables);
        String text = springTemplateEngine.process(template, context);
        helper.setText(text, true);
        javaMailSender.send(message);
    }
}
