package edu.hillel.homework.fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        int initialValue = 1;
        int finalValue = 1_000_000;
        int[] data = IntStream.rangeClosed(initialValue, finalValue).toArray();
        int countOfPrimeNumber = new ForkJoinPool().invoke(new PrimeNumberTask(data));
        System.out.println(String.format("The sum of prime numbers of the array from %d to %d is: %d",
                initialValue, finalValue, countOfPrimeNumber));

    }
}
