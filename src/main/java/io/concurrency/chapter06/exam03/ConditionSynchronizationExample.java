package io.concurrency.chapter06.exam03;

public class ConditionSynchronizationExample {
    private boolean isAvailable = false;


    // synchronized 키워드 사용
    public synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("생산됨.");
        isAvailable = true;
        notify();
    }

    // synchronized 키워드 사용
    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("소비됨.");
        isAvailable = false;
        notify();
    }

    public static void main(String[] args) {
        ConditionSynchronizationExample example = new ConditionSynchronizationExample();

        // producer 생성
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.produce();
            }
        });

        // consumer 생성
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.consume();
            }
        });

        // 각 실행
        producer.start();
        consumer.start();
    }
}