package io.concurrency.chapter10.exam03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnableTask = () ->{
            System.out.println("Runnable 작업 수행 중..");
            System.out.println("Runnable 작업 완료");
        };

        // executorService에게 수행해야 할 작업을 제출하고, 스레드 풀에서 작업을 실행한다
        executorService.execute(runnableTask);

        executorService.shutdown();
    }
}
