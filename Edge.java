public class Edge<V> implements Comparable<Edge<V>> {
  public Vertex<V> source;
  public Vertex<V> target;
  public Double distance;

  public Edge(Vertex<V> source, Vertex<V> target, Double theDistance) {
    this.source = source;
    this.target = target;
    this.distance = theDistance;
  }

  public Edge(Vertex<V> source, Vertex<V> target) {
    this(source, target, 1.0);
  }

  public int compareTo(Edge<V> other) {
    return distance.compareTo(other.distance);
  }
}