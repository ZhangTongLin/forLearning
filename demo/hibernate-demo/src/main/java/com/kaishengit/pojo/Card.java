package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * @author Tonglin
 */
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GenericGenerator(name = "FK",strategy = "foreign",
            parameters = {@Parameter(name = "property",value = "people")})
    @GeneratedValue(generator = "FK")
    private Integer id;
    @Column(name = "card_num")
    private String cardNum;
    private String address;
    @OneToOne(mappedBy = "card")
    @PrimaryKeyJoinColumn
    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
