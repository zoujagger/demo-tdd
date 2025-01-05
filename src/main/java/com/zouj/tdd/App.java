package com.zouj.tdd;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "-------- Demo Katas ---------" );
        KataFizzBuzz fizz = new KataFizzBuzz(100);
        fizz.compute();
    }
}
