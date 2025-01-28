package io.concurrency.chapter05.exam04;

public class ThreadSafeLocalVariableExample {

    // 아래 필드 변수로 작업을 수행하게 된다면 스레드 안정성 있게 처리하지 못한다.
//    int localSum = 0;
public void printNumbers(int plus) {
    // 지역 변수, 매개 변수로 정의된 변수. 스택에 저장되므로, 각 스레드는 이 변수의 독립된 복사본을 가진다.
    // 스택에 한정 되어서 안정성 있는 스레드 작업이 가능하다.
    int localSum = 0;

    // 각 스레드 마다 15씩 더한다.
    for (int i = 1; i <= 5; i++) {
        localSum += i;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    localSum += plus;
    System.out.println(Thread.currentThread().getName() + " - 현재 합계: " + localSum);
}

    public static void main(String[] args) {
        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        // 스레드 생성
        Thread thread1 = new Thread(() -> {
            example.printNumbers(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumbers(40);
        }, "Thread-2");

        // 스레드 실행
        thread1.start();
        thread2.start();
    }
}
