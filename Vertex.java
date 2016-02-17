import java.util.ArrayList;

// Standard Vertex Objects
public class Vertex {
  ArrayList<Edge> neighbors;

  public Vertex(){
    this.neighbors = new ArrayList<Edge>();
  }
}

// Vertex Objects with positions
class Vertex2D extends Vertex {
  double x, y;

  // Empty constructor
  public Vertex2D(){
    super();
    this.x = 0.0;
    this.y = 0.0;
  }

  public Vertex2D(double x1, double y1){
    super();
    this.x = x1;
    this.y = y1;
  }
}

// Vertices inside the unit cube
class Vertex3D extends Vertex2D {
  double z;

  // Empty constructor
  public Vertex3D(){
    super();
    this.z = 0.0;
  }

  // Regular constructor
  public Vertex3D(double x1, double y1, double z1){
    super(x1, y1);
    this.z = z1;
  }
}

// Vertices inside the unit hypercube
class Vertex4D extends Vertex3D {
  double a;

  // Empty constructor
  public Vertex4D(){
    super();
    this.a = 0.0;
  }

  // Regular constructor
  public Vertex4D(double x1, double y1, double z1, double a1){
    super(x1, y1, z1);
    this.a = a1;
  }
}
