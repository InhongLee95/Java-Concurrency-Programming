package io.concurrency.chapter10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) {

        // 스레드 풀 생성, 스레드의 개수를 제한하지 않으며 작업 요청이 많을 때는 스레드 수가 증가
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 작업 제출
        for (int i = 1; i <= 20; i++) {
            executorService.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}
