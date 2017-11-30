package com.kaishengit.hibernate;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tonglin
 */
public class EhcacheTest {

    private Session session = null;

    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }

    //@After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void cacheTest() {


        Student student = (Student) session.get(Student.class,4);
        System.out.println(student.getStudentName());

        session.getTransaction().commit();

        Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictAllRegions();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        Student student2 = (Student) session2.get(Student.class,4);
        System.out.println(student2.getStudentName());

        session2.getTransaction().commit();

    }

}
