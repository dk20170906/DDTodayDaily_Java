package com.dingding.dddaily.domain;

import javax.persistence.*;

@Entity
@Table(name = "dd_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //部门id
private Long depId;
//部门名称
private String depName;
//父部门id，根部门为1
private Long parentId;
//true表示是false表示不是
private boolean createDeptGroup;
//当群已经创建后，是否有新人加入部门会自动加入该群,
//true表示是
//false表示不是
private boolean autoAddUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isCreateDeptGroup() {
        return createDeptGroup;
    }

    public void setCreateDeptGroup(boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public boolean isAutoAddUser() {
        return autoAddUser;
    }

    public void setAutoAddUser(boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
    }
}
