package io.concurrency.chapter02.exam02;

public class ThreadStartRunExample {
    public static void main(String[] args) {

        // 스레드 생성 후 실행
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(new Runnable() {

            // JVM이 run 메서드를 자동 호출한다.
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " :스레드 실행중..");
            }
        });
        // start, start0 메서드를 실행하여 스레드 생성 후 run 메서드 자동 호출
        thread.start();
//        thread.run();  -> 스레드를 생성하지 않고 기존 main 스레드 이용하여 실행
//        myRunnable.run(); -> 객체를 통해 바로 run 메서드를 실행해도 스레드 생성되지 않고, 기존 스레드 실행
    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
        }
    }
}
