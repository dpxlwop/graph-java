package org.school;

import org.school.stack.s21_Stack;
import org.school.queue.s21_Queue;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphAlgorithms {

    private GraphAlgorithms() {
        /* This utility class should not be instantiated */
    }

    public static int[] depthFirstSearch(Graph graph, int startVertex) {
        if (graph == null) {
            throw new NullPointerException("Graph is null");
        }
        startVertex-=1;
        if (startVertex < 0 || startVertex >= graph.getSize()) {
            throw new IllegalArgumentException("Start index is invalid");
        }
        s21_Stack<Integer> stack = new s21_Stack<>();
        boolean[] visited = new boolean[graph.getSize()];
        ArrayList<Integer> result = new ArrayList<>();
        stack.push(startVertex);
        visited[startVertex] = true;
        while (!stack.isEmpty()) {
            int currentElement = stack.pop();
            result.add(currentElement);
            for (int i = graph.getSize() - 1; i >= 0; i--) {
                if (graph.getAdjacencyMatrix()[currentElement][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
        return result.stream().mapToInt(i -> i + 1).toArray();
    }

    public static int[] breadthFirstSearch(Graph graph, int startVertex){
        if (graph == null) {
            throw new NullPointerException("Graph is null");
        }
        startVertex-=1;
        if (startVertex < 0 || startVertex >= graph.getSize()) {
            throw new IllegalArgumentException("Start index is invalid");
        }
        s21_Queue<Integer> queue = new s21_Queue<>();
        boolean[] visited = new boolean[graph.getSize()];
        ArrayList<Integer> result = new ArrayList<>();
        queue.push(startVertex);
        visited[startVertex] = true;
        while (!queue.isEmpty()){
            int currentElement = queue.pop();
            result.add(currentElement);
            for(int i = 0; i < graph.getSize(); i++){
                if (graph.getAdjacencyMatrix()[currentElement][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.push(i);
                }
            }
        }
        return result.stream().mapToInt(i -> i + 1).toArray();
    }

    public static int getShortestPathBetweenVertices(Graph graph, int vertex1, int vertex2){
        if (graph == null){
            throw new NullPointerException("Graph is null");
        }
        for (int[] row : graph.getAdjacencyMatrix()){
            for(int i : row){
                if (i < 0){
                    throw new IllegalArgumentException("Dijkstra's algorithm can't work with negative weights of graph");
                }
            }
        }
        vertex1-=1;
        vertex2-=1;
        if (vertex1 < 0 || vertex2 < 0 || vertex1 >= graph.getSize() || vertex2 >= graph.getSize()){
            throw new IllegalArgumentException("Invalid vertex index");
        }
        return dijkstraAlgorithmImpl(graph, vertex1)[vertex2];

    }

    private static int[] dijkstraAlgorithmImpl(Graph graph, int startIndex){
        int[] distances = new int[graph.getSize()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        boolean[] visited = new boolean[graph.getSize()];
        distances[startIndex] = 0;
        int currentVertex;
        for(int i = 0; i < graph.getSize(); i++){
            int minDistance = Integer.MAX_VALUE;
            int minDistanceIndex = -1;
            for(int j = 0; j < graph.getSize(); j++){
                if(!visited[j] && distances[j] < minDistance){
                    minDistance = distances[j];
                    minDistanceIndex = j;
                }
            }
            if (minDistanceIndex == -1){
                break;
            }
            currentVertex = minDistanceIndex;
            visited[currentVertex] = true;
            for(int j = 0; j < graph.getSize(); j++){
                if(graph.getAdjacencyMatrix()[currentVertex][j] > 0 && !visited[j]){
                    int newDistance = distances[currentVertex] + graph.getAdjacencyMatrix()[currentVertex][j];
                    if (newDistance < distances[j]){
                        distances[j] = newDistance;
                    }
                }
            }
        }
        return distances;
    }

    public static int[][]getShortestPathsBetweenAllVertices(Graph graph){
        if (graph == null){
            throw new NullPointerException("Graph is null");
        }
        return floydWarshallAlgorithmImpl(graph);
    }

    private static int[][] floydWarshallAlgorithmImpl(Graph graph){
        int[][] distances = new int[graph.getSize()][graph.getSize()];
        for (int i = 0; i < graph.getSize(); i++){
            for(int j = 0; j < graph.getSize(); j++){
                if(i == j){
                    distances[i][j] = 0;
                } else if(graph.getAdjacencyMatrix()[i][j] > 0){
                    distances[i][j] = graph.getAdjacencyMatrix()[i][j];
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < graph.getSize(); k++){
            for (int i = 0; i < graph.getSize(); i++){
                for (int j = 0; j < graph.getSize(); j++){
                    if (distances[i][k] != Integer.MAX_VALUE
                            && distances[k][j] != Integer.MAX_VALUE
                            && distances[i][j] > distances[i][k] + distances[k][j]){
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        return distances;
    }

    public static int[][] getLeastSpanningTree(Graph graph) {
        if (graph == null) {
            throw new NullPointerException("Graph is null");
        }
        boolean[] visited = new boolean[graph.getSize()];
        int[][] mst = new int[graph.getSize()][graph.getSize()];
        visited[0] = true;
        for (int k = 0; k < graph.getSize() - 1; k++) {
            int minWeight = Integer.MAX_VALUE;
            int from = -1;
            int to = -1;
            for (int i = 0; i < graph.getSize(); i++) {
                if (visited[i]) {
                    for (int j = 0; j < graph.getSize(); j++) {
                        int current = graph.getAdjacencyMatrix()[i][j];
                        if (!visited[j]
                                && current > 0
                                && current < minWeight) {
                            minWeight = current;
                            from = i;
                            to = j;
                        }
                    }
                }
            }
            if (to == -1) {
                throw new IllegalArgumentException("Graph is disconnected");
            }
            mst[from][to] = minWeight;
            mst[to][from] = minWeight;
            visited[to] = true;
        }
        return mst;
    }
}
