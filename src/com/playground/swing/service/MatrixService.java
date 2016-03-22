package com.playground.swing.service;

import com.playground.swing.domain.Node;

import java.util.List;

/**
 * Created by SC on 2016-03-21.
 */
public interface MatrixService {

    List<List<Node>> getCircles(int[][] matrix);


    List<Node> getPointsOnCircle(int start, int end, int[][] matrix);

    void moveOneStepClockwise(List<Node> circle);

    void print(int[][] matrix);
}
