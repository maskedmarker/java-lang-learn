package org.example.learn.java.multi.thread.basic.state;

import java.util.concurrent.TimeUnit;

/**
 * 观察被notify的线程的状态变化
 *
 * A thread can be in one of the following states: NEW  RUNNABLE  BLOCKED  WAITING  TIMED_WAITING  TERMINATED
 *
 * sleep会影响线程的状态,在idea的debugger中会友好显示sleeping(notifiedThread.getState()还是TIMED_WAITING)
 * 在notifyingThread调用notifyAll后,跳出synchronized代码块之前,idea的debugger中会友好显示notifiedThread的状态为monitor(notifiedThread.getState()还是BLOCKED)
 */
public class NotifiedThreadStateDemo {

    private final Object lock = new Object();

    public void demo() {
        Thread notifiedThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 通过sleep来模拟执行业务代码的耗时,方便其他线程观察当前线程在被notify后的状态
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "notifiedThread");

        Thread notifyingThread = new Thread(() -> {
            try {
                // 等待notifiedThread已经执行wait方法
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("这里打断点,是为了观察线程[notifiedThread]的状态.");
            synchronized (lock) {
                System.out.println("这里打断点,是为了观察线程[notifiedThread]的状态.");
                lock.notifyAll();
                System.out.println("这里打断点,是为了观察线程[notifiedThread]的状态.");
            }
            System.out.println("这里打断点,是为了观察线程[notifiedThread]的状态.");

        }, "notifyingThread");


        notifiedThread.start();
        notifyingThread.start();

        System.out.println("这里打断点,是为了观察其他线程的状态.");
    }


    public static void main(String[] args) {
        NotifiedThreadStateDemo demo = new NotifiedThreadStateDemo();
        demo.demo();
    }

}
