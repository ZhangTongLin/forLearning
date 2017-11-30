package com.kaishengit.hibernate;

import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Person;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.*;
import org.junit.Test;

/**
 * @author Tonglin
 */
public class LockTest {

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
    public void save() {
        Customer customer = new Customer();
        customer.setName("联想电脑2");
        session.save(customer);
    }

    @Test
    public void update() throws InterruptedException {
        Customer customer = (Customer) session.get(Customer.class,"40288195600ae20701600ae209850000");
        System.out.println(customer.getName());
        customer.setName("thinkpad-3");
        Thread.sleep(5000);
    }


    @Test
    public void test() throws InterruptedException {
        final Person person = (Person) session.get(Person.class,2, LockOptions.UPGRADE);

        person.setName("张三");

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session2 = HibernateUtil.getSession();
                session2.getTransaction().begin();

                Person person2 = (Person) session2.get(Person.class,2);

                person2.setName("李四");

                session2.getTransaction().commit();
            }
        });
        thread.start();

        Thread.sleep(5000);
    }

}
