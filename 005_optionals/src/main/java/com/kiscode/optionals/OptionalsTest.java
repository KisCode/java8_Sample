package com.kiscode.optionals;


import com.kiscode.optionals.model.Student;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/****
 * Description: Optional类(java.util.Optional) 是一个容器类,对持有类进行包装
 * 常用API
 * 1. Optional.of(T t) 创建Optional实例对象
 * 2. Optional.ofNullable(T t) 若持有对象t=null ，则返回一个Optional.empty， 否则则返回Optional.ofNullable(T)
 * 3. isPresent() 是否持有指定对象, 返回true or false
 * 4. orElse(T t) 如果调用对象包含值，返回该值，否则返回t;可理解为Optional设置空持有时的默认值
 * 5. orElseGe(Supplier supplier) 如果调用对象包含值，返回该值，否则返回 Supplier 获取的值
 * 6. map(Function funtion) 如果有值对其处理并返回处理后的Optional，否则返回 Optional.empty()
 * 7. filter(Predicate predicate) 对当前Optional持有对象进行过滤，如满足条件则返回原Optional,否则返回Optional.empty()
 * Author:  kisCode
 * CreateDate: 2020/8/19 20:49
 */
public class OptionalsTest {
    public static void main(String[] args) {
        createOptional();
    }

    public static void createOptional() {

        Student student = new Student(33, "Kobe");
        //1.创建一个Optional实例
        Optional<Student> optionalStudent = Optional.empty();
        System.out.println(optionalStudent);

        // 创建一个持有 Student对象的 Optional实例
        optionalStudent = Optional.of(student);
        System.out.println(optionalStudent + "_" + optionalStudent.get());

        //Optional.ofNullable(T t) 若持有对象t=null ，则返回一个Optional.empty， 否则则返回Optional.ofNullable(T)
        optionalStudent = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null)  is \t" + optionalStudent);
        optionalStudent = Optional.ofNullable(student);
        System.out.println(optionalStudent);

        //isPresent 是否持有指定对象
        System.out.println("---------------------------------------");
        boolean isPresent = optionalStudent.isPresent();
        System.out.println("isPresent =" + isPresent);
        isPresent = Optional.ofNullable(null).isPresent();
        System.out.println("Optional.ofNullable(null).isPresent() =" + isPresent);

        System.out.println("---------------------------------------");

        Student student1 = new Student(23, "Keno");
        //orElse(T t) 如果调用对象包含值，返回该值，否则返回t
        Student student2 = optionalStudent.orElse(student1);
        System.out.println("Optional.ofNullable(null).orElse() =" + student2);

        //optionalStudent如果包含一个Student对象则直接返回，没有则返回student2对象，可理解为Optional设置空持有时的默认值
        Student student3 = optionalStudent.orElse(student2);
        System.out.println("Optional.ofNullable(null).orElse() =" + student3);

//        optionalStudent = Optional.empty();
        //optionalStudent.orElseGe(Supplier supplier) 如果调用对象包含值，返回该值，否则返回 Supplier 获取的值
        Student student4 = optionalStudent.orElseGet(new Supplier<Student>() {
            @Override
            public Student get() {
                return new Student(99, "Tim");
            }
        });

        student4 = optionalStudent.orElseGet(() -> new Student(99, "DefaultName"));
        System.out.println("Optional.ofNullable(null).orElseGet() =" + student3);

        //Optional.map(new Function<Student, String>) 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
        System.out.println("---------------------------------------");
        Optional<String> optionalStr = optionalStudent.map(new Function<Student, String>() {
            @Override
            public String apply(Student student) {
                return student.getName();
            }
        });

        optionalStr = optionalStudent.map(studentx -> studentx.getName());
        System.out.println("Optional.map(new Function<Student, String>()) =" + optionalStr);

        // Optional.filter Optional满足特定条件则返回当前optional,否则返回 Optional.empty()
        Optional<Student> filterOptionStudent = optionalStudent.filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAge() > 40;
            }
        });
        filterOptionStudent = optionalStudent.filter(studentx -> studentx.getAge() > 40);
        System.out.println("optionalStudent.filter(new Predicate<Student>() =" + filterOptionStudent);

    }
}
