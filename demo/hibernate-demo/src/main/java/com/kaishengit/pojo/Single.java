package com.kaishengit.pojo;

/**
 * @author Tonglin
 */
public class Single{

    //private static Single single = new Single();

    //private Single() {}

    /*public static Single getSingle() {
        return single;
    }*/

    private static Article article = createSingle();

    private static Article createSingle() {
        return new Article();
    }


    public static Article getSingle() {
        return article;
    }

}
