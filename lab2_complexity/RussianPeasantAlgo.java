package lab2_complexity;

import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

import java.math.BigInteger;

public class RussianPeasantAlgo {
    public static void main(String[] args){
        // Test for correct output
        // 24 * 16 = 384
        StdOut.println("24 * 16:");
        Stopwatch timer1 = new Stopwatch();
        StdOut.println(rpAlgorithm(24,16));
        StdOut.println("elapsed time = " + timer1.elapsedTime() + "\n");

        StdOut.println("123 * 123:");
        Stopwatch timer2 = new Stopwatch();
        StdOut.println(rpAlgorithm(123,123));
        StdOut.println("elapsed time = " + timer2.elapsedTime()+ "\n");

        StdOut.println("123456 * 123456:");
        Stopwatch timer3 = new Stopwatch();
        StdOut.println(rpAlgorithm(123456,123456));
        StdOut.println("elapsed time = " + timer3.elapsedTime()+ "\n");

        StdOut.println("123456789 * 123456789:");
        Stopwatch timer4 = new Stopwatch();
        StdOut.println(rpAlgorithm(123456789,123456789));
        StdOut.println("elapsed time = " + timer4.elapsedTime()+ "\n");

        StdOut.println("123456789123L * 12345678:");
        Stopwatch timer5 = new Stopwatch();
        StdOut.println(rpAlgorithm(123456789123L,12345678));
        StdOut.println("elapsed time = " + timer5.elapsedTime()+ "\n");

    }

//    public static BigInteger rpAlgorithm(long input1, long input2){
//        BigInteger answer = BigInteger.ZERO;
//
//        while (input2 > 0){
//            // If second input is odd
//            if (input2 % 2 == 1){
//                answer = answer.add(BigInteger.valueOf(input1));
//            }
//
//            input1 *= 2;    //Double input1
//            input2 /= 2;    // Halve input2
//        }
//
//        return answer;
//    }

    public static long rpAlgorithm(long input1, long input2){
        long answer = 0;
        while (input2 > 0){
            // If second input is odd
            if (input2 % 2 == 1){
                answer += input1;
            }

            input1 *= 2;    //Double input1
            input2 /= 2;    // Halve input2
        }

        return answer;
    }
}
