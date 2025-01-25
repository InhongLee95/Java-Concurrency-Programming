package io.concurrency.chapter02.exam02;

public class ThreadStackExample {
    public static void main(String[] args) {


        // 스레드 3개 생성
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }

    }
    static class MyRunnable implements Runnable{

        private final int threadId;

        // 스레드 id 할당
        public MyRunnable(int threadId) {

            this.threadId = threadId;
        }

        @Override
        public void run() {
            // 처리할 작업 정의
            System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {

            int localValue = threadId + 100;
            secondMethod(localValue);

        }

        private void secondMethod(int localValue) {
            String objectReference = threadId + ": Hello World";
            System.out.println(objectReference);
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value:" + localValue);
        }
    }
}
