package io.concurrency.chapter10.exam05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureGetExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 작업 생성
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("비동기 작업 완료.");
            return 42;
        };

        // executorService 작업 전달
        Future<Integer> future = executorService.submit(callableTask);

        // 워커 스레드가 작업 수행
        try {
            // 작업 결과 반환
            Integer result = future.get();
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
