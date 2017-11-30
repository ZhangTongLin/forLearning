package com.kaishengit.pojo;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
@Table(name = "t_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;
    private String director;

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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
