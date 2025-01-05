package com.zouj.tdd;

public class KataFizzBuzz {

    private int counter;

    KataFizzBuzz(int count) {
        this.counter = count;
    }

    public String fizzBuzz(int number) {

        if ((number % 3 == 0) && (number % 5 == 0)) {
            return "FizzBuzz";
        }

        if (number % 3 == 0) {
            return "Fizz";
        }

        if (number % 5 == 0) {
            return "Buzz";
        }

        return String.valueOf(number);
    }

    public String compute() {
        String result = "";
        for (int i = 1; i <= counter; i++) {
            result += fizzBuzz(i);
            
            if (i == counter)
                System.out.println(result);
        }
        return result;
    }

}
