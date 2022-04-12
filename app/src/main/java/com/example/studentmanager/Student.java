package com.example.studentmanager;

import java.io.Serializable;

public class Student implements Serializable {
    private final String name;
    private final int age;
    private final String sex;

    public Student(String name, int age, String sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    String getName () {
        return this.name;
    }

    int getAge () {
        return this.age;
    }

    String getSex () {
        return this.sex;
    }
}
