package com.javatpoint.entity;

import javax.persistence.*;

/**
 * Created by anand on 20/4/17.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @Column(name = "name")
    private String name ;

    @Column(name = "age")
    private int age ;

    public Student(){}; 

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
