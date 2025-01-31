package io.concurrency.chapter10.exam07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownExample {
    public static void main(String[] args) {

        // 스레드 생성
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 작업 제출
        for (int i = 0; i < 5; i++) {
            executorService.submit(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": 작업 종료");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("인터럽트 걸림");
                }
                return 42;
            });
        }

        // executorService 종료 / 해당 메서드는 대기 상태 전환이 안된다.
        executorService.shutdown();

        try {
            if(!executorService.awaitTermination(100, TimeUnit.SECONDS)){
                // 종료가 안된 상황이라면 강제종료 shutdownNow 메서드 호출
                executorService.shutdownNow();
                System.out.println("스레드 풀 강제 종료 수행");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if(executorService.isShutdown()){
            System.out.println("스레드 풀 종료 여부: " + executorService.isShutdown());
        }

        if(executorService.isTerminated()){
            System.out.println("스레드 풀 완전 종료 여부: " + executorService.isTerminated());
        }

        // isTerminated false인 경우
        while(!executorService.isTerminated()){
            System.out.println("스레드 풀 종료 중..");
        }
        // isTerminated 가 true가 되면 종료 완료된 상황을 출력
        System.out.println("모든 작업이 종료되고 스레드 풀이 종료됨");
    }
}
