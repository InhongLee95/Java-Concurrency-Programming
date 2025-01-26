package io.concurrency.chapter03.exam03;

public class IsInterruptedExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // 인터럽트 true 되면 종료
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("스레드가 작동 중입니다.");
            }
            System.out.println("스레드가 인터럽트 되었습니다.");
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        // 스레드 실행
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // sleep 이후 메인 스레드가 자식 스레드에게 인터럽트 전송
        thread.interrupt();
    }
}
