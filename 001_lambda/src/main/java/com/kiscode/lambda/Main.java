package com.kiscode.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Description: java8 - lambda 函数式编程
 * 1. （行为参数化）代码块作为参数传递
 * Author: keno
 * CreateDate: 2020/8/12 22:04
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Java test 12345");
        Main main = new Main();

        main.startThread();
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

        Runnable r2 = () -> {
            System.out.println("Run:R2" + Thread.currentThread().getName());
        };
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
}