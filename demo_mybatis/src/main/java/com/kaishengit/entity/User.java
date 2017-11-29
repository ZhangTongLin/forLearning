package com.kaishengit.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public class User {

    private int id;
    private String userName;
    private String userAddress;
    private String userTel;
    private int deptId;
    private Department dept;
    private List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userTel='" + userTel + '\'' +
                ", depId=" + deptId +
                ", department=" + dept +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int depId) {
        this.deptId = depId;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department department) {
        this.dept = department;
    }
}
