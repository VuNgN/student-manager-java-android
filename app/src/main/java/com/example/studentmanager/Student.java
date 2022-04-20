package com.example.studentmanager;

import java.io.Serializable;

/**
 * Khai báo đối tượng Student
 * name là tên của Student
 * age là tuổi của Student
 * sex là giới tính của Student
 */
public class Student implements Serializable {
    private final Integer id;
    private String name;
    private int age;
    private String sex;
    private boolean isChecked;

    public Student(Integer id, String name, int age, String sex, boolean isChecked) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isChecked = isChecked;
    }

    public Student(Integer id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    String getName() {
        return this.name;
    }

    int getAge() {
        return this.age;
    }

    String getSex() {
        return this.sex;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
