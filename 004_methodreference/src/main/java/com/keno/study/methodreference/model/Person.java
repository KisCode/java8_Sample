package com.keno.study.methodreference.model;


/**** 
 * Description: 
 * Author:  Administrator
 * CreateDate: 2020/8/15 10:31
 */
public class Person {
    private String name;
    private int age;

    public Person(Integer integer) {
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

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
