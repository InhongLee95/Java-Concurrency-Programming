package io.concurrency.chapter05.exam04;

public class ThreadSafeLocalReferenceObjectExample {
    class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject{" + "value=" + value + '}';
        }
    }

    // 아래 객체를 이용하여 메서드를 처리하면 -> 동시성 문제 발생한다.
//    LocalObject localObject = new LocalObject();

    public void useLocalObject() {
        // 메서드 안에서 객체를 생성하여 사용하므로, 스레드 안정성을 갖게 된다.
        // 지역 객체 참조. 각 스레드는 이 객체의 독립된 인스턴스를 가짐.
        LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        Thread thread1 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
