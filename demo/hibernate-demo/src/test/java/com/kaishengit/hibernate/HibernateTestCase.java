package com.kaishengit.hibernate;

import com.kaishengit.pojo.Movie;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

/**
 * @author Tonglin
 */
public class HibernateTestCase {

    @Test
    public void save() {
        //读取配置文件
        Configuration configuration = new Configuration().configure();
        //创建session工厂

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //创建session
        Session session = sessionFactory.getCurrentSession();
        //创建事务
        Transaction transaction = session.beginTransaction();

        //执行操作
        Movie movie = new Movie();
        movie.setTitle("空天猎");
        movie.setDirector("李晨");
        session.save(movie);

        //提交事务（会释放资源）
        transaction.commit();
    }


    @Test
    public void findTest() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Movie movie = (Movie) session.get(Movie.class,16);
        System.out.println(movie);

        transaction.commit();
    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Movie movie = (Movie) session.get(Movie.class,16);
        session.delete(movie);
        session.getTransaction().commit();

    }

    @Test
    public void update() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Movie movie = (Movie) session.get(Movie.class,17);
        movie.setDirector("周星驰");

        session.getTransaction().commit();

    }

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        //HQL
        String hql = "from Movie";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(5);
        List<Movie> movies = query.list();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }


    @Test
    public void freeTest() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Movie movie = new Movie();
        movie.setTitle("test");
        movie.setDirector("testDirector");

        session.save(movie);

        session.getTransaction().commit();
    }

    @Test
    public void freeTest1() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Movie movie = new Movie();
        movie.setTitle("test");
        movie.setDirector("testDirector");

        //session.save(movie);
        session.saveOrUpdate(movie);

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        movie.setDirector("bbb");
        //session1.update(movie);
        session1.saveOrUpdate(movie);
        session1.getTransaction().commit();
    }

    @Test
    public void freeTest2() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Movie movie = (Movie) session.get(Movie.class,18);
        movie.setTitle("荒野求生");

        session.getTransaction().commit();



    }

}
