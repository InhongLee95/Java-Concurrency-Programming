package io.concurrency.chapter03.exam01;

public class MultiThreadSleepExample {
    public static void main(String[] args) {

        // 여러 스레드들을 각 sleep 호출 가능하다.
        // 비동기적 처리가 된다.
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("1초 후에 메시지가 출력됩니다");
                Thread.sleep(1000);
                System.out.println("스레드 1이 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("2초 후에 메시지가 출력됩니다");
                Thread.sleep(2000);
                System.out.println("스레드 2가 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        // 메인 스레드부터 종료
        System.out.println("여기는 메인입니다.");


    }
}
