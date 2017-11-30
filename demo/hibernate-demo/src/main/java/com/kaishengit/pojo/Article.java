package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id",unique = true)
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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
}
