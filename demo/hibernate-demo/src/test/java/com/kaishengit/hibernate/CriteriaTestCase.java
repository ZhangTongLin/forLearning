package com.kaishengit.hibernate;

import com.kaishengit.pojo.Movie;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.hibernate.criterion.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tonglin
 */
public class CriteriaTestCase {

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
    public void criteria() {
        Criteria criteria = session.createCriteria(Movie.class);

        //criteria.add(Restrictions.eq("id",1));

        //Movie movie = (Movie) criteria.uniqueResult();
        //criteria.add(Restrictions.like("title","老友", MatchMode.ANYWHERE));
//        criteria.add(Restrictions.eq("director","张三"));
//        criteria.add(Restrictions.like("title","老友",MatchMode.ANYWHERE));

        //criteria.add(Restrictions.in("id", Arrays.asList(1,4,5,34,21)));
        Criterion directorCriterion = Restrictions.eq("director","张三");
        Criterion titleCriterion = Restrictions.like("title","老");

        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(4);
        criteria.setMaxResults(2);
        criteria.add(Restrictions.or(directorCriterion,titleCriterion));
        List<Movie> movieList = criteria.list();
        showList(movieList);


    }

    @Test
    public void countTest() {

        Criteria criteria = session.createCriteria(Movie.class);
//        criteria.setProjection(Projections.rowCount());
//        Long integer = (Long) criteria.uniqueResult();
//        System.out.println(integer);
//        criteria.setProjection(Projections.count("title"));
//        Long integer = (Long) criteria.uniqueResult();
//        System.out.println(integer);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.count("title"));
        projectionList.add(Projections.rowCount());
        criteria.setProjection(projectionList);
        Object[] objects = (Object[]) criteria.uniqueResult();
        System.out.println("count title" + objects[0]);
        System.out.println("count(*)" + objects[1]);


    }

    @Test
    public void nativeSql() {
//        String sql = "select * from t_movie";
//        SQLQuery sqlQuery = session.createSQLQuery(sql);

        String sql = "select * from t_movie";
        SQLQuery sqlQuery = session.createSQLQuery(sql)
                            .addEntity(Movie.class);

        List<Movie> list = sqlQuery.list();
        for (Movie object : list) {
            System.out.println(object);
        }

    }

    private void showList(List<Movie> movieList) {
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

}
