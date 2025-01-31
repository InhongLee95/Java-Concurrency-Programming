package io.concurrency.chapter03.exam03;

public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("스레드 작동 중");
                if (Thread.interrupted()) {
                    // interrupted 상태 초기화
                    System.out.println("인터럽트 상태 초기화 되었습니다.");
                    break;
                }
            }
            // false
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();

            // true
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        // 자식 스레드 실행
        thread.start();

        // 1초 sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //자식 스레드 인터럽트 전송
        thread.interrupt();
    }
}
