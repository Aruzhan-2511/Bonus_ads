# Bonus Task: Dijkstra’s Algorithm

## Overview

This repository contains my bonus task implementation for the course **Algorithms and Data Structures**.

The goal of this bonus task is to implement **Dijkstra’s Algorithm** in Java.  
Dijkstra’s Algorithm is used to find the shortest distance from one starting vertex to all other vertices in a weighted graph.

This implementation uses:

- Java
- Weighted graph
- `Edge` class with a weight field
- Adjacency list
- Arrays for distances and visited vertices
- Simple loops
- No priority queue

---

## Task Description

The bonus task required implementing Dijkstra’s Algorithm with the following method:

```java
void dijkstra(int start)
```

The method takes a starting vertex and computes the shortest distance from that vertex to all other vertices in the graph.

---

## Requirements Completed

| Requirement | Status | Explanation |
|---|---|---|
| Extend graph to support edge weights | Completed | The graph stores weighted edges. |
| Modify Edge class to include weight field | Completed | The `Edge` class has a `weight` variable. |
| Update graph structure to store weighted edges | Completed | The graph uses an adjacency list with `Edge` objects. |
| Implement `void dijkstra(int start)` | Completed | The method is implemented in `WeightedGraph.java`. |
| Take a starting vertex | Completed | The start vertex is passed as a parameter. |
| Compute shortest distance to all vertices | Completed | The algorithm calculates distances from the start vertex. |
| Output results clearly | Completed | The program prints shortest distances to every vertex. |
| Use arrays for distances and visited nodes | Completed | The program uses `int[] distance` and `boolean[] visited`. |
| Use simple loops | Completed | The algorithm finds the minimum distance vertex using a loop. |
| No priority queue required | Completed | No `PriorityQueue` is used. |

---

## Project Structure

```text
Bonus_ads
│
├── README.md
└── src
    ├── Edge.java
    ├── WeightedGraph.java
    └── Main.java
```

---

## File Explanation

### `Edge.java`

This file contains the `Edge` class.

The `Edge` class represents a weighted connection between vertices.

```java
public class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
```

The class has two fields:

- `to` — the destination vertex
- `weight` — the cost of moving to that vertex

For example, if we have an edge from vertex `0` to vertex `1` with weight `4`, it means the cost of going from `0` to `1` is `4`.

---

### `WeightedGraph.java`

This file contains the main graph logic.

The graph is stored using an adjacency list:

```java
private ArrayList<ArrayList<Edge>> adjacencyList;
```

This means every vertex has its own list of connected weighted edges.

For example:

```text
Vertex 0 → Edge to 1 with weight 4
Vertex 0 → Edge to 2 with weight 2
```

This is better than storing only numbers because each edge also stores its weight.

---

### `Main.java`

This file creates a sample graph and runs Dijkstra’s Algorithm.

```java
public class Main {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);

        graph.dijkstra(0);
    }
}
```

---

## Graph Representation

The graph has 5 vertices:

```text
0, 1, 2, 3, 4
```

The weighted edges are:

```java
graph.addEdge(0, 1, 4);
graph.addEdge(0, 2, 2);
graph.addEdge(1, 2, 1);
graph.addEdge(1, 3, 5);
graph.addEdge(2, 3, 8);
graph.addEdge(2, 4, 10);
graph.addEdge(3, 4, 2);
```

The graph can be understood like this:

```text
        4
   0 -------- 1
   |        / |
 2 |     1 /  | 5
   |      /   |
   2 -------- 3
    \   8     |
 10  \        | 2
      \       |
        ----- 4
```

This graph is undirected, so if there is an edge from `0` to `1`, there is also an edge from `1` to `0`.

---

## How Edges Are Added

The `addEdge` method adds a weighted edge between two vertices.

```java
public void addEdge(int from, int to, int weight) {
    adjacencyList.get(from).add(new Edge(to, weight));
    adjacencyList.get(to).add(new Edge(from, weight));
}
```

The first line adds the edge from `from` to `to`.

The second line adds the reverse edge from `to` to `from`.

That is why this graph is undirected.

Example:

```java
graph.addEdge(0, 1, 4);
```

This creates:

```text
0 → 1 with weight 4
1 → 0 with weight 4
```

---

## Dijkstra’s Algorithm Explanation

Dijkstra’s Algorithm finds the shortest distance from one starting vertex to all other vertices.

The main idea is:

1. Start from one vertex.
2. Set the distance to the starting vertex as `0`.
3. Set the distance to all other vertices as infinity.
4. Choose the unvisited vertex with the smallest current distance.
5. Check all neighbors of that vertex.
6. If a shorter path to a neighbor is found, update the distance.
7. Repeat until all reachable vertices are processed.

---

## Arrays Used in the Algorithm

### Distance Array

```java
int[] distance;
```

The `distance` array stores the shortest known distance from the starting vertex to every other vertex.

At the beginning:

```text
distance[start] = 0
all other distances = infinity
```

Example:

```text
distance[0] = 0
distance[1] = 3
distance[2] = 2
distance[3] = 8
distance[4] = 10
```

This means:

