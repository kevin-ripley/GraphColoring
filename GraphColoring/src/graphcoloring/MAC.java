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

/*NOTE THIS WORKS ALMOST EVERYTIME WITH VERTICE EDGE CONSTRAINTS, WITHOUT MANY OF THE GRAPHS ARE NOT SOLVABLE. THIS IS THE SAME THING BRYAN WAS TALKING ABOUT
WE WILL NEED TO SET A VERTEX EDGE CONSTRAINT FOR SIZE 3 COLORINGS. WE WILL EXPLAIN WHY IN OUR PAPER, UNLESS SOMEONE HAS ANOTHER SOLUTION
 */
public class MAC {

    Color[] colors = new Color[4];
    Random r = new Random();

    public MAC() {
        this.colors[0] = Color.blue;
        this.colors[1] = Color.GREEN;
        this.colors[2] = Color.RED;
        this.colors[3] = Color.black;
    }

    public boolean solve(Vertex v1, Vertex v2, ArrayList<Vertex> edges, int totalVert, int colorSize) {

        //We'll use a while loop instead of recursion to avoid stack overflow. Some of the paths can take a while to find a solution and recursion generally puts too much on the stack
        while (remainingVerticies(edges)) {
            // For the number of vertices, pick one, if it is yellow we need to color it.
            //TODO we hardcode the graph size now. We need to add user input later
            v1 = edges.get(this.r.nextInt(totalVert));
            //   for ( int k = 0; k < v1.getNeighbors().size(); k++){
            v2 = v1.getNeighbors().get(this.r.nextInt(v1.getNeighbors().size()));
            // if (v1.color == Color.YELLOW && v2.color == Color.YELLOW) {
            //We now know we need to color the vertex. We'll loop over each color and check each neighbor for consistency. If the color is not consistent
            //remove it from the available colros
            int neighbor = this.r.nextInt(v2.getNeighbors().size());

            for (int j = 0; j < colorSize; j++) {
                if (!isConsistent(v2.getNeighbors().get(neighbor), colorSize)) {
                    v2.getNeighbors().get(neighbor).removeColor(v2.getNeighbors().get(neighbor).color);
                }

            }
            // if all the colors are removed, we found a conflict and need to backtrack. Reset colors and try again.
            if (v2.getNeighbors().get(neighbor).colorsTried.isEmpty()) {
                resetColors(edges);
                v1.color = Color.YELLOW;
                v2.color = Color.YELLOW;
                v2.getNeighbors().get(neighbor).color = Color.YELLOW;
            }
            // }
        }
        //  }
        //return if a solution was found
        return isGoal(v1, edges);
    }

    // set the vertex's available colors back to default
    public void resetColors(ArrayList<Vertex> edges) {
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).resetColors();
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
