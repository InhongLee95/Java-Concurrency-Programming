package io.concurrency.chapter05.exam02;

public class CpuNonSyncExample {
    private static int count = 0;
    private static final int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {

        // 스레드 1 생성
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        // 스레드 2 생성
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        // 스레드 실행
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        // 최종 값이 20만이어야 하지만 실제 결과는 13만이다.
        // CPU 원자성 보장이 되지 않는다. CPU가 동기화 되지 않아 데이터 불일치 현상이 일어난다.
        System.out.println("예상 결과: " + (2 * ITERATIONS));
        System.out.println("실제 결과: " + count);
    }
}
