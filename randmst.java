import java.util.*;

class randmst {

    static double x[][];
    public static void main(String[] args) {

        if (args.length != 4)  
            return;

        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);
        

        CompleteGraph c = new CompleteGraph(numpoints, dimension);
        for (int i =0; i < numpoints; i++) {
            for (int j=0; j<numpoints; j++) {
                if (c.weight(i,j) >= 0) {
                    System.out.print(String.format("v%dv%d = %.2f  ", i, j, c.weight(i,j)));
                }
            }
            System.out.println();
        }

        MinHeap heap = new MinHeap(numpoints);         // initialize MinHeap of size numpoints
        boolean[] isInMST = new boolean[numpoints];    // if v[i] is true, vertex i has been included in MST
        
        while (!heap.isEmpty()) {
            int v = heap.deletemin();
            System.out.println("(" + v + "," + heap.u + ")  "+  heap.min_dist);
            isInMST[v] = true;
            for (int i = 0; i < numpoints; i++) {
                if (!isInMST[i] && c.weight(v,i) < heap.dist[i]) {
                    heap.change(i, c.weight(v,i), v);
                }
            }
        }

    }

}