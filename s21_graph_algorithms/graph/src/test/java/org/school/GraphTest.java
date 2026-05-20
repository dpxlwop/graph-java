package org.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.school.exceptions.EmptyFileException;
import org.school.exceptions.SaveFailException;
import org.school.exceptions.WrongFileException;
import org.school.exceptions.WrongMatrixException;

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
                {1, 0}
        };

        assertDoesNotThrow(() -> new Graph(matrix));
    }

    @Test
    void constructorNullMatrix() {
        assertThrows(
                WrongMatrixException.class,
                () -> new Graph((int[][]) null)
        );
    }

    @Test
    void constructorEmptyMatrix() {
        int[][] matrix = new int[0][0];

        assertThrows(
                WrongMatrixException.class,
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
                WrongMatrixException.class,
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
                WrongMatrixException.class,
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
                EmptyFileException.class,
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
                WrongMatrixException.class,
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
                WrongMatrixException.class,
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
                WrongFileException.class,
                () -> new Graph("no_such_file.txt")
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
    void exportEmptyEdgesGraph() throws IOException {
        int[][] matrix = {
                {0, 0},
                {0, 0}
        };

        Graph graph = new Graph(matrix);

        Path output = tempDir.resolve("empty.dot");

        graph.exportGraphToDot(output.toString());

        String result = Files.readString(output);

        assertTrue(result.contains("graph G"));
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
    void exportToInvalidDirectory() {
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };

        Graph graph = new Graph(matrix);

        assertThrows(
                SaveFailException.class,
                () -> graph.exportGraphToDot("/invalid/path/file.dot")
        );
    }
}