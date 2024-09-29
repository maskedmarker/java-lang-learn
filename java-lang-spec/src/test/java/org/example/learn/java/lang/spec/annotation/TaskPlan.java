package org.example.learn.java.lang.spec.annotation;

public class TaskPlan {

    @Schedule(jobName = "job1", cron = "11111")
    @Schedule(jobName = "job2", cron = "22222")
    @Executor("thread-pool-task")
    public void perform() {
    }
}
