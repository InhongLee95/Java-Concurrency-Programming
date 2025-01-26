package io.concurrency.chapter03.exam02;

public class InterruptedJoinExample {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        // 자식 스레드 1
        Thread longRunningThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("긴 작업 스레드가 계속 실행 중...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("긴 작업 스레드가 인터럽트 되었습니다.");
                // 긴 작업 스레드 종료
            }
        });

        longRunningThread.start();

        // 자식 스레드 2가 작업 스레드에게 인터럽트 신호 전송
        Thread interruptingThread = new Thread(() -> {
            try {
                System.out.println("인터럽트 스레드가 2초 후에 긴 작업 스레드를 인터럽트 합니다.");
                Thread.sleep(2000);
                longRunningThread.interrupt();
            } catch (InterruptedException e) {
                System.out.println("인터럽트 스레드가 중단되었습니다.");
            }
        });

        interruptingThread.start();

        try {
            System.out.println("메인 스레드가 긴 작업 스레드의 완료를 기다립니다.");
            longRunningThread.join(); // 긴 작업 스레드가 끝날 때까지 대기
            System.out.println("메인 스레드 작업 완료");
        } catch (InterruptedException e) {
            System.out.println("메인 스레드가 인터럽트 되었습니다.");
            // 메인 스레드가 중단되더라도 정상적으로 종료
            Thread.currentThread().interrupt(); // 인터럽트 상태 복원
        }
    }
}
