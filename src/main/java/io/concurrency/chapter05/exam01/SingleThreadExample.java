package io.concurrency.chapter05.exam01;

public class SingleThreadExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = 0;

        // 메인 스레드 단일로 처리
        for (int i = 1; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
                throw new RuntimeException("error");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 예외 발생 시 app이 실행되지 못하고 종료된다.
        // 단일 스레드는 1~ 1000까지 sum 하는데 소요 시간 2초
        System.out.println("합계: " + sum);
        System.out.println("싱글 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
