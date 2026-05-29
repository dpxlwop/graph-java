package org.school;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import lombok.Getter;

public class CliService {
    @Getter private final Graph graph;
    private final Scanner scanner;

    public CliService() {
        this.graph = new Graph();
        this.scanner = new Scanner(System.in);
        this.scanner.useLocale(Locale.US);
    }

    public int getOption() {
        drawMainWindow();
        if (graph.getSize() == 0) {
            int option = parseInt(1, 2);
            return option == 2 ? 8 : option;
        }
        return parseInt(1, 8);
    }

    public void loadNewGraph() {
        try {
            this.graph.loadGraphFromFile(getFilename());
            System.out.println("Graph loaded successfully!\n");
        } catch (RuntimeException e) {
            System.out.println("Wrong file!");
        }
    }

    public int getVertex(String message) {
        System.out.print(message);
        return parseInt(1, graph.getSize());
    }

    public void printMatrix(int[] matrix) {
        System.out.println(Arrays.toString(matrix));
    }

    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void printAntResult(TsmResult result) {
        System.out.println("Route: ");
        printMatrix(result.getVertices());
        System.out.printf("Distance: %.2f%n", result.getDistance());
    }

    private String getFilename() {
        System.out.print("Enter filepath: ");
        return scanner.next();
    }

    private int parseInt(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= min && number <= max) {
                    return number;
                }
            } else {
                scanner.next();
            }
            System.out.print("Wrong input. Try again: ");
        }
    }

    private void drawMainWindow() {
        System.out.println("\n*************************");
        System.out.println("s21 graph algorithms CLI");
        if (graph.getSize() == 0) {
            System.out.println("1. Load graph from file");
            System.out.println("2. Exit");
        } else {
            System.out.println("1. Load graph from file");
            System.out.println("2. DFS");
            System.out.println("3. BFS");
            System.out.println("4. Get shortest path between vertices");
            System.out.println("5. All-pairs shortest paths");
            System.out.println("6. Minimum spanning tree");
            System.out.println("7. Traveling salesman problem");
            System.out.println("8. Exit");
        }
        System.out.println("*************************\n");
        System.out.print("Enter option: ");
    }
}
