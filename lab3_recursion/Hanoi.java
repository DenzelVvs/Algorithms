package lab3_recursion;

import lab1_algorithm_analysis.StdOut;
import lab1_algorithm_analysis.Stopwatch;

public class Hanoi {
    public static void main(String[] args) {
        // Test using different disk sizes
        // e.g. 5, 10, 15, 20
        int diskSize = 20;
        StdOut.println("Disk Size: " + diskSize);
        StdOut.println("Source: A\tDestination: C\tAuxiliary: B\n");

        Stopwatch timerHanoi = new Stopwatch();
        towersOfHanoi(diskSize,"A","C","B");
        StdOut.println("\nElapsed Time: " + timerHanoi.elapsedTime());
    }

    static void towersOfHanoi(int disk, String source, String dest, String auxiliary){
        try{
            if(disk <= 0){
                throw new IllegalArgumentException("Disk must be more than 0.");
            }else{
                if(disk == 1){      // Move disk from source to destination
                    StdOut.println("Move disk " + disk + " from " + source + " to " + dest);
                }else{
                    towersOfHanoi(disk - 1,source,auxiliary,dest);  // Move from source to auxiliary
                    StdOut.println("Move disk " + disk + " from " + source + " to " + dest);
                    towersOfHanoi(disk - 1,auxiliary,dest,source);  // Move from auxiliary to destination
                }
            }
        }catch(IllegalArgumentException e){
            StdOut.println("Disk size: " + disk + "\n" + e);
        }
    }
}
