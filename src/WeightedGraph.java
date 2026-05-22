import java.util.ArrayList;

public class WeightedGraph {
    private ArrayList<ArrayList<Edge>> adjacencyList;
    private int vertices;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacencyList.get(from).add(new Edge(to, weight));
        adjacencyList.get(to).add(new Edge(from, weight)); // remove this line for directed graph
    }

    public void dijkstra(int start) {
        int[] distance = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        distance[start] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int current = findMinDistance(distance, visited);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.to;
                int weight = edge.weight;

                if (!visited[neighbor] && distance[current] != Integer.MAX_VALUE) {
                    int newDistance = distance[current] + weight;

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                    }
                }
            }
        }

        printDistances(start, distance);
    }

    private int findMinDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private void printDistances(int start, int[] distance) {
        System.out.println("Shortest distances from vertex " + start + ":");

        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("To vertex " + i + " = unreachable");
            } else {
                System.out.println("To vertex " + i + " = " + distance[i]);
            }
        }
    }
}
