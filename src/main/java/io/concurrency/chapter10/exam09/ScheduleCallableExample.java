package io.concurrency.chapter10.exam09;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) {

        // 스레드 풀 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 작업 정의
        Callable<String> task = () -> {
            return "작업이 한번 실행 되고 결과를 반환 합니다.";
        };

        // 작업 예약
        ScheduledFuture<String> future = scheduler.schedule(task, 3, TimeUnit.SECONDS);


        // 결과값 반환
        try {
            String result = future.get();
            System.out.println("작업 결과: " + result);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
