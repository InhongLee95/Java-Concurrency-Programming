package io.concurrency.chapter01.exam01;

import java.util.ArrayList;
import java.util.List;

public class ConcurrencyExample {
    public static void main(String[] args) {

        // cpu 코어 수 
        int cpuCores = Runtime.getRuntime().availableProcessors();
//        int cpuCores = 13;

        // CPU 개수만큼 데이터를 생성
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // CPU 개수만큼 데이터를 각각의 스레드로 병렬 처리
        long startTime1 = System.currentTimeMillis();
        long sum1 = data.stream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime1 = System.currentTimeMillis();
        System.out.println("cpu 코어수 " + cpuCores);
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime1 - startTime1) + "ms");
        System.out.println("결과1: " + sum1);
    }
}
