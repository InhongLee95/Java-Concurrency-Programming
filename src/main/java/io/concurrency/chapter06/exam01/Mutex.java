package io.concurrency.chapter06.exam01;
public class Mutex {
    // 락 용도 변수
    private boolean lock = false;

    // 락 획득 메서드, synchronized 이용
    public synchronized void acquired() {
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 먼저 작업 실행하고 있는 스레드가 release 호출하기 전까지 lock = true
        this.lock = true;
    }

    // 락 해제 메서드
    public synchronized void release() {
        this.lock = false;
        this.notify();
    }
}
