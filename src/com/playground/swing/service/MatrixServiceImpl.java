package com.playground.swing.service;

import com.playground.swing.domain.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SC on 2016-03-21.
 */
public class MatrixServiceImpl implements MatrixService {

    /**
     * @param matrix Input matrix
     * @return A list of co-centric circles based on the matrix square
     */
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

