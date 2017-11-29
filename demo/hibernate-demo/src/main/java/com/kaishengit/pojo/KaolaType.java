package com.kaishengit.pojo;

import java.util.Set;

/**
 * @author Tonglin
 */
public class KaolaType {

    private Integer id;
    private String typeName;
    private Integer parentId;
    private Set<Kaola> kaolaSet;

    public Set<Kaola> getKaolaSet() {
        return kaolaSet;
    }

    public void setKaolaSet(Set<Kaola> kaolaSet) {
        this.kaolaSet = kaolaSet;
    }

    @Override
    public String toString() {
        return "KaolaType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
