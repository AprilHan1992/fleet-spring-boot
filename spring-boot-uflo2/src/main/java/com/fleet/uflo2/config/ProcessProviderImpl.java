package com.fleet.uflo2.config;

import com.bstek.uflo.console.provider.ProcessFile;
import com.bstek.uflo.console.provider.ProcessProvider;
import com.fleet.uflo2.dao.Uflo2FileDao;
import com.fleet.uflo2.entity.Uflo2File;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "uflo2.process.provider")
public class ProcessProviderImpl implements ProcessProvider {

    private static final String NAME = "uflo2-process-provider";

    // 特定文件前缀
    private String prefix = "uflo2-";

    // 是否禁用
    private boolean disabled;

    @Resource
    private Uflo2FileDao uflo2FileDao;

    @Override
    public InputStream loadProcess(String s) {
        Uflo2File uflo2File = uflo2FileDao.getUfol2FileByName(getFileName(s));
        byte[] content = uflo2File.getContent();
        return new ByteArrayInputStream(content);
    }

    @Override
    public List<ProcessFile> loadAllProcesses() {
        List<Uflo2File> list = uflo2FileDao.getUfol2FileList();
        List<ProcessFile> floList = new ArrayList<>();
        for (Uflo2File uflo2File : list) {
            floList.add(new ProcessFile(uflo2File.getName(), uflo2File.getUpdateTime()));
        }
        return floList;
    }

    @Override
    public void saveProcess(String s, String s1) {
        s = getFileName(s);
        Uflo2File uflo2File = uflo2FileDao.getUfol2FileByName(s);
        Date date = new Date();
        if (uflo2File == null) {
            uflo2File = new Uflo2File();
            uflo2File.setName(s);
            uflo2File.setContent(s1.getBytes());
            uflo2File.setCreateTime(date);
            uflo2File.setUpdateTime(date);
            uflo2FileDao.insertUfol2File(uflo2File);
        } else {
            uflo2File.setContent(s1.getBytes());
            uflo2File.setUpdateTime(date);
            uflo2FileDao.updateUfol2File(uflo2File);
        }
    }

    @Override
    public void deleteProcess(String s) {
        uflo2FileDao.deleteUfol2FileByName(getFileName(s));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public boolean support(String s) {
        return false;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
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
