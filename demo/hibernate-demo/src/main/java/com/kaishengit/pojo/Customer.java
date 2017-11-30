package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
public class Customer {

    @Id
    @GenericGenerator(strategy = "uuid",name = "myuuid")
    @GeneratedValue(generator = "myuuid")
    private String id;

    private String name;
    @Version
    private Integer version;

    //排除数据库连接
    @Transient
    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
