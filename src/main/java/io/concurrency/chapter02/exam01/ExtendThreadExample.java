package io.concurrency.chapter02.exam01;

public class ExtendThreadExample {
    public static void main(String[] args) {

        // 스레드 객체 생성 후 커널 스레드 생성
        MyThread myThread = new MyThread();
        myThread.start();
    }
 }

 class MyThread extends Thread{
     @Override
     public void run() {
         System.out.println(Thread.currentThread().getName() + " :스레드 실행 중.. ");
     }
 }