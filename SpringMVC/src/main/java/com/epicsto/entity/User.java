package com.epicsto.entity;

import javax.persistence.*;

/**
 * Created by anandkumar on 16/5/17.
 */
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "user_name")
    private String userName ;

    @Column(name = "user_phone")
    private String userPhone ;

    @Column(name = "user_email")
    private String userEmail ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
