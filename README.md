# Homework 5 Programming - Implementing Dijkstra's Algorithm (50 pts)

Please remember that to submit the assignment you must click Mark as Complete under the Education menu in the toolbar.

Please make sure to look at the written portion of this assignment, which is available on Courseworks. 

## Before you start:
In this project you will work with a graph representing train connections between North American cities (the graph represents train routes in the popular board game [Ticket to Ride](https://boardgamegeek.com/boardgame/9209/ticket-ride).
The file `ttrvertices.txt` contains a list of cities and their (X,Y) coordinates on the map. These are the vertices in the graph. The file `ttredges.txt` lists direct connections between pairs of cities. These links are *bidirectional*, if you can travel from NewYork to Boston, you can get from Boston to NewYork. These are the edges of the graph.

For this assignment, we will use Codio's "Virtual Desktop" functionality. Virtual Desktop allows you to run GUI (graphical user interface) applications on Codio and interact with them in a browser window. You should already see a button labeled "Virtual Desktop" in your Codio menu bar. If not, perform the following steps:
 
* Click on Tools > Install Software
* Search for "X Server" and install the package -- this will take a couple of minutes. It may look like Codio got stuck, but it's installing the package as requested -- be patient. 
* Click on Project > Restart Box... and restart the project box. 

You are now ready to test the GUI. Compile all java sources and run the class Display. Then click on "Virtual Desktop" in your codio menu. This should display the map and a user interface. You can find more information about Virtual Desktop [here](https://codio.com/docs/ide/boxes/installsw/gui/).


In this problem you will implement Dijkstra's algorithm on the map.  We are providing a representation for the graph, as well as code to visualize the map and the output of your algorithm.

Carefully read through the classes `Vertex.java` and `Edge.java`, which represent the vertices and edges of a graph. 
You will only have to modify `Graph.java`, which represents a graph and will contain your implementation of Dijkstra's algorithm.  The graph representation is essentially identical to the adjacency lists based implementation discussed one discussed in class. You will need to use the instance variable `vertices`, which contains a mapping from city names to `Vertex` instances after the graph is read from the data files.
Note also that Graph<V> is generic. V is the data type used to label each vertex. For the train map, this will be a `Graph<String>`. 

### Part 1 (10 pts)
Initially the edge cost (distance) for all edges is set to 1.0. We need to compute the actual distances between the cities. The GUI already contains a button for this purpose, but it does not do anything. In the class `Graph`, implement the method `computeAllEuclideanDistances()` which should compute the euclidean distance between all cities that have a direct link and set the weights for the corresponding edges in the graph. The euclidean distance between two points (ux, uy) and (vx, vy) is defined as sqrt((ux-vx)^2 + (uy-vy)^2). You might want to use `java.lang.Math` to help with the calculation. After recompiling, clicking the button should now display the correct distances. 

### Part 2 (25 pts)
Implement the method `doDijkstra(String s)` in Graph.java. This method should implement Dijkstra's algorithm starting at the city with name s. Use the distances associated with the edges. The method should update the `distance` and `prev` instance variables of the `Vertex` objects. Because the shortest path cost associated with vertices already on the priority queue can change, we need a priority queue implementation that supports the decrease key operation, i.e. it can adjust the position of entries the heap if their cost changes. The provided BinaryHeap implementation (which you implemented on homework 4) automatically updates the position of the entry in the heap when you try to re-insert an existing item with a lower cost. You will have to modify the `Vertex` class to implement `Comparable`. You also need to add an appropriate `hashCode` and `equals` method to the Vertex class (hash only the name of the vertex, not the cost or other fields). 

### Part 3 (15 pts)
Implement the method `public List<Edge<V>> getDijkstraPath(V s, V t)`, which first calls `doDijkstra(s)` and then uses the `distance` and `prev` instance variables of the Vertex objects to find the shortest path between s and t. The result of this method should be a list of Edges on the path.
If implemented correctly, the "Draw Dijkstra's Path" button in the GUI should display the shortest path between New York and LA.
