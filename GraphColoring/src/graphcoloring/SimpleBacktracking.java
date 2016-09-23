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
public class SimpleBacktracking {

    Color[] colors = new Color[4];
    Random r = new Random();

    public SimpleBacktracking() {
        this.colors[0] = Color.blue;
        this.colors[1] = Color.GREEN;
        this.colors[2] = Color.RED;
        this.colors[3] = Color.black;
    }

    public boolean solve(Vertex v, ArrayList<Vertex> edges, int totalVert, int colorSize) {
        //We'll use a while loop instead of recursion to avoid stack overflow. Some of the paths can take a while to find a solution and recursion generally puts too much on the stack
        while (remainingVerticies(edges)) {
            // For the number of vertices, pick one, if it is yellow we need to color it.
            //TODO we hardcode the graph size now. We need to add user input later
            v = edges.get(this.r.nextInt(totalVert));

            if (v.color == Color.YELLOW) {
                //We now know we need to color the vertex. We'll loop over each color and check if it is valid. If it is valid keep the color and break out of the loop.
                //Yes I know using breaks is generally bad practice. Deal with it. :)
                for (int j = 0; j < colorSize; j++) {
                    if (isSafe(v, j)) {
                        break;
                    } else {
                        // If the color was not valid we need to backtrack so set the neighbor to this vetice back to Yellow (or uncolored)
                        v.color = Color.YELLOW;
                        v.getNeighbors().get(this.r.nextInt(v.getNeighbors().size())).color = Color.YELLOW;
                    }
                }
            }
        }
        //return if a solution was found
        return isGoal(v, edges);
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

    public Color setColor(int j) {
        return colors[j];
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
        v.color = setColor(j);
        for (int i = 0; i < v.neighbors.size(); i++) {
            if (v.color == v.neighbors.get(i).color) {
                return false;
            }
        }
        return true;
    }

}
