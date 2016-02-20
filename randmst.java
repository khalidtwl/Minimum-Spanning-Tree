import java.util.*;

class randmst {

    static double x[][];
    public static void main(String[] args) {

        if (args.length != 4)
            return;

        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);

        long startTime = System.nanoTime();
        CompleteGraph c = new CompleteGraph(numpoints, dimension);
        for (int i =0; i < numpoints; i++) {
            for (int j=0; j<numpoints; j++) {
                if (c.weight(i,j) >= 0) {
                    System.out.print(String.format("v%dv%d = %.2f  ", i, j, c.weight(i,j)));
                }
            }
            System.out.println();
        }

        // Prim's Algorithm
        MinHeap h = new MinHeap(numpoints);
        boolean[] isInTree = new boolean[numpoints];
        int v = -1;
        int n = numpoints;
        while(n != 0) {
            h.deletemin();
            v = h.min_v;
            System.out.println("(" + v + "," + h.u + ")  "+  h.min_dist);
            isInTree[v] = true;
            for (int i = 0; i < numpoints; i++) {
                // CHANGE?
                if (isInTree[i] == false && c.weight(v,i) < h.dist[i] && c.weight(v,i) > 0) {
                    h.insert(i, c.weight(v,i), v);
                }
            }
            n--;
        }

        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000000.0);

    }

}
