package com.kiscode.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalField;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/8/29 14:14
 * <p>
 * https://www.cnblogs.com/jnba/p/10636844.html
 */

public class DateTimeTester {
    public static void main(String[] args) {
//        testInitLocalDate();

//        testInitLocacleDateTime();
        testFormateLocacleDateTime();

//        testInstant();
    }


    private static void testInitLocalDate() {
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);  //输出:2020-08-29

        //初始化年月日
        localDate = LocalDate.of(2020, 11, 11);
        System.out.println(localDate);

        //初始化为 2020年中的第99天
        localDate = LocalDate.ofYearDay(2020, 99);
        System.out.println(localDate);

        localDate = LocalDate.parse("2019-01-02");
        System.out.println(localDate);

        localDate = LocalDate.ofEpochDay(1);
        System.out.println("LocalDate.ofEpochDay(1) =" + localDate);

        localDate = LocalDate.parse("2019-01-02", DateTimeFormatter.ISO_DATE);
        localDate.plusDays(10);//加10天


        localDate = LocalDate.parse("1970-06-01");
        localDate.toEpochDay();  //相对1970-01-01相差多少天
        System.out.println(localDate + "---" + localDate.toEpochDay());
    }

    private static void testInitLocacleDateTime() {
        System.out.println("--------------------LocalDateTime------------------");
        //2020-08-29T12:47:55.335
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime.now() =" + LocalDateTime.now());

//        localDateTime = LocalDateTime.parse("2020-08-29 12:47:55", DateTimeFormatter.ISO_DATE_TIME);
//        System.out.println("LocalDateTime.parse(\"2020-08-29 12:47:55\") = " + localDateTime);
        String dateTime = localDateTime.getYear()
                + "." + localDateTime.getMonth().getValue()
                + "." + localDateTime.getDayOfMonth()
                + " " + localDateTime.getHour()
                + ":" + localDateTime.getMinute()
                + ":" + localDateTime.getSecond();
        System.out.println("formatDateTime: " + dateTime);


        String formateDate = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG));
        formateDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        System.out.println("formatDateTime: " + formateDate);

    }

    /***
     * 时间戳
     */
    private static void testInstant() {
        Instant ins = Instant.now();  //UTC时间
        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        System.out.println(ins.getNano());
        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }


    private static void testFormateLocacleDateTime() {
        System.out.println("--------------------testFormateLocacleDateTime------------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime:" + localDateTime);
        System.out.println("DateTimeFormatter.ISO_DATE_TIME \t" + localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        System.out.println("--------------------------------------");

        String formateDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        System.out.println("format \t" + formateDateTime);

        localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd kk:mm"));
        System.out.println("format \t" + formateDateTime);


        //将指定的时间字符转为 LocalDateTime
        LocalDateTime localDateTime1 = LocalDateTime.parse("2017-07-07T12:12", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        System.out.println(localDateTime1);


    }
}