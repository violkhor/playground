package com.playground.swing;

import com.playground.swing.domain.Node;
import com.playground.swing.service.MatrixServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Created by SC on 2016-03-20.
 */
public class Main {

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
        MatrixServiceImpl matrixService = new MatrixServiceImpl();
        List<List<Node>> circleList = matrixService.getCircles(matrix);
        if (circleList == null) {
            System.out.println("ERROR"); // not a square
            return;
        }
        // now we have the list of circles, time to rotate
        circleList.forEach(matrixService::moveOneStepClockwise);

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
