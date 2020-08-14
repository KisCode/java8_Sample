package com.kiscode.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
//        testListStream();

//        testStreamMap();

//        testStreamSort();

//        testStreamMatch();

        testStreamCollect();
    }

    /***
     * stream 筛选与切片函数
     * 1. filter 过滤
     * 2. distinct 去重
     * 3. limit 限制个数
     * 4. skip 跳过前n个
     */
    public static void testListStream() {
        String[] arr = {"A", "B", "C", "D", "NB", "CBA", "DNF", "ALSDKJF", "ASDI", "F", "C", "D",};
        //通过 stream 过滤数组中长度为1的对象、去重、遍历打印
        Arrays.stream(arr)
                .filter(s -> s.length() == 1)
                .distinct()
                .forEach(s -> System.out.println(s));

        System.out.println("---------------------------------------");
        List<String> strList = Arrays.asList(arr);
        strList.stream()
                .limit(5)  //选取前5个
                .skip(2)  //跳过前2个
                .forEach(s -> System.out.println(s));
        System.out.println("---------------------------------------");


    }

    /***
     * stream 映射
     * 1. map 将一个元素映射为一个新类型的元素
     * 2. flatMap 将一个元素映射为一个新的Stream
     */
    public static void testStreamMap() {
        String[] arr = {"A", "B", "C", "D", "NB", "CBA", "DNF", "ALSDKJF", "ASDI", "F", "C", "D",};
        //通过 stream 过滤数组中长度为1的对象、去重、遍历打印
        Arrays.stream(arr)
                .map(s -> s + "---" + s + "---" + s)
                .forEach(s -> System.out.println(s));

        System.out.println("---------------------------------------");
        List<String> strList = Arrays.asList(arr);
        strList.stream()
                .mapToDouble(new ToDoubleFunction<String>() {
                    @Override
                    public double applyAsDouble(String value) {
                        return value.length();
                    }
                })
                .forEach(s -> System.out.println(s));
        System.out.println("---------------------------------------");

        strList.stream()
                .flatMap(new Function<String, Stream<?>>() {
                    @Override
                    public Stream<?> apply(String s) {
                        return null;
                    }
                });

    }

    /***
     * stream 排序
     * 1. sorted 按自然顺序排序
     * 2. sorted(Comparator comp) 按比较器进行排序
     */
    public static void testStreamSort() {
        String[] arr = {"A", "B", "C", "D", "NB", "CBA", "DNF", "ALSDKJF", "ASDI", "F", "C", "D",};
        Arrays.stream(arr)
                .sorted()  //按自然顺序排序
                .forEach(s -> System.out.println(s));

        System.out.println("---------------------------------------");
        Arrays.asList(arr)
                .stream()
                .distinct()
                .sorted()
                /*
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                    //按照长度从小到达进行排序
                        return o1.length() - o2.length();
                    }
                })
                */
                .sorted((o1, o2) -> o1.length() - o2.length())
                .forEach(s -> System.out.println(s));
    }

    /***
     * stream match
     * 1. allMatch 是否全部满足
     * 2. anyMatch 是否任一一个满足
     * 3. noneMatch 没有一个满足
     * 4. findFirst 返回流中第一个元素
     * 5. findAny 返回流中任一元素
     * 6. count 流中元素个数
     * 7. min 最小值
     * 8. max 最大值
     */
    public static void testStreamMatch() {
        String[] arr = {"A", "B", "C", "D", "NB", "CBA", "DNF", "Jordan", "ASDI", "F", "C", "D",};
        boolean isAllMatch = Arrays.stream(arr)
                .allMatch(s -> s.length() >= 1 && s.length() <= 5);
        System.out.println(isAllMatch);

        System.out.println("---------------------------------------");

        boolean isAnyMatch = Arrays.stream(arr)
                .anyMatch(s -> s.length() == 4);
        System.out.println("isAnyMatch:" + isAnyMatch);

        System.out.println("---------------------------------------");

        boolean isNoneMatch = Arrays.stream(arr)
                .noneMatch(s -> s.length() == 4);
        System.out.println("isNoneMatch:" + isNoneMatch);


        System.out.println("---------------------------------------");
        Optional<String> firstStr = Arrays.stream(arr)
                .findFirst(); //返回流中第一个元素
        Optional<String> anyStr = Arrays.stream(arr)
                .findAny();  //返回流中任一元素
        System.out.println(firstStr.get() + "---" + anyStr.get());

        System.out.println("---------------------------------------");

        //返回流中元素个数
        long count = Arrays.stream(arr).count();

        System.out.println("---------------------------------------");
        //获取最大值 最小值
        int[] numberArr = {3, 4, 1, 5, 9, 11, 33, 22, 10, 45, 675, 0};
        int minNumber = Arrays.stream(numberArr).min().getAsInt();
        int maxNumber = Arrays.stream(numberArr).max().getAsInt();
        System.out.println(minNumber + "---" + maxNumber);

        Optional<String> minLenStr = Arrays.stream(arr)
                /*
                         .min(new Comparator<String>() {
                             @Override
                             public int compare(String o1, String o2) {
                                 //按长度进行排序
                                 return o1.length() - o2.length();
                             }
                         })
                         */
                .min((o1, o2) -> o1.length() - o2.length());

        Optional<String> maxLenStr = Arrays.stream(arr)
                .max((o1, o2) -> o1.length() - o2.length());
        System.out.println(minLenStr + "---" + maxLenStr);


    }


    /**
     * stream collect
     * 1.  collect(Collectors.toList()) 将stream转为List
     * 2.  collect(Collectors.toSet()) 将stream转为Set
     * 3.  collect(Collectors.toCollection()) 将stream转为Collection
     * 4.  collect(Collectors.joining()) 将stream中所有元素拼接成为一个元素
     */
    public static void testStreamCollect() {

        String[] arr = {"A", "B", "C", "DD", "NB", "CBA", "DNF", "Jordan", "ASDIB", "FXX", "CDDD", "DA",};
        List<String> list = Arrays.stream(arr).collect(Collectors.toList());
        Set<String> set = Arrays.stream(arr).collect(Collectors.toSet());

        Collection<String> collection = Arrays.stream(arr).collect(Collectors.toCollection(ArrayList::new));

        //collect(Collectors.joining()) 将stream中所有元素拼接成为一个元素
        String joinStr = Arrays.stream(arr).collect(Collectors.joining());
        System.out.println(joinStr);

        long count = Arrays.stream(arr).collect(Collectors.counting());

        //
//        Integer sumInt = Arrays.stream(arr).collect(Collectors.summingInt(String::length));
        Integer sumInt = Arrays.stream(arr).mapToInt(String::length).sum();

        System.out.println("---------------------------------------");
        Map<Integer, List<String>> map = Arrays.stream(arr).collect(Collectors.groupingBy(String::length));
        map.forEach(new BiConsumer<Integer, List<String>>() {
            @Override
            public void accept(Integer integer, List<String> strings) {
                System.out.println("---------------- key:" + integer);
                strings.stream().forEach(s -> System.out.println(s));
            }
        });

        //简写
        Arrays.stream(arr).collect(Collectors.groupingBy(String::length))
                .forEach((integer, strings) -> {
                    System.out.println("---------------- key:" + integer);
                    strings.stream().forEach(s -> System.out.println(s));
                });
    }
}