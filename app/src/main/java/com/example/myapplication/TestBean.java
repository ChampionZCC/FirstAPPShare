package com.example.myapplication;

/**
 * @description:
 * @author: ZhouCong
 * @email: 13328023455@163.com
 * @date :   2020/7/10 9:37 AM
 */
public class TestBean {
    private String name;
    private int age;
    private int grade;

    public TestBean(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name == null ? "" : name;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
