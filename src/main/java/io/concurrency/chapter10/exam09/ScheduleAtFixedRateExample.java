package io.concurrency.chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {

        // 스레드 풀 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        // 작업 정의
        Runnable task = () -> {
            try {
                Thread.sleep(2000);  // 작업이 2초 걸림
                System.out.println("thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 1초 후 실행, 1초마다 반복 실행
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

        // 메인 스레드를 충분히 대기시켜 작업이 실행될 시간을 확보
        try {
            Thread.sleep(10000);  // 메인 스레드가 10초 동안 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 작업 취소 및 스레드 풀 종료
//        future.cancel(true);
        scheduler.shutdown();
    }
}
