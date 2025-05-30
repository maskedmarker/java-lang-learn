# 关于juc doc的一些记录

## Runnable
Runnable:whose instances are intended to be executed by a thread.
Runnable.run()代表操作系统线程(新线程,而非当前)预留给应用代码的执行入口


## Callable<V>
The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially executed by another thread. 
A Runnable, however, does not return a result and cannot throw a checked exception.
Runnable call()无法声明checked-exception和无返回值


## Future<V>
A Future represents the result of an asynchronous computation.
正常情况下,Future.get()代表未来正常计算后的结果.
异常情况下,都无法获取到正常结果.异常情况包括:
1.当前线程在等待结果的过程中,当前线程被中断,提前退出等待,此时Future.get()抛出InterruptedException
2.当前线程在等待结果的过程中,执行计算任务被其他线程(任务线程/其他的)取消了,提前退出等待,此时Future.get()抛出CancellationException
3.当前线程在等待结果的过程中,任务线程执行中抛出了异常,从而无法计算出结果,提前退出等待,此时Future.get()抛出ExecutionException
4.当前线程在等待结果的过程中,等待超时,提前退出等待,此时Future.get()抛出TimeoutException
因为正常和异常都是被当作执行完成,所以Future.get()必须在非正常执行完成下抛出异常,否则区分执行完成是否是因为异常导致的.

备注:
1.Future并不是纯粹的the result of an asynchronous computation,还提供了cancel()方法来取消task的执行.
2.Future.cancel()感觉应该出现在对应的task上.当task被cancel后,Future.get()将抛出CancellationException.

## RunnableFuture<V>
RunnableFuture<V> extends Runnable, Future<V>
javadoc中对RunnableFuture的描述是A Future that is Runnable. Successful execution of the run method causes completion of the Future and allows access to its results.
我的理解是a Runnable(task) with a Future result.javadoc中只是重点强调了result,而回避了主体Runnable(task)
Runnable.run()代表了执行过程,Future.get()代表了执行结果(并未指明是哪个类执行的).RunnableFuture表示的当前类的计算结果(包括计算中的副作用)作为异步计算的结果,而非其他类的计算结果.
备注:
1.RunnableFuture既要当作Runnable看待,也要当作Future看待.
2.RunnableFuture接口的一个重要实现类FutureTask


## FutureTask<V>
FutureTask<V> implements RunnableFuture<V>
FutureTask实现了RunnableFuture接口,是一个task,内部有完整的状态控制


### FutureTask(Runnable runnable, V result)
Creates a FutureTask that will, upon running, execute the given Runnable, and arrange that get will return the given result on successful completion.
result – the result to return on successful completion. If you don't need a particular result, consider using constructions of the form: Future<?> f = new FutureTask<Void>(runnable, null)

### FutureTask的状态更新
1.正常情况下,FutureTask在任务正常执行完,才会将状态从new更新为COMPLETING,再将状态更新为NORMAL,其后设置run的结果值(outcome)和唤醒其他等待结果的线程.
2.异常情况下,FutureTask在任务抛出异常后,才会将task的状态从new更新为EXCEPTIONAL,其后设置异常值(outcome)和唤醒其他等待结果的线程.
3.在任务开始执行到执行完(正常/异常)前,状态此时还是new

#### FutureTask.cancel(mayInterruptIfRunning)
1.所谓mayInterruptIfRunning指的是,在任务执行完(正常/异常)前,将状态从new更新为INTERRUPTING,这样就阻止了正在执行任务的线程更新状态和设置outcome.
2.如果任务线程还未开始执行任务,就不用标记任务线程为interrupt状态.
3.如果任务线程已经开始执行任务,标记任务线程为interrupt状态.(至于任务线程对于interrupt标记怎么处理,就任务线程来决定.通常情况下,线程是要处理中断状态的.)
4.最后将状态更新为INTERRUPTED,并唤醒其他等待结果的线程.

#### FutureTask.run()
if (state != NEW || !UNSAFE.compareAndSwapObject(this, runnerOffset,null, Thread.currentThread())) {return;}
解释:只有状态为new的任务且cas原子更新runner才有可能执行(考虑到中断因素).
while (state == INTERRUPTING) Thread.yield();
解释:因为run先cas更新runner,执行任务后再cas更新state.可能在更新完runner后到开始执行任务这段时间,被调用了cancel.此时任务线程等待interrupter将任务线程标记为中断.
while循环可以保证run返回后看到的任务状态为终态(INTERRUPTED),而非无意义的瞬时态(INTERRUPTING)

#### FutureTask.done()
FutureTask预留的扩展点,当任务结束(正常/或异常)后回调.


## Callable优于Runnable
FutureTask内部用的是Callable;Executor也是侧重使用Callable,内部会将Runnable转换为Callable<Void>
Executors.callable(java.lang.Runnable, T)提供了Runnable转换Callable的工具方法.
Executors.callable(java.lang.Runnable, T):Returns a Callable object that, when called, runs the given task and returns the given result.
我们在使用Callable时更看重call方法的返回值,使用Runnable时更看重run方法的副作用(它也没返回值).
再配合使用Future时,会将Callable.call的返回值作为Future.get的返回值,会将Runnable.run的副作用作为Future.get的返回值(究竟是哪个副作用,比较不明了)

## ScheduledFuture
ScheduledFuture<V> extends Delayed, Future<V>
Usually a scheduled future is the result of scheduling a task with a ScheduledExecutorService.

## RunnableScheduledFuture
RunnableScheduledFuture<V> extends RunnableFuture<V>, ScheduledFuture<V>
A ScheduledFuture that is Runnable. Successful execution of the run method causes completion of the Future and allows access to its results.
备注:RunnableScheduledFuture是一个task.


## CompletionService
A service that decouples the production of new asynchronous tasks from the consumption of the results of completed tasks. 
Producers submit tasks for execution. Consumers take completed tasks and process their results in the order they complete.
解耦提交新的异步任务与消费已完成任务的任务结果。
生产者提交任务以供执行,消费者获取已完成的任务并按完成顺序处理其结果。
CompletionService 可以用于管理异步 I/O，其中执行读取操作的任务在程序或系统的某一部分中提交，然后在程序的另一部分中在读取完成时进行处理，可能与请求的顺序不同。通常，CompletionService 依赖于单独的 Executor 来实际执行任务，此时 CompletionService 仅管理内部完成队列。ExecutorCompletionService 类提供了此方法的实现。

## ExecutorCompletionService
ExecutorCompletionService是CompletionService的重要实现类
AbstractExecutorService.doInvokeAny方法中有ExecutorCompletionService的用法