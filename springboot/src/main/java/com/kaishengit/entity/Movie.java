package com.kaishengit.entity;

/**
 * @author Tonglin
 */
public class Movie {

    private Integer id;
    private String title;
    private String director;

    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
