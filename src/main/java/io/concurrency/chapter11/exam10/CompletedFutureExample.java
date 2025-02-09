package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;

public class CompletedFutureExample {
    public static void main(String[] args) {


        // completedFuture 이용하여 주어진 값으로 설정하고 CompletableFuture 를 완료 상태로 전환
        CompletableFuture<String> cf = CompletableFuture.completedFuture("Hello World");

//        CompletableFuture<String> cf2 = new CompletableFuture<>();
//        cf2.complete("Hello World");

        CompletableFuture<Void> finalCf = cf.thenAccept(r -> {
            System.out.println("result: " + r);
        });
    }
}
