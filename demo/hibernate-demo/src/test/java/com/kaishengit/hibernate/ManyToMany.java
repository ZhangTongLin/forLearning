package com.kaishengit.hibernate;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tonglin
 */
public class ManyToMany {

    private Session session = null;

    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void findTest() {
        Student student = (Student) session.get(Student.class,2);

        Set<Teacher> teachers = student.getTeacherSet();

        for (Teacher teacher : teachers) {
            System.out.println("id>>" + teacher.getId() + "teacher>>" + teacher.getTeacherName());
        }

    }

    @Test
    public void save() {
        Student student = new Student();
        student.setStudentName("s6");

        Teacher teacher = new Teacher();
        teacher.setTeacherName("t7");

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherName("t8");

        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher);
        teacherSet.add(teacher2);
        student.setTeacherSet(teacherSet);


        session.save(teacher);
        session.save(teacher2);
        session.save(student);

    }

    @Test
    public void save2() {
        Student student = new Student();
        student.setStudentName("SSSS-1");

        Student student2 = new Student();
        student2.setStudentName("SSSS-2");

        Teacher teacher = new Teacher();
        teacher.setTeacherName("TTTT-1");

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherName("TTTT-2");

        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher);
        teacherSet.add(teacher2);

       /* Set<Student> studentSet = new HashSet<Student>();
        studentSet.add(student);
        studentSet.add(student2);

        teacher.setStudentSet(studentSet);
        teacher2.setStudentSet(studentSet);*/

        student.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        session.save(teacher);
        session.save(teacher2);
        session.save(student);
        session.save(student2);


    }

}
