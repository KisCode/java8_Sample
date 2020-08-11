package com.kiscode.lambda;

public class Main {
    public static void main(String[] args) {
        System.out.println("Java test 12345");
        Main main =new Main();

        main.startThread();
    }

    private void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("CurrentThread:"+Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            System.out.println("CurrentThread lambda:"+Thread.currentThread().getName());
        }).start();
    }
}