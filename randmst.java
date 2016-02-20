import java.util.*;

class randmst {

    static double x[][];
    public static void main(String[] args) {

        if (args.length != 4)
            return;

        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);

        CompleteGraph cgraph = new CompleteGraph(numpoints, dimension);
        for (int i =0; i < numpoints; i++) {
            for (int j=0; j<numpoints; j++) {
                if (cgraph.weight(i,j) >= 0) {
                    System.out.print(String.format("v%dv%d = %.2f  ", i, j, cgraph.weight(i,j)));
                }
            }
            System.out.println();
        }

        long startTime = System.nanoTime();
        // Prim's Algorithm
        MinHeap heap = new MinHeap(numpoints);         // initialize MinHeap of size numpoints
        boolean[] isInMST = new boolean[numpoints];    // if v[i] is true, vertex i has been included in MST
        
        while (!heap.isEmpty()) {
            int min_v = heap.deletemin();
            System.out.println("(" + min_v + "," + heap.vInMST + ")  "+  heap.min_dist);
            isInMST[min_v] = true; 
            for (int i = 0; i < numpoints; i++) {
                if (!isInMST[i]) {
                    double weight = cgraph.weight(min_v, i);
                    if (weight > 0 && weight < heap.dist[i]) {
                        heap.change(i, weight, min_v);
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000000.0);

    }

}
