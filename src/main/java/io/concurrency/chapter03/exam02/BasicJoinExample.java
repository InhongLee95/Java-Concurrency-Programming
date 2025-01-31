package io.concurrency.chapter03.exam02;

public class BasicJoinExample {

    public static void main(String[] args) {

        // 메인 스레드에 의해서 자식 스레드 실행하고, 작업 대기
        Thread thread = new Thread(() -> {
            try {
                System.out.println("스레드가 3초 동안 작동합니다.");
                Thread.sleep(5000);
                System.out.println("스레드 작동 완료.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다.");

        try {
            // 자식 스레드에게 join을 호출하여 메인 스레드가 순차적 처리하기 위해 대기하게 된다.
            // 누군가 인터럽트를 전달하면, 작업 대기에서 실행 대기 상태로 전환된다.
            // 결국 아래 join 밑으로 작업 수행하지 못하고, 자식 스레드가 완료되어야 아래 sysout이 출력 된다.
            thread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드가 계속 진행합니다");
    }
}
