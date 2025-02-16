package io.concurrency.chapter05.exam02;

public class CpuSyncExample {
    private static int count = 0;
    private static final int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {

        // 스레드 1 생성
        // synchronized 이용하여 동기화 처리
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized(CpuSyncExample.class) {
                    count++;
                }
            }
        });

        // 스레드 2 생성
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized(CpuSyncExample.class) {
                    count++;
                }
            }
        });

        // 스레드 실행
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상 결과: " + (2 * ITERATIONS));
        System.out.println("실제 결과: " + count);
    }
}
