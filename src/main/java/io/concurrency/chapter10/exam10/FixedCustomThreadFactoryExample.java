package io.concurrency.chapter10.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedCustomThreadFactoryExample {
    public static void main(String[] args) {

        // ThreadFactory 생성
        ThreadFactory threadFactory = new CustomThreadFactory("CustomThread");

        // 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(3, threadFactory);


        // Future List 생성
        List<Future<Integer>> futures = new ArrayList<>();


        // 작업 정의
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            Callable<Integer> task = () -> {
                System.out.println("Thread : " + Thread.currentThread().getName() + ", Result: " + taskNumber + 1);
                return taskNumber + 1;
            };


            // 작업 제출
            Future<Integer> future = executor.submit(task);
            futures.add(future);
        }


        // 결과값 반환
        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String name;
        private int threadCount = 0;

        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            // 스레드 생성 커스텀
            threadCount++;
            String threadName = name + "-" + threadCount;
            Thread newThread = new Thread(r, threadName);
            System.out.println("스레드 이름: " + threadName);
            return newThread;
        }
    }
}
