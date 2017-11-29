package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/26.
 */
public interface MovieMapper {
    List<Movie> findByTitle(@Param("title") String title);
    List<Movie> findByParams(Map map);
    List<Movie> findByIdList(@Param("idList") List idList);
    int batchSave(@Param("movieList") List<Movie> movieList);
}
