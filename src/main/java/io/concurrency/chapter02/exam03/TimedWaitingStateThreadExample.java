package io.concurrency.chapter02.exam03;

public class TimedWaitingStateThreadExample {

    public static void main(String[] args) throws InterruptedException {
        // 스레드 상태 4단계 : sleep이용하여 TIMED_WAITING
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread.getState()); // TIMED_WAITING 상태
    }

}
