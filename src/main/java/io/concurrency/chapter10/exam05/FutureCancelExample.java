package io.concurrency.chapter10.exam05;

import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 작업 생성
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            try {
                // 작업 도중 인터럽트가 발생할 수 있음
                Thread.sleep(5000);  // 작업을 5초로 늘림
            } catch (InterruptedException e) {
                System.out.println("작업이 인터럽트되었습니다.");
                throw e;  // 인터럽트 예외 던짐으로써 작업이 종료됨
            }
            System.out.println("비동기 작업 완료.");
            return 42;
        };

        // 작업을 제출하고 Future 객체를 받음
        Future<Integer> future = executorService.submit(callableTask);

        // 1초 후 작업 취소 시도
        Thread.sleep(1000);
        System.out.println("작업 취소 시도 중...");
        boolean cancel = future.cancel(true);  // 작업 강제 취소

        // 취소 여부 확인 및 결과 처리
        if (!cancel) {
            System.out.println("작업이 이미 완료되어 취소할 수 없습니다.");
        } else {
            System.out.println("작업이 성공적으로 취소되었습니다.");
        }

        // future.get() 호출을 통해 취소된 작업 결과 확인
        try {
            Integer result = future.get();  // 작업이 취소된 경우 CancellationException 발생
            System.out.println("Result: " + result);
        } catch (CancellationException e) {
            // 작업이 취소되어서 해당 예외가 발생되었고, 예외 처리하여 출력된다.
            System.out.println("작업이 취소되어 결과를 가져올 수 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
