package io.concurrency.chapter10.exam04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {

    interface Callback{
        void onComplete(int result);
    }

    public static void main(String[] args) {

        // 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(1);


        // 워커스레드에게 태스크 전달
        executorService.execute(()->{
            try {
                Thread.sleep(1000); // 태스크 수행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int result = 42;  // 결과값 반환

            // 콜백 메서드 호출
            Callback callback = new MyCallback();
            callback.onComplete(result);
        });

        System.out.println("비동기 작업 시작");
    }

    static class MyCallback implements Callback{
        // 콜백 호출이 되면 result를 통해 결과값을 받고 처리할 수 있다.
        @Override
        public void onComplete(int result) {
            System.out.println("비동기 작업 결과: " + result);
        }
    }
}
