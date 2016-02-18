import java.util.Random;

class CompleteGraph {

    // adjacency list is an array of linkedlist
    private LinkedList[] graph;


    public CompleteGraph(int numpoints, int dimension) {
        
        // initialize adjacency list to store weights between vertices
        graph = new LinkedList[numpoints];
        for (int v = 0; v < numpoints; v++) {
            graph[v] = new LinkedList();
        }

        Random r = new Random();

        // if dimension is 0, each edge is between 0 and 1
        if (dimension == 0) {
            for (int current_v = 0; current_v < numpoints; current_v++) {
                for (int v = current_v + 1; v < numpoints; v++) {
                    r.setSeed(System.nanoTime());
                    double weight = r.nextDouble();
                    graph[current_v].append(v, weight);
                    graph[v].append(current_v, weight);
                }
            }
        }
        // otherwise, randomly assign coordinates to vertices between (0,1)
        // and compute weights between edges
        else {

            // generate random coordinates for numpoints vertices
            double[][] vertices = new double[numpoints][dimension];
            for (int v = 0; v < numpoints; v++) {
                for (int d = 0; d < dimension; d++) {
                    r.setSeed(System.nanoTime());
                    vertices[v][d] = r.nextDouble();
                }
            }

            

            // calculate weight between all edges and put it in adjacency list 
            for (int current_v = 0; current_v < numpoints; current_v++) {
                
                for (int v = 0; v < numpoints; v++) {
                    if (v == current_v) {
                        continue;
                    }
                    double weight = 0;
                    for (int d = 0; d < dimension; d++) {
                        weight += Math.pow(vertices[current_v][d] - vertices[v][d], 2);
                    }
                    weight = Math.sqrt(weight);
                    graph[current_v].append(v, weight);
                }
            }
        }

    }

    // return weight of an edge between v1 and v2, if not found return -1
    public double weight(int v1, int v2) {
        return this.graph[v1].getWeight(v2);
    }


    public static void main(String[] args) {
        CompleteGraph c = new CompleteGraph(10, 3); // 10 vertices in 3D
        int length = c.graph.length;
        for (int i = 0; i < length; i++) {
            System.out.print("v" + i + ": ");
            c.graph[i].display();
        }

        System.out.println((String.format("v%dv%d = %.2f", 0, 5, c.weight(0,5))));
    }



    class LinkedList {

        public Node root;   // root points to the first element
        private Node head;  // head points to the last element

        public LinkedList() {
            root = null;
            head = null;
        }

        // append a new element to linkedlist 
        public void append(int vertex, double weight) {

            if (root == null) {
                root = new Node(vertex, weight);
                head = root;
                return;
            }

            head.next = new Node(vertex, weight);
            head = head.next;

        }

        // return weight, if vertex is not found return -1
        public double getWeight(int vertex) {
            double weight = -1;
            Node current_node = root;

            while (current_node != null) {
                if (current_node.vertex == vertex) {
                    weight = current_node.weight;
                }
                current_node = current_node.next;
            }

            return weight;
        }

        public void display() {
            Node current_node = root;
            while (current_node != null) {
                System.out.print(String.format("(v%d, w=%.2f) ", current_node.vertex, current_node.weight));
                current_node = current_node.next;
            }  
            System.out.println();          
        }

        class Node {
            public int vertex;
            public double weight;
            public Node next;
            public Node(int vertex, double weight) {
                this.vertex = vertex;
                this.weight = weight;
                this.next = null;
            }
        }

    }


}