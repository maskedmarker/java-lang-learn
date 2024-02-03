package org.example.learn.java.multi.thread.basic.exception;

import java.util.concurrent.TimeUnit;

public class RunnableThrowExTest {

    private static class TimerBomb {
        private int seconds = 3;
        private Thread thread;

        public TimerBomb(int seconds) {
            this.seconds = seconds;
        }

        private void countdown() {
            thread = new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        seconds--;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (seconds == 0) {
                        throw new RuntimeException("timerBomb is bombing");
                    }
                }
            });

            thread.start();
        }
    }

    public static void main(String[] args) {
        TimerBomb timerBomb = new TimerBomb(5);
        timerBomb.countdown();
    }
}
