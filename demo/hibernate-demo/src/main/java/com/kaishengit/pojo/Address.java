package com.kaishengit.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String address;
    private String street;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
