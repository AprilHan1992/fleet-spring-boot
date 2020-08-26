package com.fleet.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fleet.easyexcel.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
public class UserListener extends AnalysisEventListener<User> {

    List<User> list = new ArrayList<>();

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        list.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
