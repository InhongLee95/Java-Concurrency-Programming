package io.concurrency.chapter02.exam01;

public class AnonymousRunnableClassExample {
    public static void main(String[] args) {
        // Runnable 및 익명 클래스를 이용하여 스레드 생성, 일회성
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중..");
            }
        });

        thread.start();

    }
}
