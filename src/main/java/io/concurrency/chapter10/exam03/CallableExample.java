package io.concurrency.chapter10.exam03;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args)  {

        ExecutorService executorService = Executors.newFixedThreadPool(1);


        // call 오버라이딩을 통해 작업을 정의한다.
        Callable<Integer> callableTask = ()->{
            System.out.println("Callable 작업 수행중..");
            System.out.println("Callable 작업 완료");

            return 42;
        };

        //executorService에게 작업 제출
        Future<Integer> future = executorService.submit(callableTask);
        int result;
        try {
            // get 메서드로 작업 결과 값 조회
            result = future.get();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Callable 작업 결과 : " + result);

        executorService.shutdown();
    }
}
