package org.example.learn.java.multi.thread.basic.notify;

/**
 * 3个线程按规定顺序依次打印字符A/B/C
 *
 * 使用基本的wait/notify机制实现
 * 下面的代码出自 极海Channel https://www.bilibili.com/video/BV1dG4y1a7fS?vd_source=a7e33aa193d06f1f8aecfd38e7447125
 */
public class PrintInTurnDemo {

    // 0-A 1-B 2-C
    private int state;

    private final Object lock = new Object();

    // 一共需要打印多少字符
    private int totalCount;

    // 当前已经打印了多少字符
    private int count;

    public PrintInTurnDemo(int totalCount) {
        this.totalCount = totalCount;
    }

    public void printInTurn() {
        Thread threadA = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count < totalCount && state == 0) {
                        System.out.println("A");
                        count++;
                        state = 1;

                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count < totalCount && state == 1) {
                        System.out.println("B");
                        count++;
                        state = 0;

                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "threadB");

        Thread threadC = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count < totalCount && state == 2) {
                        System.out.println("C");
                        count++;
                        state = 0;

                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "threadC");

        threadA.start();
        threadB.start();
        threadC.start();

        // 上面的线程在完成打印任务后,就一直在空转,线程不会结束
        System.out.println("这里打断点,是为了观察其他线程的状态.");
    }

    public static void main(String[] args) {
        PrintInTurnDemo demo = new PrintInTurnDemo(100);
        demo.printInTurn();
    }
}
