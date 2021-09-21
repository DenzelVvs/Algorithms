package lab3_recursion;

import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

public class Fibonacci {
    public static void main (String[] args)
    {
        // Try for different values of N:
        // e.g. 0, 1, 10, 20, 30, 40, 41, 42, 43, 44, 45
        int n = 45;
        StdOut.println("N = " + n + "\n");

        Stopwatch timerIterative = new Stopwatch();
        StdOut.println(fibonacciIterative(n));
        StdOut.println("Iterative Elapsed Time: " + timerIterative.elapsedTime() + "\n");

        Stopwatch timerRecursive = new Stopwatch();
        StdOut.println(fibonacciRecursive(n));
        StdOut.println("Recursive Elapsed Time: " + timerRecursive.elapsedTime() + "\n");
    }

    static int fibonacciIterative(int n){
        if (n < 1)
            return 0;

        int fib = 1;
        int prevFib =  1;

        // Goes through the sequence until it reaches N
        for (int i = 2; i < n; i++) {
            // fib becomes the next value in the sequence (current value of fib + prevFib)
            // prevFib becomes previous value of fib
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    static int fibonacciRecursive(int n){
        if(n == 0){         // Base case: first number of the Fibonacci sequence ( N = 0 )
            return 0;
        }else if(n == 1){   // Base case: second number of the Fibonacci sequence ( N = 1 )
            return 1;
        }else{
            return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
        }
    }

}
