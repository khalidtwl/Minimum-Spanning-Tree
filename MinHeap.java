import java.util.Arrays;

class MinHeap {

    // dist[i] is the min distance between vertex i and some vertex in MST.
    public double[] dist;

    // the edge between vertex i and vertex mst_vertices[i] has length dist[i]
    private int[] mst_vertices;     

    public double min_dist = -1.0;
    public int u = -1;              // vertex in MST that is connected to min vertex

    private int size;

    public MinHeap(int size) {
        this.size = size;
        dist = new double[size];
        mst_vertices = new int[size];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
    }

    /***
     * Traverse dist[] array to find min dist[i] and return it, update
     * dist[i] to Double.MAX_VALUE to signify that we have included 
     * vertex i in MST and no longer need to find dist[i] again.
     * To get 
     **/
    public int deletemin() {
        int v = 0;
        double min = dist[0];

        for (int i = 1, length = dist.length; i < length; i++) {
            if (dist[i] < min) {
                v = i;
                min = dist[i];
            }
        }
        dist[v] = Double.MAX_VALUE;
        min_dist = min;
        u = mst_vertices[v];
        size--;

        return v;
    }

    /***
     * Assume v is not in MST. Change distance between v and some_mst_vertex.
     **/
    public void change(int v, double dist, int some_mst_vertex) {
        // change distance between v and some_mst_vertex
        this.dist[v] = dist;

        // mark that this distance is between v and some_mst_vertex
        mst_vertices[v] = some_mst_vertex;  
    }

    public boolean isEmpty() {
        return size == 0;
    }

}