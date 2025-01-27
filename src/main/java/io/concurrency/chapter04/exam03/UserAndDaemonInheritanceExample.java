package io.concurrency.chapter04.exam03;

public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) {

        // 사용자 스레드 생성
        Thread userThread = new Thread(() -> {
            Thread childOfUserThread = new Thread(() -> {
                System.out.println("사용자 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            });
            // 자식 스레드 실행
            childOfUserThread.start();
            System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        // 데몬 스레드 생성
        Thread daemonThread = new Thread(() -> {
            Thread childOfDaemonThread = new Thread(() -> {
                System.out.println("데몬 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            });
            // 자식 스레드 실행 -> 데몬스레드는 데몬 스레드를 생성하게 된다.
            childOfDaemonThread.start();
            System.out.println("데몬 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        // 데몬 스레드 ture 설정
        daemonThread.setDaemon(true);

        // 각 스레드 실행
        userThread.start();
        daemonThread.start();
    }
}