- The shortest distance from vertex `0` to vertex `0` is `0`.
- The shortest distance from vertex `0` to vertex `1` is `3`.
- The shortest distance from vertex `0` to vertex `2` is `2`.
- The shortest distance from vertex `0` to vertex `3` is `8`.
- The shortest distance from vertex `0` to vertex `4` is `10`.

---

### Visited Array

```java
boolean[] visited;
```

The `visited` array shows whether a vertex has already been processed.

Example:

```text
visited[0] = true
visited[1] = false
```

This means:

- Vertex `0` has already been processed.
- Vertex `1` has not been processed yet.

---

## Main Dijkstra Method

The main algorithm is implemented in this method:

```java
public void dijkstra(int start)
```

The `start` parameter is the vertex where the algorithm begins.

Example:

```java
graph.dijkstra(0);
```

This means the algorithm starts from vertex `0` and finds the shortest distances from vertex `0` to all other vertices.

---

## Finding the Smallest Unvisited Vertex

The task allowed using simple loops, so this implementation does not use a priority queue.

Instead, it uses this method:

```java
private int findMinDistance(int[] distance, boolean[] visited)
```

This method checks all vertices and returns the unvisited vertex with the smallest current distance.

If there are no reachable unvisited vertices, it returns `-1`.

---

## Step-by-Step Example

The starting vertex is:

```text
0
```

Initial distances:

```text
Vertex 0 = 0
Vertex 1 = infinity
Vertex 2 = infinity
Vertex 3 = infinity
Vertex 4 = infinity
```

---

### Step 1: Start from vertex 0

Neighbors of vertex `0`:

- Vertex `1` with weight `4`
- Vertex `2` with weight `2`

Updated distances:

```text
Vertex 0 = 0
Vertex 1 = 4
Vertex 2 = 2
Vertex 3 = infinity
Vertex 4 = infinity
```

---

### Step 2: Visit vertex 2

The smallest unvisited distance is vertex `2` with distance `2`.

Neighbors of vertex `2`:

- Vertex `1` with weight `1`
- Vertex `3` with weight `8`
- Vertex `4` with weight `10`

Calculations:

```text
Distance to vertex 1 through vertex 2 = 2 + 1 = 3
Distance to vertex 3 through vertex 2 = 2 + 8 = 10
Distance to vertex 4 through vertex 2 = 2 + 10 = 12
```

Updated distances:

```text
Vertex 0 = 0
Vertex 1 = 3
Vertex 2 = 2
Vertex 3 = 10
Vertex 4 = 12
```

The distance to vertex `1` changed from `4` to `3` because the path `0 → 2 → 1` is shorter than the direct path `0 → 1`.

---

### Step 3: Visit vertex 1

The smallest unvisited distance is vertex `1` with distance `3`.

Neighbor of vertex `1`:

- Vertex `3` with weight `5`

Calculation:

```text
Distance to vertex 3 through vertex 1 = 3 + 5 = 8
```

This is better than the previous distance `10`.

Updated distances:

```text
Vertex 0 = 0
Vertex 1 = 3
Vertex 2 = 2
Vertex 3 = 8
Vertex 4 = 12
```

---

### Step 4: Visit vertex 3

The smallest unvisited distance is vertex `3` with distance `8`.

Neighbor of vertex `3`:

- Vertex `4` with weight `2`

Calculation:

```text
Distance to vertex 4 through vertex 3 = 8 + 2 = 10
```

This is better than the previous distance `12`.

Final distances:

```text
Vertex 0 = 0
Vertex 1 = 3
Vertex 2 = 2
Vertex 3 = 8
Vertex 4 = 10
```

---

## Expected Output

When the starting vertex is `0`, the program prints:

```text
Shortest distances from vertex 0:
To vertex 0 = 0
To vertex 1 = 3
To vertex 2 = 2
To vertex 3 = 8
To vertex 4 = 10
```

---

## How to Run the Program

Compile the Java files:

```bash
javac src/*.java
```

Run the program:

```bash
java -cp src Main
```

---

## Why This Implementation Matches the Task

This implementation matches the bonus task requirements because it clearly supports weighted edges and uses Dijkstra’s Algorithm to calculate shortest paths.

The graph is extended to store weights by using the `Edge` class.  
Each `Edge` object stores the destination vertex and the edge weight.

The shortest distances are calculated using:

```java
int[] distance;
```

The processed vertices are tracked using:

```java
boolean[] visited;
```

The algorithm does not use a priority queue.  
Instead, it uses a simple loop to find the unvisited vertex with the smallest distance.

---

## Important Note

Dijkstra’s Algorithm works correctly only when all edge weights are non-negative.

This implementation should not be used with negative edge weights.  
For graphs with negative weights, another algorithm such as Bellman-Ford should be used.

---

## Conclusion

In this bonus task, I implemented Dijkstra’s Algorithm in Java using a weighted adjacency list.

The program:

- Creates a weighted graph
- Stores edges with weights
- Uses an `Edge` class
- Implements `void dijkstra(int start)`
- Finds the shortest distance from the starting vertex to every other vertex
- Prints the result clearly
- Uses arrays and simple loops
- Does not use a priority queue

This completes the bonus task requirements.
