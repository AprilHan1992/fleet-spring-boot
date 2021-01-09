package com.fleet.mybatis.dynamic.sql.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;
import java.util.Set;

/**
 * 自定义注释生成器
 *
 * @author April Han
 */
public class CommentGenerator extends DefaultCommentGenerator {

    // 是否生成注释
    private boolean suppressAllComments = false;
    // 是否生成日期
    private boolean suppressDate = false;
    // 是否添加数据表中的注释
    private boolean addRemarkComments = false;

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
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments || !addRemarkComments) {
            return;
        }

        topLevelClass.addJavaDocLine("/**");

        String remarks = introspectedTable.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            String[] lines = remarks.split(System.getProperty("line.separator"));
            for (String line : lines) {
                topLevelClass.addJavaDocLine(" * " + line);
            }
            topLevelClass.addJavaDocLine(" *");
        }

        topLevelClass.addJavaDocLine(" * @author April Han");
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
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
        if (StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            String[] lines = remarks.split(System.getProperty("line.separator"));
            for (String line : lines) {
                field.addJavaDocLine(" * " + line);
            }
        } else {
            field.addJavaDocLine(" *");
        }

        field.addJavaDocLine(" */");
    }
}
