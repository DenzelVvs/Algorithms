package lab5_advanced_sorting_algorithms_1;

import lab1_algorithm_analysis.In;
import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;
import lab4_elementary_sorting.InsertionSort;

import java.util.Arrays;

public class MergeSort {
    private static void merge(int[] a, int lo, int mid, int hi){
        int[] aux = new int[a.length];
        // Copying array 'a' into auxiliary array
        System.arraycopy(a, 0, aux, 0, a.length);

        // Starting index of sub-arrays
        int i = lo;
        int j = mid + 1;

        // Copying from auxiliary array back into array 'a'
        for(int k = lo; k < hi + 1; k++){
            if(i < mid + 1 && j < hi + 1){
                if(aux[i] > aux[j]){
                    a[k] = aux[j++];
                }else if(aux[i] <= aux[j]){
                    a[k] = aux[i++];
                }
            }else if(j <= hi){
                // If all elements from the left sub-array have been copied
                a[k] = aux[j++];
            }else if(i <= mid){
                // If all elements from the right sub-array have been copied
                a[k] = aux[i++];
            }
        }
    }

    public static void mergeSort(int[] array){
        mergeSort(array,0, array.length - 1);
    }

    private static void mergeSort(int[] array, int lo, int hi){
        if(lo < hi){
            try{
                int mid = (lo + hi)/2;
                mergeSort(array,lo,mid);            // Sort left sub-array
                mergeSort(array,mid + 1,hi);    // Sort right sub-array
                merge(array,lo,mid,hi);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void mergeSortEnhanced(int[] array,int cutOff){
        mergeSortEnhanced(array,0, array.length - 1,cutOff);
    }

    private static void mergeSortEnhanced(int[] array, int lo, int hi, int cutOff){
        if(lo < hi){
            try{
                int mid = (lo + hi)/2;
                mergeSort(array,lo,mid);            // Sort left sub-array
                mergeSort(array,mid + 1,hi);    // Sort right sub-array
                merge(array,lo,mid,hi);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(lo + cutOff >= hi){
            InsertionSort.sort(array);
        }
    }

    public static void main(String[] args) {
//        int[] array = new int[]{5, 6, 8, 2, 4, 1, 7, 3};
//        int[] array2 = new int[]{5, 6, 8, 2, 4, 1, 7, 3};
//        mergeSort(array, 0, array.length-1);
//        InsertionSort.sort(array2);
//        InsertionSort.printArray(array);
//        InsertionSort.printArray(array2);

        In input = new In("sorting_data/1KInts.txt");
        int[] arr = input.readAllInts();

//        InsertionSort.printArray(arr);

        StdOut.println("Start");
        Stopwatch timer = new Stopwatch();
//        mergeSort(arr);
        mergeSortEnhanced(arr,15);
        StdOut.println("Elapsed Time: " + timer.elapsedTime());

//        InsertionSort.printArray(arr);
    }
}
