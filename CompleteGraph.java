import java.util.*;

class CompleteGraph
{
    // data strcture to store a complte graph, vertices[i][j] = k and
    // weights[i][j] = w means that edge {i,k} has weight w
    private int[][] vertices;
    private double[][] weights;

    public CompleteGraph(int numpoints, int dimension)
    {
      // Last row is blank (maybe)
      vertices = new int[numpoints][];
      weights = new double[numpoints][];

      int[] temp_vertices = new int[numpoints];
      double[] temp_weights = new double[numpoints];

      Random rand = new Random(System.nanoTime());

      // any edge with weight > r is thrown away. Please see the report for more detailed derivation
      double r = Double.MAX_VALUE;
      if (numpoints >= 2)
      {
        double epsilon = 1e-16;
        switch (dimension)
        {
          case 0: r = Math.sqrt(2.0 * (1.0 - Math.pow(epsilon, 1.0/((double)numpoints - 2.0))));
                  break;
          case 2: r = Math.sqrt(4.0/Math.PI * (1.0 - Math.pow(epsilon,1.0/((double)numpoints-1.0))));
                  break;
          case 3: r = Math.pow(6.0/Math.PI * (1.0 - Math.pow(epsilon,1.0/((double)numpoints-1.0))), 1.0/3.0);
                  break;
          case 4: r = Math.pow(8.0/Math.PI * (1.0 - Math.pow(epsilon,1.0/((double)numpoints-1.0))), 1.0/4.0);
          default: break;
        }
      }
      // System.out.println("r=" + r);

      // Randomly creates all the vertices for 2D and up
      double[][] coordinates = new double[numpoints][dimension];
      if (dimension > 0)
      {
        for (int v = 0; v < numpoints; v++)
        {
          // System.out.print("v" + v + "=(");
          for (int d = 0; d < dimension; d++)
          {
            coordinates[v][d] = rand.nextDouble();
            // System.out.print(coordinates[v][d] + ", ");
          }
          // System.out.println(")");
        }
      }
      int total = 0;
      // calculate weights and store then in weights[][] and vertices[][]
      for (int current_v = 0; current_v < numpoints; current_v++) {
          int length = 0;
          for (int v = current_v + 1; v < numpoints; v++) {
              double weight = 0;
              if (dimension > 0) {
                  for (int d = 0; d < dimension; d++) {
                      double diff = coordinates[current_v][d] - coordinates[v][d];
                      weight += diff * diff;
                  }
                  weight = Math.sqrt(weight);
              }
              else if (dimension == 0) {
                  weight = rand.nextDouble();
              }

              if (weight < 0.5) {
                  total++;
              }

              if (weight < r) {         // edge elimination condition
                  temp_vertices[length] = v;
                  temp_weights[length] = weight;
                  length++;
                  total++;
              }
          }

          vertices[current_v] = new int[length];
          weights[current_v] = new double[length];

          for (int i = 0; i < length; i++) {
              vertices[current_v][i] = temp_vertices[i];
              weights[current_v][i] = temp_weights[i];
          }
      }
      // System.out.println("Number of edges: " + total);
    }

    /***
     * return weight of an edge between v1 and v2, if not found return -1
     **/
    public double weight(int v1, int v2)
    {
      if (v1 > v2)
      {
          int temp = v1;
          v1 = v2;
          v2 = temp;
      }
      else if (v1 == v2)
          return -1.0;
      int i = 0, length = vertices[v1].length;
      while (i < v2 && i < length)
      {
          int v = vertices[v1][i];
          if (v == v2)
              return weights[v1][i];
          else if (v > v2)
              break;
          i++;
      }
      return -1.0;
    }

    // Prettily prints out the complete graph
    public void printGraph()
    {
      System.out.println("Complete Graph:");
      for (int i = 0; i < weights.length; i++)
      {
        for (int j = 0; j < weights[i].length; j++)
        {
            System.out.print(String.format("v%dv%d = %.2f  ", i, vertices[i][j], weights[i][j]));
        }
        System.out.println();
      }
    }

}
