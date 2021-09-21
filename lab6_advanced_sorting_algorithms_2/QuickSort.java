package lab6_advanced_sorting_algorithms_2;

import lab1_algorithm_analysis.In;
import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;
import lab4_elementary_sorting.InsertionSort;

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] array){
        shuffle(array);
        sort(array,0,array.length-1);
    }

    public static void enhancedSort(int[] array, int cutOff){
        shuffle(array);
        enhancedSort(array,0,array.length-1,cutOff);
    }

    private static void sort(int[] array, int start, int end){
        if(start < end){
            int pivot = partition(array,start,end);
            sort(array,start,pivot - 1);
            sort(array,pivot + 1, end);
        }
    }

    private static void enhancedSort(int[] array, int start, int end, int cutOff){
        if (start < end) {
            if(end - start < cutOff){
                InsertionSort.sort(array,start,end);
                return;
            }
            int pivot = partition(array,start,end);
            enhancedSort(array,start,pivot - 1,cutOff);
            enhancedSort(array,pivot + 1, end,cutOff);
        }
    }

    private static int partition(int[] array, int start, int end){
        int pivot = array[end];
        int pi = start;
        int i,temp;

        for(i = start; i <= end - 1; i++){
            if(array[i] <= pivot){
                temp = array[pi];
                array[pi] = array[i];
                array[i] = temp;
                pi++;
            }
        }

        temp = array[pi];
        array[pi] = array[end];
        array[end] = temp;

        return pi;
    }

    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4,6,2,1,7,5,8,1};
//
//        sort(arr);
//        System.out.println(Arrays.toString(arr));

        In input = new In("sorting_data/1KInts.txt");
        int[] arr = input.readAllInts();

//        InsertionSort.printArray(arr);

        StdOut.println("Start");
        Stopwatch timer = new Stopwatch();
//        sort(arr);
        enhancedSort(arr,6);
        StdOut.println("Elapsed Time: " + timer.elapsedTime());

//        InsertionSort.printArray(arr);
    }
}
