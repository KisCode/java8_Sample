package com.kiscode.java8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "Runnable");
            }
        }).start();


        new Thread(() -> Log.i("Runnable", "Runnable")).start();


        List<String> list = Arrays.asList("A", "B", "C", "D", "NB", "CBA", "DNF");
        list.forEach(s -> Log.i("forEach", s));

        list.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "String is " + s;
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.i("string", s);
            }
        });

        //lambda表达式实现
        list.stream().map(s -> "String is " + s)
                .forEach(s ->
                        Log.i("string", s));
    }
}