package com.playground.seesaw;

import java.util.Scanner;

/**
 * Created by SC on 2016-03-20.
 */
public class Main {
    public static void main(String[] args) {
        // Assumption: MAX of 100 rows
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // skipping to the end of input
        // using a array to keep track of which numbers have showed up
        int[] showedUp = new int[100];
        //
        initializeArray(showedUp);
        int[][] matrix = new int[n][];
        for (int t = 0; t < n; t++) {
            String line = sc.nextLine();
            String str[] = line.split(" ");
            int[] intArr = convertToIntArray(str, showedUp);
            matrix[t] = intArr;
        }
        int kValue = sc.nextInt();
        boolean result = findDuplicate(matrix, kValue, showedUp);
        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static void initializeArray(int[] showedUp) {
        for (int i = 0; i < showedUp.length; i++) {
            showedUp[i] = 0;
        }
    }

    private static boolean findDuplicate(int[][] matrix, int kValue, int[] showedUp) {
        /*
            Go through each row, if the value showed up,
            check the surrounding values that are k-distance away
         */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (showedUp[matrix[i][j]] > 1) {
                    //check adjacent
                    boolean result = checkAdjacent(matrix, kValue, i, j);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private static boolean checkAdjacent(int[][] matrix, int kValue, int row, int col) {
        int minRow, minCol, maxRow, maxCol;
        if (row - kValue + 1 < 0) {
            minRow = 0;
        } else {
            minRow = row - kValue + 1;
        }
        if (col - kValue + 1 < 0) {
            minCol = 0;
        } else {
            minCol = col - kValue + 1;
        }
        if (row + kValue - 1 > matrix.length - 1) {
            maxRow = matrix.length - 1;
        } else {
            maxRow = row + kValue - 1;
        }
        if (col + kValue - 1 > matrix[0].length - 1) {
            maxCol = matrix[0].length - 1;
        } else {
            maxCol = col + kValue - 1;
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (row == i && col == j) continue;
                if (matrix[i][j] == matrix[row][col]) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * @param charArray The tokenized line input
     * @param showedUp  An array that tells if a number has shown up
     * @return
     */
    private static int[] convertToIntArray(String[] charArray, int[] showedUp) {
        int[] result = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            result[i] = Integer.parseInt(charArray[i]);
            showedUp[result[i]]++;
        }
        return result;

    }


}
