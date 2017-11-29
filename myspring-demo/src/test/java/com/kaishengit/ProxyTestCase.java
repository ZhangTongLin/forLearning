package com.kaishengit;

import com.kaishengit.proxy.Dell;
import com.kaishengit.proxy.Lenovo;
import com.kaishengit.proxy.Sales;
import com.kaishengit.proxy.jdk.MyInvocationHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/10/29.
 */
public class ProxyTestCase {

    @Test
/*
    public void proxyTest() {
        Dell dell = new Dell();
        Proxy proxy = new Proxy(dell);
        proxy.sale();
    }
*/

    public void userServiceTest() {

        Lenovo lenovo = new Lenovo();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(lenovo);

        Sales sales = (Sales) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),
                lenovo.getClass().getInterfaces(),
                myInvocationHandler);

        System.out.println(Proxy.isProxyClass(sales.getClass()));

        sales.sale();
    }
}
