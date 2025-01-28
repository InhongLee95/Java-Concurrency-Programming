package io.concurrency.chapter06.exam02;

public class BinarySemaphoreExample {

    public static void main(String[] args) {

        CommonSemaphore semaphore = new BinarySemaphore();
        SharedResource resource = new SharedResource(semaphore);

        // 스레드 생성 및 실행
        Thread t1 = new Thread(resource::sum);
        Thread t2 = new Thread(resource::sum);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 값: " + resource.getSum());
    }
}

class SharedResource {
    private int value = 0;
    private CommonSemaphore commonSemaphore;

    public SharedResource(CommonSemaphore commonSemaphore) {
        this.commonSemaphore = commonSemaphore;
    }

    public void sum() {
        // 시그널 획득
        commonSemaphore.acquired();
        for(int i=0; i< 1000000; i++) {
            value++;
        }
        // 시그널 해제
        commonSemaphore.release();
    }
    public int getSum() {
        return value;
    }
}


