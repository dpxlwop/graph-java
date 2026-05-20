package org.school;

import org.junit.jupiter.api.Test;
import org.school.exceptions.WrongMatrixException;

import static org.junit.jupiter.api.Assertions.*;

class GraphAntTest {

    @Test
    void solveTravelingSalesmanProblemValidGraphTest() {

        int[][] matrix = {
                {0, 2, 3, 4},
                {2, 0, 5, 1},
                {3, 5, 0, 6},
                {4, 1, 6, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertNotNull(result);
        assertNotNull(result.getVertices());

        assertEquals(
                graph.getSize() + 1,
                result.getVertices().length
        );

        assertTrue(result.getDistance() > 0);

        assertEquals(
                result.getVertices()[0],
                result.getVertices()[result.getVertices().length - 1]
        );
    }

    @Test
    void solveTravelingSalesmanProblemNullGraphTest() {

        assertThrows(
                WrongMatrixException.class,
                () -> AntAlgorithm.makeAnts(null)
        );
    }

    @Test
    void solveTravelingSalesmanProblemDisconnectedGraphTest() {

        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                WrongMatrixException.class,
                () -> AntAlgorithm.makeAnts(graph)
        );
    }

    @Test
    void solveTravelingSalesmanProblemTwoVerticesTest() {

        int[][] matrix = {
                {0, 10},
                {10, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertArrayEquals(
                new int[]{1, 2, 1},
                result.getVertices()
        );

        assertEquals(
                20.0,
                result.getDistance()
        );
    }

    @Test
    void solveTravelingSalesmanProblemSinglePathTest() {

        int[][] matrix = {
                {0, 1, 100, 100},
                {1, 0, 1, 100},
                {100, 1, 0, 1},
                {100, 100, 1, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertNotNull(result);

        assertEquals(
                graph.getSize() + 1,
                result.getVertices().length
        );

        assertTrue(result.getDistance() > 0);
    }
    @Test
    void solveTravelingSalesmanProblemTriangleGraphTest() {

        int[][] matrix = {
                {0, 1, 2},
                {1, 0, 3},
                {2, 3, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertNotNull(result);

        assertEquals(
                graph.getSize() + 1,
                result.getVertices().length
        );

        assertEquals(
                result.getVertices()[0],
                result.getVertices()[result.getVertices().length - 1]
        );

        assertTrue(result.getDistance() > 0);
    }

    @Test
    void solveTravelingSalesmanProblemLargeWeightsTest() {

        int[][] matrix = {
                {0, 1000, 2000, 3000},
                {1000, 0, 1500, 2500},
                {2000, 1500, 0, 1200},
                {3000, 2500, 1200, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertNotNull(result);

        assertTrue(result.getDistance() > 0);
    }

    @Test
    void solveTravelingSalesmanProblemSymmetricGraphTest() {

        int[][] matrix = {
                {0, 5, 5, 5},
                {5, 0, 5, 5},
                {5, 5, 0, 5},
                {5, 5, 5, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertNotNull(result);

        assertEquals(
                20.0,
                result.getDistance()
        );
    }

    @Test
    void solveTravelingSalesmanProblemRouteContainsAllVerticesTest() {

        int[][] matrix = {
                {0, 2, 9, 10},
                {2, 0, 6, 4},
                {9, 6, 0, 8},
                {10, 4, 8, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        boolean[] visited = new boolean[graph.getSize()];

        for (int i = 0; i < result.getVertices().length - 1; i++) {
            visited[result.getVertices()[i] - 1] = true;
        }

        for (boolean vertexVisited : visited) {
            assertTrue(vertexVisited);
        }
    }

    @Test
    void solveTravelingSalesmanProblemDistancePositiveTest() {

        int[][] matrix = {
                {0, 7, 3},
                {7, 0, 2},
                {3, 2, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertTrue(result.getDistance() > 0);
    }

    @Test
    void solveTravelingSalesmanProblemRouteStartsAndEndsSameVertexTest() {

        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        int[] route = result.getVertices();

        assertEquals(
                route[0],
                route[route.length - 1]
        );
    }

    @Test
    void solveTravelingSalesmanProblemGraphWithZeroEdgeTest() {

        int[][] matrix = {
                {0, 1, 0, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                WrongMatrixException.class,
                () -> AntAlgorithm.makeAnts(graph)
        );
    }

    @Test
    void solveTravelingSalesmanProblemOneVertexGraphTest() {

        int[][] matrix = {
                {0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                WrongMatrixException.class,
                () -> AntAlgorithm.makeAnts(graph)
        );
    }

    @Test
    void solveTravelingSalesmanProblemNegativeWeightGraphTest() {

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
    void solveTravelingSalesmanProblemResultRouteSizeTest() {

        int[][] matrix = {
                {0, 4, 1, 9},
                {4, 0, 6, 11},
                {1, 6, 0, 2},
                {9, 11, 2, 0}
        };

        Graph graph = new Graph(matrix);

        TsmResult result = AntAlgorithm.makeAnts(graph);

        assertEquals(
                graph.getSize() + 1,
                result.getVertices().length
        );
    }
}