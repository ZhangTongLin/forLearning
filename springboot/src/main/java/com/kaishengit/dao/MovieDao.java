package com.kaishengit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Tonglin
 */
@Repository
public class MovieDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String title,String director) {
        String sql = "insert into t_movie (title,director) values (?,?)";
        jdbcTemplate.update(sql,title,director);
    }

}
