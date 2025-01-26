package io.concurrency.chapter03.exam03;

public class InterruptedExample1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // interrupted 와 isInterrupted 차이는 interrupted는 상태를 확인하고, 동시에 상태를 초기화 한다.
            while (!Thread.interrupted()) {
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


        // 자식 스레드에게 인터럽트 전송
        thread.interrupt();
    }
}
