/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Austin
 */


public class BacktrackingFC {

    ArrayList<Color> colors = new ArrayList<>();

    Random r = new Random();

    public BacktrackingFC() {
        this.colors.add(Color.blue);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.RED);
        this.colors.add(Color.black);
    }

    public boolean solve(Vertex v, ArrayList<Vertex> edges, int totalVert, int colorSize) {
        int backtrack = 0;
        //We'll use a while loop instead of recursion to avoid stack overflow. Some of the paths can take a while to find a solution and recursion generally puts too much on the stack
        while (remainingVerticies(edges)) {
            if (colorSize == 3) {
                for (int i = 0; i < edges.size(); i++) {
                    edges.get(i).removeColor(Color.BLACK);
                }
            }
            // For the number of vertices, pick one, if it is yellow we need to color it.
            //TODO we hardcode the graph size now. We need to add user input later
            v = edges.get(this.r.nextInt(totalVert));

            if (v.color == Color.YELLOW) {
                //We now know we need to color the vertex. We'll loop over each color and check each neighbor for consistency. If the color is not consistent
                //remove it from the available colros
                for (int j = 0; j < colorSize; j++) {
                    if (!isConsistent(v, colorSize)) {
                        v.removeColor(v.color);
                    }

                }
                // if all the colors are removed, we found a conflict and need to backtrack. Reset colors and try again.
                if (v.colorsTried.isEmpty()) {
                    int n = this.r.nextInt(v.getNeighbors().size());
                    resetColors(edges, colorSize);
                    v.color = Color.YELLOW;
                    v.getNeighbors().get(n).color = Color.YELLOW;
                    backtrack++;
                }
            }
            //assume in infinite loop or time is too long for any useful purpose
            if(backtrack > 99999){
                break;
            }
        }
        System.out.println(backtrack);
        //return if a solution was found
        return isGoal(v, edges);
    }

    // set the vertex's available colors back to default
    public void resetColors(ArrayList<Vertex> edges, int size) {
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).resetColors(size);
        }
    }

    //for each color check neighbors and make sure they don't match
    public boolean isConsistent(Vertex v, int colorSize) {
        for (int i = 0; i < colorSize; i++) {
            for (int j = 0; j < v.getNeighbors().size(); j++) {
                if (!isSafe(v, i)) {
                    return false;
                }
            }

        }

        return true;
    }

    //make sure we have no remaining yellow, or un-colored vertices
    public boolean remainingVerticies(ArrayList<Vertex> edges) {

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).color == Color.YELLOW) {
                return true;
            }
        }
        return false;
    }

    //We check the entire graph to make sure that it is a valid solution. If that is true we are :) otherwise we are :(
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

    //This is where we get a color, and check to see if it violates our constraint
    public boolean isSafe(Vertex v, int j) {
        v.color = v.getFCColor();
        for (int i = 0; i < v.neighbors.size(); i++) {
            if (v.color == v.neighbors.get(i).color) {
                return false;
            }
        }
        return true;
    }

}
