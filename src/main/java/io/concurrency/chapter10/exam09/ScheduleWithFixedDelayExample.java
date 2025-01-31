package io.concurrency.chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayExample {
    public static void main(String[] args) {

        // 스레드 풀 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);


        // 작업 정의
        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // 예외 처리
            }
        };

        // 처음 1 초가 지난 후 실행 되고 작업이 완료 되고 나서 지정된 주기 마다 계속 실행 된다
        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("작업이 인터럽트로 중단되었습니다.");
        }

        future.cancel(true); // 작업을 취소하면 인터럽트 되어 스케줄링이 중지된다
        scheduler.shutdown();
    }
}
