import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/** Larissa Tyree, lbt2116 
 * Data Structures Fall 2019 
 * HW 5 **/ 


public class Graph<V> { 

   
    int x; 
    int y; 
    // Keep an index from node labels to nodes in the map
    protected Map<V, Vertex<V>> vertices; 

    /**
     * Construct an empty Graph.
     */
    public Graph() {
       vertices = new HashMap<V, Vertex<V>>();
    }

    /**
     * Retrieve a collection of vertices. 
     */  
    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }

    public void addVertex(V u) {
        addVertex(new Vertex<>(u));
        
    }
    
    public void addVertex(Vertex<V> v) {
        if (vertices.containsKey(v.name)) 
            throw new IllegalArgumentException("Cannot create new vertex with existing name.");
        vertices.put(v.name, v);
        
    }

    /**
     * Add a new edge from u to v.
     * Create new nodes if these nodes don't exist yet. 
     * @param u unique name of the first vertex.
     * @param w unique name of the second vertex.
     * @param cost cost of this edge. 
     */
    public void addEdge(V u, V w, Double cost) {
        if (!vertices.containsKey(u))
            addVertex(u);
        if (!vertices.containsKey(w))
            addVertex(w);

        vertices.get(u).addEdge(
            new Edge<>(vertices.get(u), vertices.get(w), cost)); 

    }

    public void addEdge(V u, V w) {
        addEdge(u,w,1.0);
    }

    public void printAdjacencyList() {
        for (V u : vertices.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(u.toString());
            sb.append(" -> [ ");
            for (Edge e : vertices.get(u).getEdges()){
                sb.append(e.target.name);
                sb.append(" ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }    
  
   /**
    * Add a bidirectional edge between u and v. Create new nodes if these nodes don't exist
    * yet. This method permits adding multiple edges between the same nodes.
    *
    * @param u  
    *          the name of the source vertex.
    * @param v 
    *          the name of the target vertex.
    * @param cost
    *          the cost of this edge
    */
    public void addUndirectedEdge(V u, V v, Double cost) {
        addEdge(u,v, cost);
        addEdge(v,u, cost);
    }
    
    //edge.source.x,
                        //edge.source.y, edge.target.x, edge.target.y

    /****************************
     * Your code follows here.  *
     ****************************/ 
    
   // helper method for computeAllEuclideanDistances() 
    public double computeEuclideanDistance(double a, double b, double c, double d) {
        return Math.sqrt(Math.pow(a - c, 2) + Math.pow(b - d, 2));
    }
    
       
    public void computeAllEuclideanDistances() {
       
     for(Vertex<V> vert: getVertices()){ // for all vertices 
        for(Edge edge: vert.getEdges()){ // for all edges to those vertices
           Vertex theVertex = edge.target;
            // find distances  
           edge.distance= (computeEuclideanDistance(vert.posX,
                 vert.posY, theVertex.posX, theVertex.posY));
         } 
     }
    }
    
    public void doDijkstra(V s) {
        
    PriorityQueue<Vertex<V>> queue = new PriorityQueue<Vertex<V>>();
   
    //first set distance and cost and visited
    for (Vertex<V> x : getVertices()) {
      x.visited = false;
      x.cost = Double.POSITIVE_INFINITY;
      x.backpointer = null;
    }

    
    Vertex<V> sV = vertices.get(s); // given vertex from name
    sV.visited = true;
    sV.cost = 0.0;
        
    queue.add(sV);
    Vertex<V> t; // used below as target vertex
    Vertex<V> next; // polled vertex
       
    while (!queue.isEmpty()) {
      next = queue.poll();          
      next.visited = true;
              
      for (Edge uv : next.getEdges()) {
        t = uv.target;
        if (((next.cost + uv.distance) < t.cost) &&  !t.visited ) {
          // then there exists a shorter path 
          // update t cost and backpointer 
          t.cost = uv.distance + next.cost;
          t.backpointer = next;        
          queue.add(t); //add t to front of queue 
           
          }
        }        
      }
       
   
        
  }

  

    // Part 3
    public List<Edge<V>> getDijkstraPath(V s, V t) {
       
        doDijkstra(s); // find all possible paths 
     
            List<Edge<V>> Dpath = new ArrayList<Edge<V>>();
           
            Vertex<V> destination = vertices.get(t); // get last vertex 
            Vertex<V> previous = destination.backpointer;  // next to last 
           
        
            while(previous != null){ // while not to start 
             for(Edge edge: previous.getEdges()){
                 // working backwords from t to s 
              if(edge.target.name.equals(destination.name)){
                 // use .equals method in vertex 
                 Dpath.add(edge); // create path 
                      break;
                   }
            }
            destination = previous; // move destination up a vertex 
            previous = previous.backpointer;
            }
           
            return Dpath; // reurn the path 
                }  

            }
