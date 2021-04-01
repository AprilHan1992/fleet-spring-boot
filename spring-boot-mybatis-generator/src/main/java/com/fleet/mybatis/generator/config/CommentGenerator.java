package com.fleet.mybatis.generator.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * 自定义注释生成器
 *
 * @author April Han
 */
public class CommentGenerator extends DefaultCommentGenerator {

    /**
     * 阻止生成所有注释（true：是，false：否）
     */
    private boolean suppressAllComments = false;

    /**
     * 阻止生成日期（true：是，false：否）
     */
    private boolean suppressDate = true;

    /**
     * 是否添加数据表中的注释（true：是，false：否）
     */
    private boolean addRemarkComments = true;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void addComment(XmlElement xmlElement) {
    }

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments || !this.addRemarkComments) {
            return;
        }

        topLevelClass.addJavaDocLine("/**");

        String remarks = introspectedTable.getRemarks();
        if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
            topLevelClass.addJavaDocLine(" *");
        }

        topLevelClass.addJavaDocLine(" * @author April Han");
        topLevelClass.addJavaDocLine(" * @date " + this.sdf.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

        field.addJavaDocLine("/**");

        String remarks = introspectedColumn.getRemarks();
        if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }

        field.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable() + "." + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        if (suppressAllComments || !addRemarkComments) {
            return;
        }

        field.addJavaDocLine("/**");

        String remarks = introspectedColumn.getRemarks();
        if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        } else {
            field.addJavaDocLine(" *");
        }

        field.addJavaDocLine(" */");
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }
}
