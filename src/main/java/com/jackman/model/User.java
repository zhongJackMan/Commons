package com.jackman.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author shusheng
 * @Date 18/11/7 上午10:55
 */
public class User implements Serializable{

    private static final long serialVersionUID = -6734294753970604784L;
    private String userName;
    private String password;
    private Byte age;
    private Integer weight;
    private BigDecimal incoming;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getIncoming() {
        return incoming;
    }

    public void setIncoming(BigDecimal incoming) {
        this.incoming = incoming;
    }
}
