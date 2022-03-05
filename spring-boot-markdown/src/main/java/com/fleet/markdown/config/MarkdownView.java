package com.fleet.markdown.config;

import org.pegdown.PegDownProcessor;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MarkdownView extends AbstractTemplateView {

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.append("<!DOCTYPE HTML><html><body>");
        printWriter.append(html());
        printWriter.append("</body></html>");
        printWriter.flush();
        printWriter.close();
    }

    private String html() throws URISyntaxException, IOException {
        String tempPath = "templates/" + getUrl();
        URL tempUrl = MarkdownView.class.getClassLoader().getResource(tempPath);
        if (tempUrl != null) {
            Path path = Paths.get(tempUrl.toURI());
            String md = new String(Files.readAllBytes(path));
            return new PegDownProcessor().markdownToHtml(md);
        } else {
            return null;
        }
    }

    private String html1() throws IOException {
        String tempPath = "templates/" + getUrl();
        InputStream tempStream = this.getClass().getClassLoader().getResourceAsStream(tempPath);
        if (tempStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(tempStream, StandardCharsets.UTF_8));

            String line;
            StringBuilder md = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                md.append(line).append("\r\n");
            }

            PegDownProcessor pegDownProcessor = new PegDownProcessor(Integer.MAX_VALUE);
            return pegDownProcessor.markdownToHtml(md.toString());
        }
        return null;
    }
}
