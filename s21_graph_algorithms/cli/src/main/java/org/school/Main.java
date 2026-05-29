package org.school;

import org.school.exceptions.*;

public class Main {
    public static void main(String[] args) {
        CliService cliService = new CliService();
        boolean isUserWantToExit = false;
        while (!isUserWantToExit) {
            try {
                int choice = cliService.getOption();
                switch (choice) {
                    case 1 -> cliService.loadNewGraph();
                    case 2 ->
                        cliService.printMatrix(GraphAlgorithms.depthFirstSearch(
                            cliService.getGraph(), cliService.getVertex("Enter vertex: ")));
                    case 3 ->
                        cliService.printMatrix(GraphAlgorithms.breadthFirstSearch(
                            cliService.getGraph(), cliService.getVertex("Enter vertex: ")));
                    case 4 ->
                        System.out.println(GraphAlgorithms.getShortestPathBetweenVertices(
                            cliService.getGraph(), cliService.getVertex("Enter start vertex: "),
                            cliService.getVertex("Enter end vertex: ")));
                    case 5 ->
                        cliService.printMatrix(
                            GraphAlgorithms.getShortestPathsBetweenAllVertices(cliService.getGraph()));
                    case 6 ->
                        cliService.printMatrix(GraphAlgorithms.getLeastSpanningTree(cliService.getGraph()));
                    case 7 ->
                        cliService.printAntResult(
                            GraphAlgorithms.solveTravelingSalesmanProblem(cliService.getGraph()));
                    case 8 -> isUserWantToExit = true;
                    default -> System.out.println("Wrong option");
                }
            } catch (EmptyFileException | SaveFailException | WrongFileException | WrongMatrixException
                | WrongInputData e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Все сломалось :(" + e.getMessage());
            }
        }
    }
}
