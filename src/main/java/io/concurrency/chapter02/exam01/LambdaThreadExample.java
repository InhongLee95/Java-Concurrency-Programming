package io.concurrency.chapter02.exam01;

public class LambdaThreadExample {
    public static void main(String[] args) {
        // 람다 표현식 이용하여 스레드 생성 방식
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중.."));
        thread.start();
    }
}
