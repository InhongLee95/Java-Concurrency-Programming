package io.concurrency.chapter06.exam02;

public interface CommonSemaphore {

    // 추상 메서드 구현
    void acquired();
    void release();
}
