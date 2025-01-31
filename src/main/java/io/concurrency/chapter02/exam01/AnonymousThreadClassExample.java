package io.concurrency.chapter02.exam01;

public class AnonymousThreadClassExample {
    public static void main(String[] args) {

        // 익명클래스 이용하여 스레드 생성 방식, 일회성
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " :스레드 실행 중..");
            }
        };

        thread.start();
    }
}
