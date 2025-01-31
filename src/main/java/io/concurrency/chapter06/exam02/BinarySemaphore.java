package io.concurrency.chapter06.exam02;

public class BinarySemaphore implements CommonSemaphore {
    private int signal = 1;

    // 시그널 획득 메서드
    public synchronized void acquired() {
        while (this.signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
            }
        }
        // 시그널 0으로 설정
        this.signal = 0;
    }

    // 시그널 해제 메서드
    public synchronized void release() {
        this.signal = 1;
        this.notify();
    }
}
