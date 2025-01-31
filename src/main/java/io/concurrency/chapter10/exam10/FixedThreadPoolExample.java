package io.concurrency.chapter10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        
        // 스레드 풀 생성, Executors를 이용하여 ExecutorService 타입 생성
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        // 작업 제출
        for (int i = 1; i <= 5; i++) {
            executorService.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}
