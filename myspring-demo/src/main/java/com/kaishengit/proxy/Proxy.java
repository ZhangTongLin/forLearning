package com.kaishengit.proxy;

/**
 * Created by Administrator on 2017/10/29.
 */
public class Proxy implements Sales {

    //Lenovo lenovo = new Lenovo();
    private Sales sales;

    public Proxy(Sales sales){
        this.sales = sales;
    }

    @Override
    public void sale() {
        System.out.println("提价+++++++++++");
        sales.sale();
    }
}
