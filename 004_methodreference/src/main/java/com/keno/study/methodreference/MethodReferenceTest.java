package com.keno.study.methodreference;

import com.keno.study.methodreference.model.Person;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Description: 方法引用
 * 方法引用是指通过方法的名字来指向一个方法：方法引用的唯一用途是支持Lambda的简写(可以理解为方法引用是lambda表达式的另一种表现形式，快捷写法)
 * 使用 :: 操作符将方法名和对象或类的名字分隔开
 * 1. 类方法引用  object :: methed
 * 2. 静态方法    ClassName :: methed
 * 3.
 *
 * <p>
 * Author: keno
 * CreateDate: 2020/8/15 9:57
 */

public class MethodReferenceTest {
    public static void main(String[] args) {

        testClassMethod();

//        testStaticMethod();

//        testStaticMethod2();
//        testStaticMethod3();

    }


    private static void testClassMethod() {
        Person person = new Person("Jordan", 50);
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return person.getName();
            }
        };

        supplier = () -> person.getName();

        supplier = person::getName;

        System.out.println(supplier.get());
    }

    /***
     * 静态方法引用
     */
    private static void testStaticMethod() {
        //1. 传统写法
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        };

        //2. lambda表达式
        Function<Integer, String> function2 = integer -> String.valueOf(integer);

        //3. lambda表达式 + 方法引用（静态）
        Function<Integer, String> function3 = String::valueOf;


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().map(function3).forEach(System.out::println);
    }


    private static void testStaticMethod2() {
        String[] strArr = {"A", "B", "C", "DD", "NB", "CBA", "DNF", "Jordan", "ASDIB", "FXX", "CDDD", "DA"};
        Arrays.stream(strArr)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                });

        System.out.println("---------------------------------------");

        Arrays.stream(strArr)
                .map(s -> s.length())
                .forEach(integer ->
                        System.out.println(integer));


        System.out.println("---------------------------------------");

        Arrays.stream(strArr)
//                .map(s -> s.length())
                .map(String::length)
                .forEach(System.out::println);
    }

    private static void testStaticMethod3() {
        // 遍历 PersonArray 按年龄排序输出 age
        Person[] personArr = {
                new Person("Jordan", 50),
                new Person("Kobe", 39),
                new Person("Wade", 36),
                new Person("JAY", 40),
                new Person("Smith", 56),
                new Person("Amy", 29),
                new Person("BetaMo", 12),
        };

        // 1. 传统写法 匿名函数
        Arrays.stream(personArr)
                .map(new Function<Person, Integer>() {
                    @Override
                    public Integer apply(Person person) {
                        return person.getAge();
                    }
                })
                .sorted()
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println("age:" + integer);
                    }
                });

        //2. lambda表达式
        Arrays.stream(personArr)
                .map(person -> person.getAge())
                .sorted()
                .forEach(integer -> System.out.println("age:" + integer));

        //3. lambda + 方法引用
        Arrays.stream(personArr)
                .map(Person::getAge)
                .sorted()
                .forEach(System.out::print);
    }

    private static void testConstructor() {

        Function<Integer, Person> function = new Function<Integer, Person>() {
            @Override
            public Person apply(Integer integer) {
                return new Person();
            }
        };

        System.out.println("---------------------------------------");
        Function<Integer, Person> function2 = integer -> new Person();
        System.out.println("---------------------------------------");
        Function<Integer, Person> function3 = Person::new;


        Function<Integer, HashMap<Integer, String>> hashMapFunction = new Function<Integer, HashMap<Integer, String>>() {
            @Override
            public HashMap<Integer, String> apply(Integer integer) {
                return new HashMap<>();
            }
        };

        hashMapFunction = integer -> new HashMap<>();

        hashMapFunction = HashMap::new;
    }


}