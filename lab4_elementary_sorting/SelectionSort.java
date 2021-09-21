package lab4_elementary_sorting;

import lab1_algorithm_analysis.In;
import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

public class SelectionSort {
    private void sort(int[] arr) {
        int n = arr.length;

        /* Go through all elements of the array */
        for (int i = 0; i < n-1; i++)
        {
            /* Find the minimum element to the right min_index */
            int min_index = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_index])
                    min_index = j;

            /* Swap the minimum element with a[i] */
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        In input = new In("sorting_data/100KInts.txt");
        int[] arr = input.readAllInts();
        SelectionSort ob = new SelectionSort();

        StdOut.println("Start");
        Stopwatch timer = new Stopwatch();
        ob.sort(arr);
        StdOut.println("Elapsed Time: " + timer.elapsedTime());

//        InsertionSort.printArray(arr);
    }
}
