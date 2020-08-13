package com.kiscode.lambda;


import com.kiscode.lambda.model.Person;
import com.kiscode.lambda.strategy.AgeCheckPerson;
import com.kiscode.lambda.strategy.CheckPerson;
import com.kiscode.lambda.strategy.MailCheckPerson;

import java.util.List;
import java.util.function.Predicate;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/8/13 21:10
 */


public class PersonFilter {
    public static void main(String[] args) {
        List<Person> personList = Person.createRoster();
        printPersonsOlderThan(personList, 20);
        System.out.println("---------------------------------------");
        printPersonsFilterGender(personList, Person.Sex.MALE);
        System.out.println("---------------------------------------");
        printPersonsByCheck(personList, new AgeCheckPerson());
        System.out.println("---------------------------------------");
        printPersonsByCheck(personList, new MailCheckPerson());
//        printPersonsByCheck(personList, person -> false);
        System.out.println("---------------------------------------");
        printPersonsWithPredicate(personList, new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() > 20;
            }
        });
        System.out.println("-----------------lambda----------------------");
        printPersonsWithPredicate(personList, person -> person.getAge() > 20);
        //通过lambda自由进行组合
        printPersonsWithPredicate(personList, person -> person.getAge() > 20 && person.getAge() < 33);
        printPersonsWithPredicate(personList, person -> person.getAge() > 20 && person.getGender() == Person.Sex.MALE);
        printPersonsWithPredicate(personList, person -> person.getAge() > 20 && person.getGender() == Person.Sex.MALE && person.getEmailAddress().contains("gmail"));
        printPersonsWithPredicate(personList, person -> person.getAge() > 20 && person.getGender() == Person.Sex.FEMALE && person.getBirthday().isLeapYear());
    }

    /**
     * 过滤并输出大于指定年龄的 对象
     * 匹配符合某一特征的成员的方法
     *
     * @param personList
     * @param age        年龄
     */
    public static void printPersonsOlderThan(List<Person> personList, int age) {
        for (Person p : personList) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    /**
     * 2. 过滤并输出大于指定性别的 对象
     *
     * @param personList 人员列表
     * @param gender     指定性别
     */
    public static void printPersonsFilterGender(List<Person> personList, Person.Sex gender) {
        for (Person p : personList) {
            if (p.getGender() == gender) {
                p.printPerson();
            }
        }
    }

    /**
     * 3. 过滤并输出大于指定性别的 对象
     *
     * @param personList 人员列表
     * @param gender     指定性别
     */
    public static void printPersons(List<Person> personList, Person.Sex gender) {
        for (Person p : personList) {
            if (p.getGender() == gender) {
                p.printPerson();
            }
        }
    }

    /**
     * 4. 本地类实现过滤需求，不同CheckPerson实现类 完成不同搜索需求，代码解耦，同时代码量增加
     *
     * @param personList 人员列表
     * @param check      指定性别
     */
    public static void printPersonsByCheck(List<Person> personList, CheckPerson check) {
        for (Person p : personList) {
            if (check.check(p)) {
                p.printPerson();
            }
        }
    }


    /***
     * 5. 通过Predicate替代 CheckPerson接口方式
     * @param personList
     * @param predicate
     */
    public static void printPersonsWithPredicate(List<Person> personList, Predicate<Person> predicate) {
        for (Person person : personList) {
            if (predicate.test(person)) {
                person.printPerson();
            }
        }
    }

}
