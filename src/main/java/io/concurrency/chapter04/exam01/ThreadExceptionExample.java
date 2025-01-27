package io.concurrency.chapter04.exam01;

public class ThreadExceptionExample {

    public static void main(String[] args) {
        // 해당 스레드에서 예외가 발생하면 catch문에서 잡을 수 가 없다.
        try {
            new Thread(() -> {
                throw new RuntimeException("스레드 1 예외!");
            }).start();
        }catch(Exception e){
            sendNotificationToAdmin(e);
        }

    }

    private static void sendNotificationToAdmin(Throwable e) {
        System.out.println("관리자에게 알림: " + e.getMessage());
    }
}
