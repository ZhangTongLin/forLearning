package com.kaishengit.hibernate;

import com.kaishengit.pojo.Movie;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tonglin
 */
public class HibernateLifeTestCase {

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
    public void freeTest() {

        Movie movie = (Movie) session.get(Movie.class,176);
        //System.out.println(movie);
        System.out.println(movie == null);
    }

    @Test
    public void test1() {
        Movie movie = (Movie) session.load(Movie.class,171);
        //System.out.println(movie);

        //session.getTransaction().commit();
        System.out.println(movie);
    }

    @Test
    public void test2(){
        Movie movie = new Movie();
        movie.setTitle("testt");
        movie.setDirector("testee");
        //session.save(movie);
        session.persist(movie);
        System.out.println(movie.getId());
    }


}
