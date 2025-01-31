package io.concurrency.chapter04.exam05.logger;

public class LogWorker implements Runnable {


    // 스레드 실행 작업
    @Override
    public void run() {

        ThreadLocalLogger.ServiceA serviceA = new ThreadLocalLogger.ServiceA();
        ThreadLocalLogger.ServiceB serviceB = new ThreadLocalLogger.ServiceB();
        ThreadLocalLogger.ServiceC serviceC = new ThreadLocalLogger.ServiceC();

        if (Thread.currentThread().getName().equals("Thread-1")) {
            serviceA.process();
            serviceB.process();
            serviceC.process();
        } else if (Thread.currentThread().getName().equals("Thread-2")) {
            serviceB.process();
            serviceA.process();
            serviceC.process();
        } else {
            serviceC.process();
            serviceA.process();
            serviceB.process();
        }

        ThreadLocalLogger.printLog();
        ThreadLocalLogger.clearLog();
    }
}
