import java.util.*;

class CompleteGraph {

    private int[][] vertices;
    private double[][] weights;

    public CompleteGraph(int numpoints, int dimension) {

        // Last row is blank (maybe)
        vertices = new int[numpoints][];
        weights = new double[numpoints][];

        int[] temp_vertices = new int[numpoints];
        double[] temp_weights = new double[numpoints];

        Random r = new Random(System.nanoTime());

        // Randomly creates all the vertices for 2D and up
        double[][] coordinates = new double[numpoints][dimension];
        if (dimension > 0) {
            for (int v = 0; v < numpoints; v++) {
                System.out.print("v" + v + "=(");
                for (int d = 0; d < dimension; d++) {
                    coordinates[v][d] = r.nextDouble();
                     System.out.print(coordinates[v][d] + ", ");
                }
                System.out.println(")");
            }
        }

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
                    weight = r.nextDouble();
                }

                // CHANGE
                if (true) {         // edge elimination condition
                    temp_vertices[length] = v;
                    temp_weights[length] = weight;
                    length++;
                }
            }

            vertices[current_v] = new int[length];
            weights[current_v] = new double[length];

            for (int i = 0; i < length; i++) {
                vertices[current_v][i] = temp_vertices[i];
                weights[current_v][i] = temp_weights[i];
            }
        }

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                System.out.print(String.format("v%dv%d = %.2f  ", i, vertices[i][j], weights[i][j]));
            }
            System.out.println();
        }
    }

    // return weight of an edge between v1 and v2, if not found return -1 O(n)
    public double weight(int v1, int v2) {

        if (v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        else if (v1 == v2) {
            return -1.0;
        }

        for (int v = 0, len = vertices[v1].length ; v < v2 && v < len; v++) {
            if (vertices[v1][v] == v2) {
                return weights[v1][v];
            }
            else if (vertices[v1][v] > v2) {
                break;
            }
        }

        return -1.0;
    }


    // public static void main(String[] args) {
    //     long startTime = System.nanoTime();
    //     CompleteGraph c = new CompleteGraph(7, 3); // 10 vertices in 3D
    //     long endTime = System.nanoTime();
    //     // System.out.println((endTime-startTime)/1000000000.0);

    //     for (int i = 0; i < c.weights.length; i++) {
    //         for (int j = 0; j < c.weights[i].length; j++) {
    //             System.out.print(String.format("v%dv%d = %.2f  ", i, c.vertices[i][j], weights[i][j]));
    //         }
    //         System.out.println();
    //     }

    // }

}
