package com.fleet.initializr.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author April Han
 */
public class BaseGenerator {

    private static String ENCODING = "UTF-8";

    private static Configuration cfg;

    {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_29);
            File file = new File(this.getClass().getResource("/templates").getPath());
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding(ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Template getTemplate(String ftl) throws IOException {
        return cfg.getTemplate(ftl, ENCODING);
    }

    protected void write(File file, String ftl, Object data) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return;
                }
            }
            if (!file.createNewFile()) {
                return;
            }
        }
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
        try {
            Template template = getTemplate(ftl);
            template.process(data, osw);
        } finally {
            osw.flush();
            osw.close();
        }
    }
}
