package com.kaishengit.dao;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Tonglin
 */

public abstract class BaseDao<T,PK extends Serializable> {

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> entityClazz;

    public BaseDao() {
        ParameterizedType parameterizedType = (ParameterizedType) this
                .getClass().getGenericSuperclass();
        entityClazz = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }

    public T findById(PK id) {
        return (T) getSession().get(entityClazz,id);
    }

    public void deleteById(PK id) {
        getSession().delete(findById(id));
    }

    public List<Kaola> findAll() {
        Criteria criteria = getSession().createCriteria(entityClazz);
        criteria.setFirstResult(0);
        criteria.setMaxResults(100);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    public List<T> findByQuery(List<QueryList> queryList) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        iterationQueryList(queryList, criteria);
        return criteria.list();
    }

    private Criterion getCriterion(String condition,String parameter,Object value) {

        if (parameter.contains("_or_")) {
            String[] params = parameter.split("_or_");
            Disjunction disjunction = Restrictions.disjunction();
            for (String name : params) {
                disjunction.add(getQueryCriterion(condition,name,value));
            }
            return disjunction;
        } else {
            return getQueryCriterion(condition,parameter,value);
        }
    }

    private Criterion getQueryCriterion(String condition,String parameter,Object value) {
        if ("eq".equalsIgnoreCase(condition)) {
            return Restrictions.eq(parameter,value);
        } else if ("gt".equalsIgnoreCase(condition)) {
            return Restrictions.gt(parameter,value);
        } else if ("lt".equalsIgnoreCase(condition)) {
            return Restrictions.lt(parameter,value);
        } else if ("like".equalsIgnoreCase(condition)) {
            return Restrictions.like(parameter, (String) value, MatchMode.ANYWHERE);
        } else if ("ge".equalsIgnoreCase(condition)) {
            return Restrictions.ge(parameter,value);
        } else if ("le".equalsIgnoreCase(condition)) {
            return Restrictions.le(parameter,value);
        }
        return null;
    }

    public Long count() {

        Criteria criteria = getSession().createCriteria(entityClazz);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public Long count(List<QueryList> queryList) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        iterationQueryList(queryList, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public Page<T> queryForPage(List<QueryList> queryList, Integer pageNum) {

        Criteria criteria = getSession().createCriteria(entityClazz);
        iterationQueryList(queryList, criteria);

        Page<T> page = new Page<T>(15,pageNum,count(queryList).intValue());

        criteria.setFirstResult(page.getStartNum());
        criteria.setMaxResults(15);
        List<T> resultList =  criteria.list();

        page.setItems(resultList);
        return page;
    }

    private void iterationQueryList(List<QueryList> queryList, Criteria criteria) {
        for (QueryList query : queryList) {
            criteria.add(getCriterion(query.getQueryCondition(),
                    query.getParameter(),
                    query.getQueryValue()));
        }
    }
}
