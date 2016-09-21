/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public class SimpleBacktracking {

    public SimpleBacktracking() {

    }

    public boolean solve(Vertex v, ArrayList<Vertex> edges) {
        boolean solution = true;

        for (int k = 0; k < edges.size(); k++) {
            if (edges.get(k).color == Color.WHITE) {
                v = edges.get(k);
                solution = false;
                break;
            }
        }
        if (solution) {
            return true;
        }

        // if (!v.neighbors.get(i).visited) {
        for (int j = 0; j < v.colorsTried.length; j++) {
            v.setColor(setColor(j));
            if (isGoal(v)) {
                for (int i = 0; i < v.neighbors.size(); i++) {

                    if (solve(v.neighbors.get(i), edges)) {
                        return true;
                    }
                }
            }

        }
        //}

        return false;
    }

    public Color setColor(int i) {
        Color blue = Color.BLUE;
        Color green = Color.GREEN;
        Color red = Color.RED;
        Color[] colors = new Color[3];
        colors[0] = blue;
        colors[1] = green;
        colors[2] = red;

        return colors[i];

    }

    public boolean isGoal(Vertex v) {

        for (int i = 0; i < v.neighbors.size(); i++) {
            if (v.color == v.neighbors.get(i).color) {
                return false;
            }
        }
        return true;
    }

}
