package io.concurrency.chapter03.exam04;

public class CurrentThreadExample {
    public static void main(String[] args) {

        // 현재 실행 중인 스레드를 가져와 이름 출력 (메인 스레드 정보 출력)
        System.out.println("현재 스레드: " + Thread.currentThread()); // 현재 실행 중인 메인 스레드 정보
        System.out.println("현재 스레드 이름: " + Thread.currentThread().getName()); // 메인 스레드 이름 출력

        // Thread 객체를 사용하여 스레드 생성 및 시작
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // 스레드 1번 정보 출력 (현재 실행 중인 스레드 정보 출력)
                System.out.println("현재 스레드: " + Thread.currentThread());
                // 현재 실행 중인 스레드의 이름 출력 (Thread 객체 사용)
                System.out.println("현재 스레드 이름 (Thread 객체 사용): " + getName());
            }
        };

        // 스레드1 실행 (Thread 클래스의 익명 객체로 정의된 스레드)
        thread1.start();

        // Runnable 인터페이스를 사용하여 스레드 생성 및 시작
        Thread thread2 = new Thread(new ThreadName());
        thread2.start();
    }

    // Runnable 인터페이스를 구현하여 스레드 동작 정의
    static class ThreadName implements Runnable {
        @Override
        public void run() {
            // 스레드 2번 정보 출력 (Runnable 객체에서 실행 중인 스레드 정보)
            System.out.println("현재 스레드: " + Thread.currentThread());
            // 현재 실행 중인 스레드의 이름 출력 (Runnable 사용)
            System.out.println("현재 스레드 이름 (Runnable 사용): " + Thread.currentThread().getName());
        }
    }
}
