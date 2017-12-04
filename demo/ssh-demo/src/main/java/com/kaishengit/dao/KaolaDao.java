package com.kaishengit.dao;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.QueryList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tonglin
 */
@Repository
public class KaolaDao extends BaseDao<Kaola,Integer> {

}
