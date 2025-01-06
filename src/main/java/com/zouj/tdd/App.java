package com.zouj.tdd;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "--------------------------------------------------" );
        System.out.println( "-------------------- Demo Katas ------------------" );
        System.out.println( "--------------------------------------------------\n" );
        
        System.out.println( "-------- FizzBuzz --------" );
        KataFizzBuzz fizz = new KataFizzBuzz(100);
        fizz.compute();

        System.out.println( "\n--------------------------------------------------\n" );
        System.out.println( "-------- Thermomètre --------" );




    }
}
