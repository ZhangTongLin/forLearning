package com.kaishengit.pojo;

import java.math.BigDecimal;

/**
 * @author Tonglin
 */
public class Kaola {

    private Integer id;
    private String productName;
    private BigDecimal price;
    private BigDecimal marketPrice;
    private String place;
    private Integer commentNum;
    private KaolaType kaolaType;

    @Override
    public String toString() {
        return "Kaola{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", marketPrice=" + marketPrice +
                ", place='" + place + '\'' +
                ", commentNum=" + commentNum +
                ", kaolaType=" + kaolaType +
                '}';
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public KaolaType getKaolaType() {
        return kaolaType;
    }

    public void setKaolaType(KaolaType kaolaType) {
        this.kaolaType = kaolaType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String poductName) {
        this.productName = poductName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
