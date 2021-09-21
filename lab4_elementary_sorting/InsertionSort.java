package lab4_elementary_sorting;

import lab1_algorithm_analysis.In;
import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

public class InsertionSort {
    public static void sort(int[] arr){
        int n = arr.length;

        /* Go through all elements of the array */
        for (int i = 0; i < n; i++)
        {
            int j = i;
            int valueToSort = arr[i];

            while(j > 0 && arr[j - 1] > valueToSort){
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = valueToSort;
        }
    }

    public static void sort(int[] arr,int low, int high){
        int n = arr.length;

        /* Go through all elements of the array */
        for (int i = low; i < high; i++)
        {
            int j = i;
            int valueToSort = arr[i];

            while(j > 0 && arr[j - 1] > valueToSort){
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = valueToSort;
        }
    }

    public static void printArray(int[] arr)
    {
        int n = arr.length;
        for (int j : arr) System.out.print(j + " ");

        System.out.println();
    }

    // Driver method
    public static void main(String[] args)
    {
        In input = new In("sorting_data/100KInts.txt");
        int[] arr = input.readAllInts();

        StdOut.println("Start");
        Stopwatch timer = new Stopwatch();
        sort(arr);
        StdOut.println("Elapsed Time: " + timer.elapsedTime());

//        printArray(arr);
    }
}
