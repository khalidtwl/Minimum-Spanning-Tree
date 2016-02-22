import java.util.*;

class randmst
{
    static double x[][];
    public static void main(String[] args)
    {
      if (args.length != 4)
      {
        System.out.println("Invalid number of arguments.");
        return;
      }

      // Command-Line Arguments
      int numpoints = Integer.parseInt(args[1]);
      int numtrials = Integer.parseInt(args[2]);
      int dimension = Integer.parseInt(args[3]);

      if (dimension < 0 || dimension == 1 || dimension > 4)
      {
        System.out.println("Invalid Dimension");
        return;
      }

      if (numpoints < 1 || numtrials < 1)
      {
        System.out.println("Invalid number of points/trials");
        return;
      }

      double average = 0;
      for (int trials = 0; trials < numtrials; trials++)
      {
        // Generates a complete graph
        CompleteGraph cgraph = new CompleteGraph(numpoints, dimension);

        // for time measurement
        // long startTime = System.nanoTime();

        // Prim's Algorithm starts here
        // Initializes a MinHeap
        MinHeap heap = new MinHeap(numpoints);

        // Keeps track of whether vertex i is in our MST
        boolean[] isInMST = new boolean[numpoints];

        double weight_of_tree = 0.0;

        while (!heap.isEmpty())
        {
          // Pop off the closest neighbor
          int min_v = heap.deletemin();
          weight_of_tree += heap.min_dist;
          // System.out.println("(" + min_v + "," + heap.vInMST + ")  "+  heap.min_dist);

          // Mark that the chosen vertex is now in our MST
          isInMST[min_v] = true;
          for (int i = 0; i < numpoints; i++)
          {
              if (!isInMST[i])
              {
                  double weight = cgraph.weight(min_v, i);
                  if (weight > 0 && weight < heap.dist[i])
                      heap.change(i, weight, min_v);
              }
          }
        }
        average += weight_of_tree;
        // For Time Measurements, uncomment
        // long endTime = System.nanoTime();
        // System.out.println("Total Time: " + (endTime-startTime)/1000000000.0 + " seconds.");
      }
      System.out.println(average/numtrials + " " + numpoints + " " + numtrials + " " + dimension);
    }
}
