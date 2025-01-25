package io.concurrency.chapter02.exam03;

public class NewStateThreadExample {

    // 스레드 상태 1단계
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 실행 중");
        });

        // getState 스레드 상태 조회
        System.out.println("스레드 상태: " + thread.getState()); // 객체만 NEW 상태
    }

}
