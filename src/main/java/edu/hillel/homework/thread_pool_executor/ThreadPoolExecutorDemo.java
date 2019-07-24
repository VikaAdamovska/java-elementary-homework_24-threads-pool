package edu.hillel.homework.thread_pool_executor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

        int startValue = 1;
        int finalValue = 1_000_000;
        int stride = 1000;
        Integer countOfPrimeNumber = 0;
        List<Future<Integer>> futureList;

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(threads,
                threads,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(finalValue / stride));


        futureList = new LinkedList<>();
        for (int i = startValue; i <= finalValue; i += stride) {

            int taskFinalValue;
            if(i + stride < finalValue){
                taskFinalValue = i + stride;
            } else {
                taskFinalValue = finalValue;
            }

            try {
                Future<Integer> fut = executorService.submit(new PrimeNumberCountTask(i, taskFinalValue));
                futureList.add(fut);
            } catch (RejectedExecutionException re) {
                System.out.println("Rejected exception occurs");
                re.printStackTrace();
            }
        }

        for (Future<Integer> future : futureList) {
            try {
                countOfPrimeNumber += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }

        System.out.println(String.format("The count of prime numbers of the array from %d to %d is: %d",
                startValue, finalValue, countOfPrimeNumber));
    }
}
