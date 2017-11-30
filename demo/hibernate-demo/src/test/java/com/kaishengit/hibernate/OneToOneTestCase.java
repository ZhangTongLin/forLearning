package com.kaishengit.hibernate;

import com.kaishengit.pojo.Article;
import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Content;
import com.kaishengit.pojo.People;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tonglin
 */
public class OneToOneTestCase {

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
    public void find() {
//        Card card = (Card) session.get(Card.class,1);
//        System.out.println(card.getId());
//
//        People people = card.getPeople();
//        System.out.println(people.getName());

        People people = (People) session.get(People.class,2);
        System.out.println(people.getName());

        System.out.println(people.getCard().getAddress());

    }

    @Test
    public void save() {
        People people = new People();
        people.setName("王五");
        Card card = new Card();
        card.setCardNum("1441");
        card.setAddress("中国");
        card.setPeople(people);

        people.setCard(card);

        session.save(card);
        session.save(people);

    }

    @Test
    public void delete() {
        People people = (People) session.get(People.class,2);

        session.delete(people);
    }

    @Test
    public void save2() {
        Article article = new Article();
        article.setTitle("诗经3");

        Content content = new Content();
        content.setContent("撒的撒的打");
        content.setArticle(article);


        article.setContent(content);

        session.save(article);
        session.save(content);

    }

    @Test
    public void find2() {
//        Article article = (Article) session.get(Article.class,2);
//        System.out.println(article.getTitle());
//        System.out.println(article.getContent().getContent());

        Content content = (Content) session.get(Content.class,2);
        System.out.println(content.getContent());
        content.getArticle().getTitle();

        //session.evict(content);
        System.out.println(session.contains(content));
        System.out.println(session.contains(content.getArticle()));
        session.getTransaction().commit();
        //System.out.println(content.getArticle().getTitle());

    }

}
