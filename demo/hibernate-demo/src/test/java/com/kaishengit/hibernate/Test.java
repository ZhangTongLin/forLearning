package com.kaishengit.hibernate;

import com.kaishengit.pojo.Article;
import com.kaishengit.pojo.Single;
import org.junit.Assert;

/**
 * @author Tonglin
 */
public class Test {

    @org.junit.Test
    public void test() {
        Son son = new Son();
    }

    @org.junit.Test
    public void test1() {

        /*Single single = Single.getSingle();
        Single single1 = Single.getSingle();
        Single single2 = new Single();*/

        //System.out.println(single == single2);

        Article article = Single.getSingle();
        Article article1 = Single.getSingle();

        Assert.assertEquals(article,article1);
    }
}
