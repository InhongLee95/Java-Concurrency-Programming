package io.concurrency.chapter06.exam02;

public class CountingSemaphoreExample {
    public static void main(String[] args) {

        int permits = 10; // 최대 10개의 스레드가 동시에 작업을 수행할 수 있습니다.
        CountingSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource resource = new SharedResource(semaphore);

        int threadCount = 15; // 전체 스레드 개수

        // 각 스레드 임계 구역 sum 메서드 호출
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(resource::sum);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("최종 값: " + resource.getSum());
    }
}

