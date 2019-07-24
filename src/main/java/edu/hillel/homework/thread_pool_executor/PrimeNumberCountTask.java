package edu.hillel.homework.thread_pool_executor;

import java.util.concurrent.Callable;

public class PrimeNumberCountTask implements Callable<Integer> {

    private int startValue;
    private int finalValue;

    public PrimeNumberCountTask(int startValue, int finalValue) {
        this.startValue = startValue;
        this.finalValue = finalValue;
    }

    @Override
    public Integer call() throws Exception {
        Integer countOfPrimeNumber = 0;
        for (int i = startValue; i < finalValue; i++) {
            if(isPrimeNumber(i)){
                countOfPrimeNumber++;
            }
        }
        return countOfPrimeNumber;
    }


     boolean isPrimeNumber(int number){
        if(number == 1){
            return false;
        }
        for (int i = 2; i < number; i++) {
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
