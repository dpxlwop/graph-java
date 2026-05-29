package org.school;

import java.util.Random;
import org.school.exceptions.WrongMatrixException;

public class AntAlgorithm {
    private static final double ALPHA = 1.0;
    private static final double BETA = 2.0;
    private static final double RHO = 0.3;
    private static final double Q = 100.0;
    private static final int ITERATIONS = 1000;
    private static final double PHEROMONE = 0.1;
    private static final Random RANDOM = new Random();

    private AntAlgorithm() {
        /* This utility class should not be instantiated */
    }

    public static TsmResult makeAnts(Graph graph) {
        if (graph == null) {
            throw new WrongMatrixException("Graph is null");
        }
        checkGraphForTsm(graph);
        int n = graph.getSize();
        double[][] pheromone = initPheromoneMap(n);
        int[] bestRoute = null;
        double bestLength = Double.POSITIVE_INFINITY;
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            evaporatePheromones(pheromone);
            for (int start = 0; start < n; start++) {
                int[] route = calculateRoute(graph, pheromone, start);
                if (route == null) {
                    continue;
                }
                double length = calculateRouteLength(graph, route);
                if (length > 0 && length < bestLength) {
                    bestLength = length;
                    bestRoute = route.clone();
                }
                if (length > 0) {
                    updatePheromones(pheromone, route, length);
                }
            }
        }
        if (bestRoute == null) {
            throw new WrongMatrixException("Impossible to solve");
        }
        int[] resultVertices = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            resultVertices[i] = bestRoute[i] + 1;
        }
        return new TsmResult(resultVertices, bestLength);
    }

    private static void checkGraphForTsm(Graph graph) {
        int[][] matrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if (i != j && matrix[i][j] == 0) {
                    throw new WrongMatrixException("Graph must be fully connected");
                }
            }
        }
    }

    private static double[][] initPheromoneMap(int n) {
        double[][] pheromone = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheromone[i][j] = PHEROMONE;
            }
        }
        return pheromone;
    }

    private static void evaporatePheromones(double[][] pheromone) {
        for (int i = 0; i < pheromone.length; i++) {
            for (int j = 0; j < pheromone.length; j++) {
                pheromone[i][j] *= (1.0 - RHO);
                if (pheromone[i][j] < 1e-10) {
                    pheromone[i][j] = 1e-10;
                }
            }
        }
    }

    private static int[] calculateRoute(Graph graph, double[][] pheromone, int start) {
        int n = graph.getSize();
        int[] route = new int[n + 1];
        boolean[] visited = new boolean[n];
        int current = start;
        route[0] = current;
        visited[current] = true;
        for (int step = 1; step < n; step++) {
            double[] probabilities = calculateProbabilities(graph, pheromone, current, visited);
            int next = chooseNextVertex(probabilities);
            if (next == -1) {
                return null;
            }
            route[step] = next;
            visited[next] = true;
            current = next;
        }
        route[n] = start;
        return route;
    }

    private static double[] calculateProbabilities(
        Graph graph, double[][] pheromone, int current, boolean[] visited) {
        int n = graph.getSize();
        double[] probabilities = new double[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] || graph.getAdjacencyMatrix()[current][i] == 0) {
                probabilities[i] = 0.0;
            } else {
                double tau = pheromone[current][i];
                double eta = 1.0 / graph.getAdjacencyMatrix()[current][i];
                probabilities[i] = Math.pow(tau, ALPHA) * Math.pow(eta, BETA);
            }
        }
        return probabilities;
    }

    private static int chooseNextVertex(double[] probabilities) {
        double total = 0.0;
        for (double probability : probabilities) {
            total += probability;
        }
        if (total <= 1e-10) {
            return -1;
        }
        double randomValue = RANDOM.nextDouble() * total;
        for (int i = 0; i < probabilities.length; i++) {
            randomValue -= probabilities[i];
            if (randomValue <= 0) {
                return i;
            }
        }
        return -1;
    }

    private static double calculateRouteLength(Graph graph, int[] route) {
        double length = 0.0;
        for (int i = 0; i < graph.getSize(); i++) {
            int from = route[i];
            int to = route[i + 1];
            int weight = graph.getAdjacencyMatrix()[from][to];
            if (weight == 0) {
                return -1;
            }
            length += weight;
        }
        return length;
    }

    private static void updatePheromones(double[][] pheromone, int[] route, double length) {
        double delta = Q / length;
        for (int i = 0; i < route.length - 1; i++) {
            int from = route[i];
            int to = route[i + 1];
            pheromone[from][to] += delta;
            pheromone[to][from] += delta;
        }
    }
}
