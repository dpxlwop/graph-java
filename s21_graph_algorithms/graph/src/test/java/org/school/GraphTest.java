package org.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.school.Graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @TempDir
    Path tempDir;

    @Test
    void constructorValidMatrix() {
        int[][] matrix = {
                {0, 1},
                {1, 0}};
        assertDoesNotThrow(() -> new Graph(matrix));
    }

    @Test
    void constructorNullMatrix() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph((int[][]) null)
        );
    }

    @Test
    void constructorEmptyMatrix() {
        int[][] matrix = new int[0][0];
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(matrix)
        );
    }

    @Test
    void constructorNonSquareMatrix() {
        int[][] matrix = {
                {0, 1},
                {1}
        };
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(matrix)
        );
    }

    @Test
    void constructorNegativeValues() {
        int[][] matrix = {
                {0, -1},
                {1, 0}
        };
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(matrix)
        );
    }

    @Test
    void loadGraphFromFileValid() throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(
                file,
                """
                0 1 0
                1 0 1
                0 1 0
                """
        );
        assertDoesNotThrow(
                () -> new Graph(file.toString())
        );
    }

    @Test
    void loadGraphFromFileEmpty() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(file.toString())
        );
    }

    @Test
    void loadGraphFromFileNonSquare() throws IOException {
        Path file = tempDir.resolve("bad.txt");
        Files.writeString(
                file,
                """
                0 1
                1 0 1
                """
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(file.toString())
        );
    }

    @Test
    void loadGraphFromFileNegative() throws IOException {
        Path file = tempDir.resolve("negative.txt");
        Files.writeString(
                file,
                """
                0 -1
                1 0
                """
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Graph(file.toString())
        );
    }

    @Test
    void exportGraphToDot() throws IOException {
        int[][] matrix = {
                {0, 2},
                {2, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("graph.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        String expected =
                """
                graph G {
                \t1 -- 2 [label="2"];
                }""";
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void exportUnweightedGraphToDot() throws IOException {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("unweighted.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        String expected =
                """
                graph G {
                \t1 -- 2;
                }""";
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void constructorCreatesDeepCopy() throws IOException {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };
        Graph graph = new Graph(matrix);
        matrix[0][1] = 999;
        Path output = tempDir.resolve("deepcopy.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        assertFalse(result.contains("999"));
        assertTrue(result.contains("1 -- 2"));
    }

    @Test
    void loadGraphFromFileWithBlankLines() throws IOException {
        Path file = tempDir.resolve("blanklines.txt");
        Files.writeString(
                file,
                """
    
                0 1
    
                1 0
    
                """
        );
        assertDoesNotThrow(
                () -> new Graph(file.toString())
        );
    }

    @Test
    void loadGraphFromFileInvalidNumber() throws IOException {
        Path file = tempDir.resolve("invalid.txt");
        Files.writeString(
                file,
                """
                0 a
                1 0
                """
        );
        assertThrows(
                NumberFormatException.class,
                () -> new Graph(file.toString())
        );
    }

    @Test
    void loadGraphFromFileNotExisting() {
        assertThrows(
                RuntimeException.class,
                () -> new Graph("no_such_file.txt")
        );
    }

    @Test
    void exportEmptyEdgesGraph() throws IOException {
        int[][] matrix = {
                {0, 0},
                {0, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("empty_edges.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        String expected =
                """
                graph G {
                }""";
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void exportThreeVerticesGraph() throws IOException {
        int[][] matrix = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("three.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        assertTrue(result.contains("1 -- 2"));
        assertTrue(result.contains("1 -- 3"));
        assertTrue(result.contains("2 -- 3"));
    }

    @Test
    void exportWeightedGraphContainsLabels() throws IOException {
        int[][] matrix = {
                {0, 5},
                {5, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("weighted.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        assertTrue(result.contains("[label=\"5\"]"));
    }

    @Test
    void exportUnweightedGraphDoesNotContainLabels() throws IOException {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("unweighted2.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        assertFalse(result.contains("label"));
    }

    @Test
    void loadGraphTwice() throws IOException {
        Path file1 = tempDir.resolve("graph1.txt");
        Path file2 = tempDir.resolve("graph2.txt");
        Files.writeString(
                file1,
                """
                0 1
                1 0
                """
        );
        Files.writeString(
                file2,
                """
                0 2
                2 0
                """
        );
        Graph graph = new Graph(file1.toString());
        assertDoesNotThrow(
                () -> graph.loadGraphFromFile(file2.toString())
        );
    }

    @Test
    void exportSelfLoopIgnored() throws IOException {
        int[][] matrix = {
                {1, 0},
                {0, 0}
        };
        Graph graph = new Graph(matrix);
        Path output = tempDir.resolve("selfloop.dot");
        graph.exportGraphToDot(output.toString());
        String result = Files.readString(output);
        assertFalse(result.contains("1 -- 1"));
    }

    @Test
    void exportToInvalidDirectory() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };
        Graph graph = new Graph(matrix);
        assertThrows(
                RuntimeException.class,
                () -> graph.exportGraphToDot("/invalid/path/file.dot")
        );
    }
}