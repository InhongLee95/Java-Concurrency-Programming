package io.concurrency.chapter04.exam03;

public class DaemonThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {

        // 유저 스레드 생성
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("사용자 스레드 실행 중..");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 데몬 스레드 생성
        Thread daemonThread = new Thread(() -> {
            // 무한 루프
            while (true){
                try {
                    Thread.sleep(500);
                    System.out.println("데몬 스레드 실행 중..");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 데몬 스레드를 데몬 true 설정
        daemonThread.setDaemon(true);
        // daemonThread.setDaemon(false);

        // 스레드 실행
        userThread.start();
        daemonThread.start();


        userThread.join();

        System.out.println("메인 스레드 종료");
    }
}
