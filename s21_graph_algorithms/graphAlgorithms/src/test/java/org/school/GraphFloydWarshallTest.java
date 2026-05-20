package org.school;

import org.junit.jupiter.api.Test;
import org.school.exceptions.WrongMatrixException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphFloydWarshallTest {

    @Test
    void allShortestPathsSimpleGraphTest() {
        int[][] matrix = {
                {0, 2, 10},
                {2, 0, 3},
                {10, 3, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 2, 5},
                {2, 0, 3},
                {5, 3, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsLinearGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 2, 0},
                {0, 2, 0, 3},
                {0, 0, 3, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 1, 3, 6},
                {1, 0, 2, 5},
                {3, 2, 0, 3},
                {6, 5, 3, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsDisconnectedGraphTest() {
        int inf = Integer.MAX_VALUE;

        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 1, inf},
                {1, 0, inf},
                {inf, inf, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsSingleVertexTest() {
        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsDenseGraphTest() {
        int[][] matrix = {
                {0, 1, 4, 7},
                {1, 0, 2, 8},
                {4, 2, 0, 1},
                {7, 8, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 1, 3, 4},
                {1, 0, 2, 3},
                {3, 2, 0, 1},
                {4, 3, 1, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsDirectedGraphTest() {
        int inf = Integer.MAX_VALUE;

        int[][] matrix = {
                {0, 5, 0},
                {0, 0, 2},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 5, 7},
                {inf, 0, 2},
                {inf, inf, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsChooseBetterRouteTest() {
        int[][] matrix = {
                {0, 50, 10, 0},
                {50, 0, 10, 1},
                {10, 10, 0, 100},
                {0, 1, 100, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(21, result[0][3]);
    }

    @Test
    void allShortestPathsNullGraphTest() {
        assertThrows(
                WrongMatrixException.class,
                () -> GraphAlgorithms
                        .getShortestPathsBetweenAllVertices(null)
        );
    }

    @Test
    void allShortestPathsLargeGraphStressTest() {
        int size = 100;

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size - 1; i++) {
            matrix[i][i + 1] = 1;
            matrix[i + 1][i] = 1;
        }

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(99, result[0][99]);
    }

    @Test
    void allShortestPathsCycleGraphTest() {
        int[][] matrix = {
                {0, 1, 4},
                {1, 0, 2},
                {4, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, 1, 3},
                {1, 0, 2},
                {3, 2, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsTreeGraphTest() {
        int[][] matrix = {
                {0, 2, 3, 0, 0},
                {2, 0, 0, 5, 0},
                {3, 0, 0, 0, 7},
                {0, 5, 0, 0, 1},
                {0, 0, 7, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(8, result[0][4]);
        assertEquals(6, result[1][4]);
        assertEquals(7, result[2][4]);
    }

    @Test
    void allShortestPathsFullyDisconnectedGraphTest() {
        int inf = Integer.MAX_VALUE;

        int[][] matrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] expected = {
                {0, inf, inf},
                {inf, 0, inf},
                {inf, inf, 0}
        };

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertArrayEquals(expected, result);
    }

    @Test
    void allShortestPathsAsymmetricGraphTest() {
        int[][] matrix = {
                {0, 1, 10},
                {5, 0, 1},
                {2, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(2, result[0][2]);
        assertEquals(2, result[2][0]);
    }

    @Test
    void allShortestPathsHugeWeightsTest() {
        int[][] matrix = {
                {0, 1_000_000, 0},
                {1_000_000, 0, 1_000_000},
                {0, 1_000_000, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(2_000_000, result[0][2]);
    }

    @Test
    void allShortestPathsZeroWeightEdgesIgnoredTest() {
        int[][] matrix = {
                {0, 0, 5},
                {0, 0, 1},
                {5, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(6, result[0][1]);
    }

    @Test
    void allShortestPathsDirectedNoReversePathTest() {
        int[][] matrix = {
                {0, 5, 0},
                {0, 0, 2},
                {0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(Integer.MAX_VALUE, result[2][0]);
    }

    @Test
    void allShortestPathsBigDenseGraphStressTest() {
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

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(1, result[0][49]);
    }

    @Test
    void allShortestPathsLongAlternativeRouteTest() {
        int[][] matrix = {
                {0, 100, 1, 0, 0},
                {100, 0, 1, 1, 0},
                {1, 1, 0, 50, 50},
                {0, 1, 50, 0, 1},
                {0, 0, 50, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(4, result[0][4]);
    }

    @Test
    void allShortestPathsSymmetricGraphTest() {
        int[][] matrix = {
                {0, 3, 0, 7},
                {3, 0, 1, 0},
                {0, 1, 0, 2},
                {7, 0, 2, 0}
        };

        Graph graph = new Graph(matrix);

        int[][] result =
                GraphAlgorithms.getShortestPathsBetweenAllVertices(graph);

        assertEquals(result[0][3], result[3][0]);
        assertEquals(result[1][2], result[2][1]);
    }
}