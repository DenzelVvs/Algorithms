package lab4_elementary_sorting;

import lab1_algorithm_analysis.In;
import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

public class BogoSort {

    boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    private void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    private void sort(int[] arr){
        while(!isSorted(arr)){
            shuffle(arr);
        }
    }

    public static void main(String[] args) {
        // Don't try BogoSort on bigger datasets, it's a waste of time...
        In input = new In("sorting_data/10Ints.txt");
        int[] arr = input.readAllInts();
        BogoSort ob = new BogoSort();

        StdOut.println("Start");
        Stopwatch timer = new Stopwatch();
        ob.sort(arr);
        StdOut.println("Elapsed Time: " + timer.elapsedTime());

//        InsertionSort.printArray(arr);
    }

}
