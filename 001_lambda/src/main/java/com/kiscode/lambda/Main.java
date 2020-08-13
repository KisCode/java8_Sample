package com.kiscode.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Description: java8 - lambda 函数式编程
 * 1. （行为参数化）代码块作为参数传递
 * 语法格式： (parameters) -> expression 或 (parameters) ->{ statements; }
 * <p>
 * 特点：语法更简洁、更灵活
 * <p>
 * Author: keno
 * CreateDate: 2020/8/12 22:04
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Java test 12345");
        Main main = new Main();

//        main.startThread();

        main.mapForeach();

        main.treeSetSort();
    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("CurrentThread:" + Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            System.out.println("CurrentThread lambda:" + Thread.currentThread().getName());
        }).start();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run:R1" + Thread.currentThread().getName());
            }
        };

        Runnable r2 = () ->
                System.out.println("Run:R2" + Thread.currentThread().getName());
        r1.run();
        r2.run();

        List<String> list = Arrays.asList("Java", "Python", "Kotlin", "Swift");
        for (String s : list) {
            System.out.println("for: " + s);
        }
        list.forEach(s -> {
            System.out.println("lambda: " + s);
        });

    }

    private void mapForeach() {
        /***
         * Map 遍历
         */
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "James");
        hashMap.put(2, "Kobe");
        hashMap.put(3, "KD");


        //1. 方式1 使用迭代器遍历
        Iterator<Map.Entry<Integer, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("iterator Map key: " + entry.getKey() + "\t value:" + entry.getValue());
        }

        //2. 通过foreach遍历数据
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println("foreachMap key: " + entry.getKey() + "\t value:" + entry.getValue());
        }

        //3. 通过lamdba进行遍历
        hashMap.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer key, String value) {

            }
        });

        hashMap.forEach((key, value) -> {
            System.out.println("lambda key: " + key + "\t value:" + value);
        });
    }

    private void treeSetSort() {
        TreeSet<Integer> treeSet1 = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        //使用lambda表达式实现 treeSet排序
        TreeSet<Integer> treeSet2 = new TreeSet<>((o1, o2) -> Integer.compare(o1, o2));

        TreeSet<Integer> treeSet4 = new TreeSet<>((Integer::compareTo));

        //treeSet排序
        treeSet1.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {

            }
        });

        //treeSet排序 lambda
        treeSet1.forEach(integer -> {

        });
    }

}