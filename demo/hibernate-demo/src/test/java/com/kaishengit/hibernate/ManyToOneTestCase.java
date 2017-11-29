package com.kaishengit.hibernate;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.Kaola;
import com.kaishengit.pojo.KaolaType;
import com.kaishengit.pojo.Person;
import com.kaishengit.pojo.uitl.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tonglin
 */
public class ManyToOneTestCase {

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
    public void manyToOne() {
        Kaola kaola = (Kaola) session.get(Kaola.class,2300);
        System.out.println(kaola.getProductName());

        KaolaType kaolaType = kaola.getKaolaType();
        System.out.println("kaolaType>>>" + kaolaType);

    }

    @Test
    public void findKaolaType() {
        //Kaola kaola = (Kaola) session.get(Kaola.class,3000);
        KaolaType kaolaType = (KaolaType) session.get(KaolaType.class,20);


        Set<Kaola> kaolaSet = kaolaType.getKaolaSet();
        for (Kaola kaola : kaolaSet) {
            System.out.println(kaola);
        }


//        Criteria criteria = session.createCriteria(Kaola.class);
//        criteria.add(Restrictions.eq("kaolaType.id",kaolaType.getId()));
//
//        List<Kaola> kaolas = criteria.list();
//
//        for (Kaola kaola : kaolas) {
//            System.out.println(kaola);
//        }

    }

    @Test
    public void save() {
        KaolaType kaolaType = (KaolaType) session.get(KaolaType.class,27);

        Kaola kaola = new Kaola();
        kaola.setPlace("ri本");
        kaola.setCommentNum(20);
        kaola.setMarketPrice(BigDecimal.valueOf(200));
        kaola.setProductName("手机");
        kaola.setKaolaType(kaolaType);

        session.save(kaola);
        Kaola kaola1 = (Kaola) session.get(Kaola.class,kaola.getId());
        System.out.println(kaola1);
    }

    @Test
    public void save1() {

        Person person = new Person();
        person.setName("json");

        Address address = new Address();
        address.setAddress("中国");
        address.setStreet("上海");
        address.setPerson(person);

        Address address2 = new Address();
        address2.setAddress("中国");
        address2.setStreet("新乡");
        address2.setPerson(person);



        session.save(address);
        session.save(address2);
        session.save(person);

    }

    @Test
    public void save2() {
        Person person = new Person();
        person.setName("小花");

        Address address = new Address();
        address.setAddress("中国");
        address.setStreet("焦作");
        address.setPerson(person);

        Address address2 = new Address();
        address2.setAddress("中国");
        address2.setStreet("永城");
        address2.setPerson(person);

//        Set<Address> addressSet = new HashSet<Address>();
//        addressSet.add(address);
//        addressSet.add(address2);
//        person.setAddressSet(addressSet);

        session.save(person);
        session.save(address);
        session.save(address2);




    }

    @Test
    public void select() {
        Address address = (Address) session.get(Address.class,3);
        System.out.println(address.getAddress());

        Person person = address.getPerson();
        System.out.println(person.getName());

    }

    @Test
    public void selectOneT0Many() {
        Person person = (Person) session.get(Person.class,3);

        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("person.id",person.getId()));
        List<Address> addresses = criteria.list();

        for (Address address : addresses) {
            System.out.println(address.getStreet());
        }

    }

    @Test
    public void selectOneToMany2() {
        Person person = (Person) session.get(Person.class,3);

        Set<Address> addressSet = person.getAddressSet();
        for (Address address : addressSet) {
            System.out.println("id>> " + address.getId() + " >> " + address.getStreet());
        }

    }

    @Test
    public void deleteTest() {
        Person person = (Person) session.get(Person.class,3);

//        Set<Address> addressSet = person.getAddressSet();
//        for (Address address : addressSet) {
//            session.delete(address);
//        }

        session.delete(person);

    }

}
