package com.kaishengit.test;

/**
 * Created by Administrator on 2017/10/31.
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 1; i < 10 ; i++) {
            System.out.println();
            for (int j = 1; j < i+1 ;j++) {
                System.out.print("" + j + "" + " *" +" "+ i + " " + "=" + j*i + "  ");
            }
        }
    }

}
