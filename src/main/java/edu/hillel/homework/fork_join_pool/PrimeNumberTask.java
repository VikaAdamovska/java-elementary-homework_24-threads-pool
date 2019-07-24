package edu.hillel.homework.fork_join_pool;

import java.util.concurrent.RecursiveTask;

public class PrimeNumberTask extends RecursiveTask<Integer> {

    private int[] data;
    private int initialValue;
    private int finalValue;
    private final int maxDataSize = 10_000;

    public PrimeNumberTask(int[] data) {
        this.data = data;
        initialValue = 0;
        finalValue = data.length;
    }

    public PrimeNumberTask(int[] data, int initialValue, int finalValue) {
        this.data = data;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
    }

    @Override
    protected Integer compute() {
        int countNumbers = 0;
        int length = finalValue - initialValue;
        if (length <= maxDataSize) {
            for (int i = initialValue; i < finalValue; i++) {
                if (primeNumber(data[i])) {
                    countNumbers++;
                }
            }
        } else {
            PrimeNumberTask task1 = new PrimeNumberTask(data, initialValue, initialValue + length / 2);
            PrimeNumberTask task2 = new PrimeNumberTask(data, initialValue + length / 2, finalValue);

            task1.fork();
            int part2 = task2.compute();
            int part1 = task1.join();
            countNumbers = part1 + part2;
        }
        return countNumbers;
    }

    public boolean primeNumber(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}