package io.concurrency.chapter03.exam03;

public class InterruptedExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
                Thread.sleep(5000);
                // 인터럽트에 의해 아래 출력 cancel
                System.out.println("스레드 잠자기 완료");
            } catch (InterruptedException e) { // InterruptedException 예외가 발생하면 인터럽트 상태가 초기화 된다 : false
                System.out.println("스레드가 인터럽트 되었습니다.");
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());

                // 상태 true
                Thread.currentThread().interrupt();
            }
        });

        // 자식 스레드 실행
        thread.start();

        // sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 자식 스레드 인터럽트 전송
        thread.interrupt();
        thread.join();
        System.out.println("인터럽트 상태 3: " + thread.isInterrupted());
    }
}
