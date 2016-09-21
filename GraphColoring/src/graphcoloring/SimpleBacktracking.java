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

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).color == Color.YELLOW) {
                v = edges.get(i);
            }
        }

        if (isGoal(v, edges)) {
            return true;
        }

        // if (!v.neighbors.get(i).visited) {
        for (int j = 0; j < v.colorsTried.length; j++) {
            if (isGoal(v, j)) {
                //  for (int i = 0; i < v.neighbors.size(); i++) {

                if (solve(v, edges)) {
                    return true;
                }
                v.setColor(Color.YELLOW);
                //}
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

    public boolean isGoal(Vertex v, ArrayList<Vertex> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            for (int k = 0; k < vertices.get(i).neighbors.size(); k++) {
                if (vertices.get(i).neighbors.get(k).color == vertices.get(i).color) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGoal(Vertex v, int j) {
        v.setColor(setColor(j));

        for (int i = 0; i < v.neighbors.size(); i++) {
            if (v.color == v.neighbors.get(i).color) {
                return false;
            }
        }
        return true;
    }

}
