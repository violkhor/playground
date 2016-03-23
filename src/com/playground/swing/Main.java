package com.playground.swing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by SC on 2016-03-20.
 */
public class Main {

    static class Node {
        private int row;
        private int col;
        private int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

    }

    static class MatrixService {
        public List<List<Node>> getCircles(int[][] matrix) {
            if (!isSquare(matrix)) return null;
            int numOfCircles = matrix.length / 2;

            // Initialize array with number of circles
            List<List<Node>> circles = new ArrayList<>(numOfCircles);

            //Populate the nodes and return all the circles with their coordinates
            for (int i = 0; i < numOfCircles; i++) {
                circles.add(i, getPointsOnCircle(i, matrix.length - i - 1, matrix));
            }
            return circles;
        }

        /**
         * @param circle The coordinates of a circle
         * @description --
         * Move the elements of the circle one step clockwise
         */

        public void moveOneStepClockwise(List<Node> circle) {
            int tmp = circle.get(circle.size() - 1).getValue();
            for (int i = circle.size() - 1; i > 0; i--) {
                circle.get(i).setValue(circle.get(i - 1).getValue());
            }
            circle.get(0).setValue(tmp);
        }

        private boolean isSquare(int[][] matrix) {
            // validate if it's a square
            for (int i = 0; i < matrix.length - 1; i++) {
                if (matrix[i].length != matrix.length) {
                    return false;
                }
            }
            return true;
        }


        /**
         * @param start  The start index
         * @param end    The end index
         * @param matrix
         * @return A list of points that is generated automatically based on  the start index and end index.
         * The list of points are all part of one circle.
         */
        public List<Node> getPointsOnCircle(int start, int end, int[][] matrix) {
            List<Node> points = new ArrayList<>();

            int i, j;
            //TOP
            for (i = start, j = start; j <= end; j++) {
                points.add(new Node(i, j, matrix[i][j]));
            }
            // RIGHT
            for (i = start + 1, j = end; i <= end; i++) {
                points.add(new Node(i, j, matrix[i][j]));
            }
            //BOTTOM
            for (j = end - 1, i = end; j >= start; j--) {
                points.add(new Node(i, j, matrix[i][j]));
            }
            //LEFT
            for (i = end - 1, j = start; i > start; i--) {
                points.add(new Node(i, j, matrix[i][j]));
            }
            return points;
        }

        /**
         * @param matrix Prints the matrix
         */

        public void print(int[][] matrix) {
            int size = matrix.length;
            for (int[] row : matrix) {
                for (int j = 0; j < size; j++) {
                    System.out.print(row[j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // skipping to the end of input
        // using a array to keep track of which numbers have showed up
        //
        int[][] matrix = new int[n][];
        for (int t = 0; t < n; t++) {
            String line = sc.nextLine();
            String str[] = line.split(" ");
            int[] intArr = convertToIntArray(str);
            matrix[t] = intArr;
        }
        // collected input, time to rotate
        rotate(matrix);

    }

    /**
     * @param matrix The matrix taken from input
     * @description --
     * 1. Based on the matrix, find the number of circles
     * 2. Get the coordinates of each circle. For example, a 3x3 matrix will have 1 circle, 4x4 matrix will have 2 circles
     * 3. Once the coordinates are ascertained, move each element one step clockwise
     */
    private static void rotate(int[][] matrix) {
        if (matrix.length == 1) {
            System.out.println(matrix[0][0]);
            return;
        }
        MatrixService matrixService = new MatrixService();
        List<List<Node>> circleList = matrixService.getCircles(matrix);
        if (circleList == null) {
            System.out.println("ERROR"); // not a square
            return;
        }
        // now we have the list of circles, time to rotate
        for (List<Node> circle : circleList) {
            matrixService.moveOneStepClockwise(circle);
        }
//        circleList.forEach(matrixService::moveOneStepClockwise);

        // re-assigning the value to the matrix
        for (List<Node> circle : circleList) {
            for (Node node : circle) {
                matrix[node.getRow()][node.getCol()] = node.getValue();
            }

        }
        matrixService.print(matrix);
    }


    private static int[] convertToIntArray(String[] charArray) {
        int[] result = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            result[i] = Integer.parseInt(charArray[i]);
        }
        return result;
    }


}
