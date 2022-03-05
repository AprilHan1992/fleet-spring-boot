package com.fleet.ureport2.config;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.fleet.ureport2.dao.Ureport2FileDao;
import com.fleet.ureport2.entity.Ureport2File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "ureport2.report.provider")
public class ReportProviderImpl implements ReportProvider {

    private static final String NAME = "ureport2-report-provider";

    // 特定文件前缀
    private String prefix = "ureport2-";

    // 是否禁用
    private boolean disabled;

    @Resource
    private Ureport2FileDao ureport2FileDao;

    @Override
    public InputStream loadReport(String s) {
        Ureport2File ureport2File = ureport2FileDao.getUreport2FileByName(getFileName(s));
        byte[] content = ureport2File.getContent();
        return new ByteArrayInputStream(content);
    }

    @Override
    public void deleteReport(String s) {
        ureport2FileDao.deleteUreport2FileByName(getFileName(s));
    }

    @Override
    public List<ReportFile> getReportFiles() {
        List<Ureport2File> list = ureport2FileDao.getUreport2FileList();
        List<ReportFile> reportList = new ArrayList<>();
        for (Ureport2File ureport2File : list) {
            reportList.add(new ReportFile(ureport2File.getName(), ureport2File.getUpdateTime()));
        }
        return reportList;
    }

    @Override
    public void saveReport(String s, String s1) {
        s = getFileName(s);
        Ureport2File ureport2File = ureport2FileDao.getUreport2FileByName(s);
        Date date = new Date();
        if (ureport2File == null) {
            ureport2File = new Ureport2File();
            ureport2File.setName(s);
            ureport2File.setContent(s1.getBytes());
            ureport2File.setCreateTime(date);
            ureport2File.setUpdateTime(date);
            ureport2FileDao.insertUreport2File(ureport2File);
        } else {
            ureport2File.setContent(s1.getBytes());
            ureport2File.setUpdateTime(date);
            ureport2FileDao.updateUreport2File(ureport2File);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean disabled() {
        return disabled;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    /**
     * 获取没有前缀的文件名
     */
    private String getFileName(String name) {
        if (name.startsWith(prefix)) {
            name = name.substring(prefix.length());
        }
        return name;
    }
}
