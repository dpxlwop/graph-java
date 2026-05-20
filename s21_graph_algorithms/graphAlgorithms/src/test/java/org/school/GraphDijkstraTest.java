package org.school;

import org.junit.jupiter.api.Test;
import org.school.exceptions.WrongInputData;
import org.school.exceptions.WrongMatrixException;

import static org.junit.jupiter.api.Assertions.*;

class GraphDijkstraTest {

    @Test
    void shortestPathSimpleGraphTest() {
        int[][] matrix = {
                {0, 1, 4},
                {1, 0, 2},
                {4, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 3);

        assertEquals(3, result);
    }

    @Test
    void shortestPathDirectEdgeTest() {
        int[][] matrix = {
                {0, 5},
                {5, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 2);

        assertEquals(5, result);
    }

    @Test
    void shortestPathSameVertexTest() {
        int[][] matrix = {
                {0, 7},
                {7, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 1);

        assertEquals(0, result);
    }

    @Test
    void shortestPathDisconnectedGraphTest() {
        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 3);

        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void shortestPathComplexGraphTest() {
        int[][] matrix = {
                {0, 2, 0, 1, 0},
                {2, 0, 3, 2, 0},
                {0, 3, 0, 0, 1},
                {1, 2, 0, 0, 4},
                {0, 0, 1, 4, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 5);

        assertEquals(5, result);
    }

    @Test
    void shortestPathNullGraphTest() {
        assertThrows(
                WrongMatrixException.class,
                () -> GraphAlgorithms.getShortestPathBetweenVertices(
                        null,
                        1,
                        2
                )
        );
    }

    @Test
    void shortestPathNegativeVertexTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                WrongInputData.class,
                () -> GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        0,
                        2
                )
        );
    }

    @Test
    void shortestPathTooLargeVertexTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                WrongInputData.class,
                () -> GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        3
                )
        );
    }

    @Test
    void shortestPathNegativeWeightsTest() {
        int[][] matrix = {
                {0, -1},
                {-1, 0}
        };

        assertThrows(
                WrongMatrixException.class,
                () -> new Graph(matrix)
        );
    }

    @Test
    void shortestPathLinearGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 2, 0},
                {0, 2, 0, 3},
                {0, 0, 3, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 4);

        assertEquals(6, result);
    }

    @Test
    void shortestPathChooseSmallerRouteTest() {
        int[][] matrix = {
                {0, 10, 1},
                {10, 0, 1},
                {1, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 2);

        assertEquals(2, result);
    }

    @Test
    void shortestPathCycleGraphTest() {
        int[][] matrix = {
                {0, 1, 4},
                {1, 0, 2},
                {4, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 3, 1);

        assertEquals(3, result);
    }

    @Test
    void shortestPathTreeGraphTest() {
        int[][] matrix = {
                {0, 2, 3, 0, 0},
                {2, 0, 0, 5, 0},
                {3, 0, 0, 0, 7},
                {0, 5, 0, 0, 1},
                {0, 0, 7, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 5);

        assertEquals(8, result);
    }

    @Test
    void shortestPathZeroWeightEdgesIgnoredTest() {
        int[][] matrix = {
                {0, 0, 5},
                {0, 0, 1},
                {5, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 2);

        assertEquals(6, result);
    }

    @Test
    void shortestPathLargeWeightsTest() {
        int[][] matrix = {
                {0, 1000, 5000},
                {1000, 0, 1000},
                {5000, 1000, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 3);

        assertEquals(2000, result);
    }

    @Test
    void shortestPathOneWayBetterThanDirectTest() {
        int[][] matrix = {
                {0, 50, 10, 0},
                {50, 0, 10, 1},
                {10, 10, 0, 100},
                {0, 1, 100, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 4);

        assertEquals(21, result);
    }

    @Test
    void shortestPathFullyDisconnectedGraphTest() {
        int[][] matrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 3);

        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void shortestPathStartEqualsEndInSingleVertexGraphTest() {
        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 1);

        assertEquals(0, result);
    }

    @Test
    void shortestPathDenseGraphTest() {
        int[][] matrix = {
                {0, 1, 4, 7},
                {1, 0, 2, 8},
                {4, 2, 0, 1},
                {7, 8, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(graph, 1, 4);

        assertEquals(4, result);
    }

    @Test
    void shortestPathLargeGraphStressTest() {
        int size = 100;

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size - 1; i++) {
            matrix[i][i + 1] = 1;
            matrix[i + 1][i] = 1;
        }

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        100
                );

        assertEquals(99, result);
    }

    @Test
    void shortestPathLargeDenseGraphStressTest() {
        int size = 50;

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (i != j) {
                    matrix[i][j] = 1;
                }
            }
        }

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        50
                );

        assertEquals(1, result);
    }

    @Test
    void shortestPathDirectedGraphTest() {
        int[][] matrix = {
                {0, 5, 0, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        4
                );

        assertEquals(10, result);
    }

    @Test
    void shortestPathDirectedGraphNoReversePathTest() {
        int[][] matrix = {
                {0, 5, 0},
                {0, 0, 2},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        3,
                        1
                );

        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void shortestPathAsymmetricWeightsTest() {
        int[][] matrix = {
                {0, 1, 10},
                {5, 0, 1},
                {2, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int result1 =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        3
                );

        int result2 =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        3,
                        1
                );

        assertEquals(2, result1);
        assertEquals(2, result2);
    }

    @Test
    void shortestPathHugeWeightsStressTest() {
        int[][] matrix = {
                {0, 1_000_000, 0, 0},
                {1_000_000, 0, 1_000_000, 0},
                {0, 1_000_000, 0, 1_000_000},
                {0, 0, 1_000_000, 0}
        };

        Graph graph = new Graph(matrix);

        int result =
                GraphAlgorithms.getShortestPathBetweenVertices(
                        graph,
                        1,
                        4
                );

        assertEquals(3_000_000, result);
    }
}