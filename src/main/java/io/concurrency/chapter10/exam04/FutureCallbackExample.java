package io.concurrency.chapter10.exam04;

import java.util.concurrent.*;

public class FutureCallbackExample {

    interface Callback{
        void onComplete(int result);
    }

    public static void main(String[] args) {
        // 1. 메인 스레드에서 워커 스레드(스레드 1) 생성 및 작업 제출
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callableTask = () -> {
            Thread.sleep(1000);  // 작업 수행
            return 42;
        };

        // 메인 스레드가 스레드 풀에 작업 제출
        Future<Integer> future = executorService.submit(callableTask);
        System.out.println("비동기 작업 시작");

        // 2. 메인 스레드가 또 다른 스레드(스레드 2) 생성 및 실행
        registerCallback(future, result -> {
            System.out.println("비동기 작업 결과: " + result);
        });

        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, Callback callback) {
        // 메인 스레드가 새로운 스레드(스레드 2) 생성 및 실행
        new Thread(() -> {
            int result;
            try {
                result = future.get();  // 작업 결과 대기
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            // 작업 완료 후 콜백 호출
            callback.onComplete(result);
        }).start();
    }
}
