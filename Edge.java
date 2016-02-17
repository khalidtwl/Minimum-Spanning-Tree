/* This class defines Edge Objects
Edges have a weight and connect two vertices
*/

public class Edge{
  int weight;
  Vertex prev, next;

  // Empty constructor
  public Edge(){
    this.weight = 0;
    this.prev = new Vertex();
    this.next = new Vertex();
  }

  // Regular constructor
  public Edge(int val, Vertex a, Vertex b){
    this.weight = val;
    this.prev = a;
    this.next = b;
  }
}
