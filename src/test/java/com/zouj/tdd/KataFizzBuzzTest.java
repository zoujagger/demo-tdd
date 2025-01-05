package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KataFizzBuzzTest {

    @Test
    void shouldMap3IntegerIntoFizzString() {
        KataFizzBuzz fizz = new KataFizzBuzz(100);
        int number = 3333;

        String result = fizz.fizzBuzz(number);

        assertEquals("Fizz", result, () -> "should return Fizz");
    }

    @Test
    void shouldMap5IntegerIntoBuzztring() {
        KataFizzBuzz fizz = new KataFizzBuzz(100);
        int number = 100;

        String result = fizz.fizzBuzz(number);

        assertEquals("Buzz", result, () -> "should return Buzz");
    }

    @Test
    void shouldMap3and5IntegerIntoFizzBuzztring() {
        KataFizzBuzz fizz = new KataFizzBuzz(100);
        int number = 15;

        String result = fizz.fizzBuzz(number);

        assertEquals("FizzBuzz", result, () -> "should return FizzBuzz");
    }

    @Test
    void shouldComputeFrom1To100FizzBuzztring() {
        String expetedStr = "12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz1617Fizz19BuzzFizz2223FizzBuzz26Fizz2829FizzBuzz3132Fizz34BuzzFizz3738FizzBuzz41Fizz4344FizzBuzz4647Fizz49BuzzFizz5253FizzBuzz56Fizz5859FizzBuzz6162Fizz64BuzzFizz6768FizzBuzz71Fizz7374FizzBuzz7677Fizz79BuzzFizz8283FizzBuzz86Fizz8889FizzBuzz9192Fizz94BuzzFizz9798FizzBuzz";
        KataFizzBuzz fizz = new KataFizzBuzz(100);

        String result = fizz.compute();

        assertEquals(expetedStr, result, () -> "should return FizzBuzz");
    }

}
