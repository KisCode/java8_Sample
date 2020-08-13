package com.kiscode.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/***
 * 函数接口：只包含一个抽象方法的接口，该抽象方法则被成为函数方法
 *
 * java内置四大核心函数式接口
 *     1. Consumer<T>: 消费型接口，对类型T的对象进行操作
 *     2. Supplier<T>： 数据提供
 *     3. Function<T,R>: 数据类型转换，将 T --> R
 *     4. Predicate<T> ：断言型接口确定类型为T的对象是否满足某约束，并返回 boolean 值
 */
public class FunctionInterfaceTest {

    public static void main(String[] args) {
        testPredicate();
    }

    public static void testPredicate() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "NB", "CBA", "DNF");
        filterStr(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        });

        //lambda
        filterStr(list, s -> s.equals("A"));
        filterStr(list, s -> s.length() == 2);
    }

    public static void testFunction() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<String> stringList = map(list, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "No." + integer;
            }
        });

        stringList = map(list, integer -> "No." + integer);
    }


    public static void testSupplier() {

        List<String> randomStrList = getRandomList(10, new Supplier<String>() {
            @Override
            public String get() {
                return "Number:" + Math.round(10) * 110;
            }
        });

        randomStrList = getRandomList(10, () -> "Number:" + Math.round(10) * 110);
    }

    public static void testConsumer() {

        printStringList("Java", new Consumer<String>() {
            @Override
            public void accept(String s) {
                //llll
                System.out.println(s);
            }
        });

        printStringList("Java", s -> System.out.println(s));
    }

    //Predicate<T> 断言型接口：将满足条件的字符串放入集合
    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }

    //Predicate<T> 断言型接口：将满足条件的字符串放入集合
    public static List<String> map(List<Integer> list, Function<Integer, String> function) {
        List<String> newList = new ArrayList<>();
        for (Integer i : list) {
            String str = function.apply(i);
            newList.add(str);
        }
        return newList;
    }

    public static List<String> getRandomList(int num, Supplier<String> supplier) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            newList.add(supplier.get());
        }
        return newList;
    }

    public static void printStringList(String string, Consumer<String> consumer) {
        consumer.accept(string);
    }

}