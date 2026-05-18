package org.school;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphDFUBFSTest {

    @Test
    void depthFirstSearchTest() {
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 4, 3},
                result
        );
    }

    @Test
    void breadthFirstSearchTest() {
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3, 4},
                result
        );
    }

    @Test
    void dfsSingleVertexTest() {
        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1},
                result
        );
    }

    @Test
    void bfsSingleVertexTest() {
        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1},
                result
        );
    }

    @Test
    void dfsDisconnectedGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2},
                result
        );
    }

    @Test
    void bfsDisconnectedGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2},
                result
        );
    }

    @Test
    void dfsCycleGraphTest() {
        int[][] matrix = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3},
                result
        );
    }

    @Test
    void bfsCycleGraphTest() {
        int[][] matrix = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3},
                result
        );
    }

    @Test
    void dfsFromMiddleVertexTest() {
        int[][] matrix = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 2);

        assertArrayEquals(
                new int[]{2, 1, 3, 4, 5},
                result
        );
    }

    @Test
    void bfsFromMiddleVertexTest() {
        int[][] matrix = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 2);

        assertArrayEquals(
                new int[]{2, 1, 3, 4, 5},
                result
        );
    }

    @Test
    void dfsLinearGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3, 4},
                result
        );
    }

    @Test
    void bfsLinearGraphTest() {
        int[][] matrix = {
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3, 4},
                result
        );
    }

    @Test
    void dfsTreeGraphTest() {
        int[][] matrix = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 4, 5, 3},
                result
        );
    }

    @Test
    void bfsTreeGraphTest() {
        int[][] matrix = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5},
                result
        );
    }

    @Test
    void dfsInvalidStartVertexLowTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.depthFirstSearch(graph, 0)
        );
    }

    @Test
    void bfsInvalidStartVertexHighTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.breadthFirstSearch(graph, 3)
        );
    }

    @Test
    void dfsWeightedGraphTest() {
        int[][] matrix = {
                {0, 5, 2},
                {5, 0, 1},
                {2, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.depthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3},
                result
        );
    }

    @Test
    void bfsWeightedGraphTest() {
        int[][] matrix = {
                {0, 5, 2},
                {5, 0, 1},
                {2, 1, 0}
        };

        Graph graph = new Graph(matrix);

        int[] result = GraphAlgorithms.breadthFirstSearch(graph, 1);

        assertArrayEquals(
                new int[]{1, 2, 3},
                result
        );
    }
    @Test
    void dfsNullGraphTest() {
        assertThrows(
                NullPointerException.class,
                () -> GraphAlgorithms.depthFirstSearch(null, 1)
        );
    }

    @Test
    void bfsNullGraphTest() {
        assertThrows(
                NullPointerException.class,
                () -> GraphAlgorithms.breadthFirstSearch(null, 1)
        );
    }

    @Test
    void dfsNegativeStartVertexTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.depthFirstSearch(graph, -5)
        );
    }

    @Test
    void bfsNegativeStartVertexTest() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.breadthFirstSearch(graph, -10)
        );
    }

    @Test
    void dfsTooLargeStartVertexTest() {
        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.depthFirstSearch(graph, 100)
        );
    }

    @Test
    void bfsTooLargeStartVertexTest() {
        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                IllegalArgumentException.class,
                () -> GraphAlgorithms.breadthFirstSearch(graph, 999)
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


}