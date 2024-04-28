package com.eniso.projetjava;
import java.util.*;

abstract class Graph {
    public List<Integer> vertices;
    public List<int[]> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    abstract void addVertex(int vertex);
    abstract void addEdge(int vertex1, int vertex2);
    abstract void printGraph();
    abstract List<Integer> BFS(int startVertex);
    abstract List<Integer> DFS(int startVertex);
    abstract Map<Integer, Integer> dijkstra(int startVertex);
    abstract ArrayList<ArrayList<Integer>> convertAdjacencyMatrixToAdjacencyList(int[][] a);
    abstract int[][] convertAdjacencyListToAdjacencyMatrix(Vector<Integer> adj[], int V);
}
