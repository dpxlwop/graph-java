package org.school;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.Getter;
import org.school.exceptions.EmptyFileException;
import org.school.exceptions.SaveFailException;
import org.school.exceptions.WrongFileException;
import org.school.exceptions.WrongMatrixException;

@Getter
public class Graph {
    private int[][] adjacencyMatrix;
    private int size;
    private boolean isWeighted;
    private boolean isEmpty;

    public Graph() {
        this.isEmpty = true;
    }

    public Graph(int[][] matrix) {
        checkMatrix(matrix);
        this.adjacencyMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.adjacencyMatrix[i], 0, matrix.length);
        }
        this.size = this.adjacencyMatrix.length;
        this.isWeighted = checkWeighted();
    }

    public Graph(String filename) {
        loadGraphFromFile(filename);
    }

    public void loadGraphFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filename));
            lines.removeIf(String::isBlank);
            if (lines.isEmpty()) {
                throw new EmptyFileException();
            }
            parseAdjacencyMatrix(lines);
            this.isEmpty = false;
        } catch (IOException e) {
            throw new WrongFileException();
        }
    }

    public void exportGraphToDot(String filename) {
        checkIsGraphEmpty();
        String dotString = getGraphDotString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(dotString);
        } catch (IOException e) {
            throw new SaveFailException();
        }
    }

    private String getGraphDotString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("graph G {\n");
        for (int i = 0; i < this.size; i++) {
            for (int j = i + 1; j < this.size; j++) {
                if (this.adjacencyMatrix[i][j] != 0) {
                    stringBuilder.append(String.format("\t%d -- %d", i + 1, j + 1));
                    if (this.isWeighted) {
                        stringBuilder.append(String.format(" [label=\"%d\"];%n", this.adjacencyMatrix[i][j]));
                    } else {
                        stringBuilder.append(";\n");
                    }
                }
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private boolean checkWeighted() {
        for (int[] row : adjacencyMatrix) {
            for (int val : row) {
                if (val != 0 && val != 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkMatrix(int[][] matrix) {
        if (matrix == null) {
            throw new WrongMatrixException("Given matrix is null!");
        }
        if (matrix.length == 0) {
            throw new WrongMatrixException("Matrix size is 0");
        }
        for (int[] row : matrix) {
            if (row.length != matrix.length) {
                throw new WrongMatrixException("Adjacency matrix must be a square!");
            }
            for (int val : row) {
                if (val < 0) {
                    throw new WrongMatrixException("Negative numbers not allowed");
                }
            }
        }
    }

    private void parseAdjacencyMatrix(List<String> file) {
        int matrixSize = file.size();
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            String[] values = file.get(i).trim().split("\\s+");
            if (values.length != matrixSize) {
                throw new WrongMatrixException("Adjacency matrix must be square");
            }
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        checkMatrix(matrix);
        this.adjacencyMatrix = matrix;
        this.size = matrixSize;
        this.isWeighted = checkWeighted();
    }

    private void checkIsGraphEmpty() {
        if (isEmpty) {
            throw new WrongMatrixException("This operation not allowed with empty graph");
        }
    }
}
