package io.concurrency.chapter02.exam01;

public class ImplementRunnableExample {
    public static void main(String[] args) {

        // 스레드 객체에다가 Runnable 객체를 전달하다.
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task);
        thread.start();
    }
}

class MyRunnable implements Runnable{

    // Runnable 인터페이스 구현 후 run 메서드 오버라이딩
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중");
    }
}
