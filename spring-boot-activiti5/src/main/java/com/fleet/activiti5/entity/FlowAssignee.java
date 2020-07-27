package com.fleet.activiti5.entity;

public class FlowAssignee {

    private String sid;

    private String assignee;

    private String rolegroup;

    private String activitiname;

    private Integer assigneetype;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRolegroup() {
        return rolegroup;
    }

    public void setRolegroup(String rolegroup) {
        this.rolegroup = rolegroup;
    }

    public String getActivitiname() {
        return activitiname;
    }

    public void setActivitiname(String activitiname) {
        this.activitiname = activitiname;
    }

    public Integer getAssigneetype() {
        return assigneetype;
    }

    public void setAssigneetype(Integer assigneetype) {
        this.assigneetype = assigneetype;
    }
}
