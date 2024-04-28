
package com.eniso.projetjava;

import java.util.*;


class UndirectedGraph extends Graph {

    public UndirectedGraph() {
        super();
    }

    void addVertex(int vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }
    @Override
    void addEdge(int vertex1, int vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        edges.add(new int[]{vertex1, vertex2});
    }
    void printGraph() {
        System.out.println("Undirected Graph:");
        for (int[] edge : edges) {
            System.out.println(edge[0] + " ---- " + edge[1]);
        }
    }

  
   @Override
    List<Integer> BFS(int startVertex) {
           List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.offer(startVertex);
        seen.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            visited.add(currentVertex);

            List<Integer> neighbors = getNeighbors(currentVertex);
            for (int neighbor : neighbors) {
                if (!seen.contains(neighbor)) {
                    queue.offer(neighbor);
                    seen.add(neighbor);
                }
            }
        }

        return visited;
    }

    // Helper method to get neighbors of a vertex
    private List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        for (int[] edge : edges) {
            if (edge[0] == vertex) {
                neighbors.add(edge[1]);
            } else if (edge[1] == vertex) {
                neighbors.add(edge[0]);
            }
        }
        return neighbors;
   
    }
       @Override
    public List<Integer> DFS(int startVertex) {
        List<Integer> visited = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        dfsHelper(startVertex, visited, seen);
        return visited;
    }

    private void dfsHelper(int vertex, List<Integer> visited, Set<Integer> seen) {
        visited.add(vertex);
        seen.add(vertex);
        for (int neighbor : getNeighbors(vertex)) {
            if (!seen.contains(neighbor)) {
                dfsHelper(neighbor, visited, seen);
            }
        }
    }

    @Override
    // Dijkstra's Algorithm
public Map<Integer, Integer> dijkstra(int startVertex) {
    Map<Integer, Integer> distances = new HashMap<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    Set<Integer> visited = new HashSet<>();

    // Initialize distances with infinity and source with 0
    for (int vertex : vertices) {
        distances.put(vertex, Integer.MAX_VALUE);
    }
    distances.put(startVertex, 0);
    pq.offer(new int[]{startVertex, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int vertex = current[0];
        int distToVertex = current[1];

        if (!visited.contains(vertex)) {
            visited.add(vertex);
            for (int[] edge : edges) {
                int neighbor = (edge[0] == vertex) ? edge[1] : (edge[1] == vertex ? edge[0] : -1);
                if (neighbor != -1) {
                    int weight = edge[2];
                    int newDistance = distToVertex + weight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        pq.offer(new int[]{neighbor, newDistance});
                    }
                }
            }
        }
    }
    return distances;
}

    @Override
    ArrayList<ArrayList<Integer>> convertAdjacencyMatrixToAdjacencyList(int[][] a) {
        // no of vertices
        int l = a[0].length;
        ArrayList<ArrayList<Integer>> adjListArray 
        = new ArrayList<ArrayList<Integer>>(l);
 
        // Create a new list for each
        // vertex such that adjacent
        // nodes can be stored
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }
         
        int i, j;
        for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a.length; j++) {
                if (a[i][j] != 0) {
                    adjListArray.get(i).add(j);
                }
            }
        }
         
        return adjListArray;
    }
     
    // Function to print the adjacency list
    static void printArrayList(ArrayList<ArrayList<Integer>> 
                                                adjListArray)
    {
        // Print the adjacency list
        for (int v = 0; v < adjListArray.size(); v++) {
            System.out.print(v);
            for (Integer u : adjListArray.get(v)) {
                System.out.print(" -> " + u);
            }
            System.out.println();
        }
    }

    @Override
     public int[][] convertAdjacencyListToAdjacencyMatrix(Vector<Integer> adj[],int V){
        int [][]matrix = new int[V][V];
 
    for(int i = 0; i < V; i++) 
    {
        for(int j : adj[i])
            matrix[i][j] = 1;
    }
    return matrix;
}
 
// Function to display adjacency matrix
static void printMatrix(int[][] adj, int V)
{
    for(int i = 0; i < V; i++) 
    {
        for(int j = 0; j < V; j++)
        {
            System.out.print(adj[i][j] + " ");
        }
        System.out.println();
    }
    System.out.println();
    }

 

}
