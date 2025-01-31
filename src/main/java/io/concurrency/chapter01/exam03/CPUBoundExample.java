package io.concurrency.chapter01.exam03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBoundExample {
    public static void main(String[] args) throws InterruptedException {

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads: " + numThreads);
        // 코어수 만큼 스레드 생성
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // 시작 시간 체크
        long startTime2 = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Future<?> future = executorService.submit(() -> {

                // Cpu 연산이 집중되고 오래 걸리는 작업
                long result = 0;
                for (long j = 0; j < 1000000000L; j++) {
                    result += j;
                }

                // 아주 잠깐 대기함
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("스레드: " + Thread.currentThread().getName() + ", " + result); // Cpu Bound 일때 ContextSwitching 은 크게 발생하지 않는다
            });
            futures.add(future);
        }
        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 작업 종료 시간
        long endTime2 = System.currentTimeMillis();
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime2 - startTime2) + "ms");
        executorService.shutdown();
    }
}
