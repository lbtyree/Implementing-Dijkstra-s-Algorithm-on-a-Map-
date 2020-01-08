import java.util.LinkedList;
import java.util.List;
import java.io.*; 
import java.util.*; 

public class Vertex<V> implements Comparable<Vertex<V>>{ //Comparable<V>
  public V name;
  private List<Edge<V>> adjacent;
  public int posX = 0;
  public int posY = 0;

  // Variables used by BFS, Dijkstra's, and Prim's
  public boolean visited;
  public double cost;   
  public Vertex backpointer; // use this instance variable for the 
                             // prev pointers used by BFS and Dijkstras, 
                             // and for the parent pointer used by Prim's

  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   * @param x
   *          the x coordinate for this vertex
   * @param y
   *          the y coordinate for this vertex
   */
  public Vertex(V vertexName, int x, int y) {
    name = vertexName;
    adjacent = new LinkedList<Edge<V>>();
    posX = x;
    posY = y;
  }

  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   */
  public Vertex(V vertexName) {
    this(vertexName, 0, 0);
  }

  /**
   * Retrieve the list of edges connected to this vertex.
   * 
   * @return a list of edges connected to this vertex.
   */
  public List<Edge<V>> getEdges() {
    return adjacent;
  }

  /**
   * Connect an edge to this vertex.
   * 
   * @param e
   *          The new edge to connect to this vertex.
   */
  public void addEdge(Edge<V> e) {
    adjacent.add(e);
  }

  public String toString() {
    return name.toString();
  }
    
  public boolean equals(Object other) {
        if (other instanceof Vertex) {
            Vertex otherVertex = (Vertex) other;
            return this.name.equals(otherVertex.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override 
    public int compareTo(Vertex<V> x) {
     if (this.name.equals(x.name)==true) {
         return 0;
         
     }else{
         return 1; 
     }
        
    }
    
    
    
    

}
