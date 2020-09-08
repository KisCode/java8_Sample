package com.kiscode.java8;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        list.stream().forEach(s -> {
            Log.i("stream() forEach", s);
        });
    }
}