package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",unique = true)
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
