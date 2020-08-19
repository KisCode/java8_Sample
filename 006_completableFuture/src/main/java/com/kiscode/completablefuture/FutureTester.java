package com.kiscode.completablefuture;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/****
 * Description: 
 * Author:  Administrator
 * CreateDate: 2020/8/19 22:54
 */

public class FutureTester {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            public Double call() {
                System.out.println(Thread.currentThread().getName());
                return 2.0D;    //异步方式在新的线程中执行操作
            }
        });

        try {
            //获取异步操作的结果，如果阻塞，等1秒后退出
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
        }

    }
}
