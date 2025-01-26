package io.concurrency.chapter03.exam03;

public class InterruptedExample2 {
    public static void main(String[] args) {
        Thread thread2 = new Thread(() -> {
            // 인터럽트 상태가 false일 때, while 반복문 수행
            while (!Thread.interrupted()) {
                System.out.println("스레드 2 작동 중");
            }
            // while이 종료된 후: 인터럽트 상태는 이미 초기화되었으므로 false 출력
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("스레드 1 작동 중");
                if (i == 2) {
                    // 스레드 2에게 인터럽트 신호를 보냄
                    thread2.interrupt();
                }
                try {
                    Thread.sleep(500); // 0.5초 일시정지
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 스레드1, 스레드2를 동시에 실행
        thread1.start();
        thread2.start();
    }
}
