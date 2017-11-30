package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Tonglin
 */

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "student_name")
    private String studentName;
    @ManyToMany
    @JoinTable(name = "student_teacher",
                joinColumns = {@JoinColumn(name = "s_id")},
                inverseJoinColumns = {@JoinColumn(name = "t_id")})
    private Set<Teacher> teacherSet;

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
