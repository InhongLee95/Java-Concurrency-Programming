package io.concurrency.chapter05.exam01;

public class MultiThreadExample {
    private static int sum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        // 스레드 1 생성
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 스레드 2 생성
        Thread thread2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
//                    throw new RuntimeException("error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 다른 스레드가 예외발생되도 다른 스레드 작업이 실행되지 못하는 상황은 발생되지 않는다.
        // 멀티 스레드로 1~1000 sum 작업 소요 시간 1초 절반으로 줄었다.
        System.out.println("합계: " + sum);
        System.out.println("멀티 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
