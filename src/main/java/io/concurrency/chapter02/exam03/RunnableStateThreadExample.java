package io.concurrency.chapter02.exam03;

public class RunnableStateThreadExample {

    public static void main(String[] args) throws InterruptedException {

        // 스레드 상태 2단계 RUNNABLE 상태
        Thread thread = new Thread(() -> {
            while (true) {
                for (int i = 0; i < 1000000000; i++) {
                    if(i%1000000000 == 0){
                        System.out.println("스레드 상태: " + Thread.currentThread().getState()); // RUNNABLE 상태
                    }
                }
            }
        });

        // start() 메서드 실행하여 스레드를 생성하고 Runnable 상태가 된다.
        thread.start();
    }
}
