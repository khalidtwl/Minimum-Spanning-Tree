import java.util.Arrays;

class MinHeap {

    public double[] dist;
    public int[] v_in_tree;

    public int min_v = -1;
    public double min_dist = -1.0;
    public int u = -1;


    public MinHeap(int size) {
        dist = new double[size];
        v_in_tree = new int[size];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
    }

    public void deletemin() {
        int v = 0;
        double min = dist[0];

        for (int i = 1, length = dist.length; i < length; i++) {
            if (dist[i] < min) {
                v = i;
                min = dist[i];
            }
        }
        dist[v] = Double.MAX_VALUE;
        min_v = v;
        min_dist = min;
        u = v_in_tree[v];
    }

    public void insert(int v, double dist, int u) {
        this.dist[v] = dist;
        v_in_tree[v] = u;
    }
}