package org.school;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphPrimTest {

    @Test
    void leastSpanningTreeSimpleGraphTest() {

        int[][] matrix = {
                {0, 2, 3},
                {2, 0, 1},
                {3, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int[][] expected = {
                {0, 2, 0},
                {2, 0, 1},
                {0, 1, 0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void leastSpanningTreeSingleVertexTest() {

        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int[][] expected = {
                {0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void leastSpanningTreeLinearGraphTest() {

        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 2, 0},
                {0, 2, 0, 3},
                {0, 0, 3, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int[][] expected = {
                {0, 1, 0, 0},
                {1, 0, 2, 0},
                {0, 2, 0, 3},
                {0, 0, 3, 0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void leastSpanningTreeDenseGraphTest() {

        int[][] matrix = {
                {0, 1, 4, 7},
                {1, 0, 2, 8},
                {4, 2, 0, 1},
                {7, 8, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int sum = 0;

        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                sum += result[i][j];
            }
        }

        assertEquals(4, sum);
    }

    @Test
    void leastSpanningTreeChooseMinimalEdgesTest() {

        int[][] matrix = {
                {0, 10, 1},
                {10, 0, 2},
                {1, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int[][] expected = {
                {0, 0, 1},
                {0, 0, 2},
                {1, 2, 0}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void leastSpanningTreeNullGraphTest() {

        assertThrows(
                NullPointerException.class,
                () -> GraphAlgorithms.getLeastSpanningTree(null)
        );
    }

    @Test
    void leastSpanningTreeDisconnectedGraphTest() {

        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.getLeastSpanningTree(graph)
        );
    }

    @Test
    void leastSpanningTreeHugeWeightsTest() {

        int[][] matrix = {
                {0, 1_000_000, 3_000_000},
                {1_000_000, 0, 2_000_000},
                {3_000_000, 2_000_000, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int sum = 0;

        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                sum += result[i][j];
            }
        }

        assertEquals(3_000_000, sum);
    }

    @Test
    void leastSpanningTreeSymmetricGraphTest() {

        int[][] matrix = {
                {0, 3, 0, 7},
                {3, 0, 1, 0},
                {0, 1, 0, 2},
                {7, 0, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        assertEquals(result[0][1], result[1][0]);
        assertEquals(result[1][2], result[2][1]);
        assertEquals(result[2][3], result[3][2]);
    }

    @Test
    void leastSpanningTreeBigGraphStressTest() {

        int size = 100;

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size - 1; i++) {
            matrix[i][i + 1] = 1;
            matrix[i + 1][i] = 1;
        }

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int sum = 0;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                sum += result[i][j];
            }
        }

        assertEquals(99, sum);
    }

    @Test
    void leastSpanningTreeAlreadyMinimalGraphTest() {

        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 2},
                {0, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        assertArrayEquals(matrix, result);
    }

    @Test
    void leastSpanningTreeSquareGraphTest() {

        int[][] matrix = {
                {0, 1, 5, 1},
                {1, 0, 1, 5},
                {5, 1, 0, 1},
                {1, 5, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getLeastSpanningTree(graph);

        int sum = 0;

        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                sum += result[i][j];
            }
        }

        assertEquals(3, sum);
    }
}