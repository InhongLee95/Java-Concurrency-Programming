package io.concurrency.chapter10.exam04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {

        //스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 태스크 제출
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000); // 작업 수행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42; // 작업 결과값 반환
        });
        System.out.println("비동기 작업 시작");
        try {
            // 작업 결과 반환, 메인 스레드 대기 상태 발생
            int result = future.get();
            System.out.println("비동기 작업결과: " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

    }
}
