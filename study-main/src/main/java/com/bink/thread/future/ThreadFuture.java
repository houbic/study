package com.bink.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by chenbinghao on 16/7/14.
 */
public class ThreadFuture {

    public ThreadFuture() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future> futureList = new ArrayList<>();
        futureList.add(executorService.submit(new MyThread(4)));
        futureList.add(executorService.submit(new MyThread(3)));
        for (Future future : futureList) {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new ThreadFuture();
    }

}
class MyThread implements Callable<Integer> {

    private Integer value;

    /**
     * @param value
     */
    public MyThread(Integer value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("%s is working", threadName));
        TimeUnit.SECONDS.sleep(value);
        System.out.println(String.format("%s is finished", threadName));
        return value;
    }
}
