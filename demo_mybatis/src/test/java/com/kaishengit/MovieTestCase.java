package com.kaishengit;

import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/10/26.
 */
public class MovieTestCase {

    SqlSession sqlSession;
    MovieMapper movieMapper;
    @Before
    public void init() {
        sqlSession = GetSqlSessionFactory.openSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void findMovieByTitle() {
        String title = "%老%";
        List<Movie> movieList = movieMapper.findByTitle(title);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void findByParamsTest() {
        Map<String,Object> map = new HashMap<>();
        //map.put("title","%老%");
        map.put("director","李四");
        List<Movie> movieList = movieMapper.findByParams(map);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void findByIdListTest() {
        List idList = Arrays.asList(1,2,3);
        List<Movie> movieList = movieMapper.findByIdList(idList);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    public void batchSaveTest() {
        Movie movie = new Movie();
        movie.setTitle("aa");
        movie.setDirector("aa");
        Movie movie1 = new Movie();
        movie1.setTitle("bb");
        movie1.setDirector("bb");
        Movie movie3 = new Movie();
        movie3.setTitle("cc");
        movie3.setDirector("cc");
        List<Movie> movieList = Arrays.asList(movie,movie1,movie3);
        int a = movieMapper.batchSave(movieList);
        sqlSession.commit();
        System.out.println("受影响行数>>>>>>>>>>>>>>>>" + a);
    }
}
