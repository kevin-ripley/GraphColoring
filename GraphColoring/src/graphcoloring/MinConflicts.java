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
 * @author Bryan Downs
 */
public class MinConflicts {
    private ArrayList<Vertex> graph;
    private ArrayList<Vertex> conflictList = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private Random randomGenerator = new Random();
    private int max_attempts;
    private int attemptsUsed = 0;
    private boolean solution = false;
    
    /**
     * Constructor
     * 
     * @param graph A randomly and completely colored graph type ArrayList<Vertex>
     * @param max_attempts integer maximum number of iterations through the algorithm
     */
    public MinConflicts(ArrayList<Vertex> graph, ArrayList<Color> colorList, int max_attempts) {
        this.graph = graph;
        this.colorList = colorList;
        this.max_attempts = max_attempts;
    }
    
    /**
     * Find Solution Method
     * 
     * @return a boolean True if solution is found or False if not found
     */
    public boolean findSolution() {
        Vertex currentVert;
        // searches for a solution until found or max_attempts reached
        for (int i = 0; i < max_attempts; i++) {
            attemptsUsed = i;
            if (getConflicts() == 0) {
                solution = true;
                break;
            }
            // sets the current vertex to a random conflicted vertex
            currentVert = getRandomVertex(conflictList);
            // minimizes conflicts for the current vertex
            minimizeConflicts(currentVert);
        }
        System.out.println(attemptsUsed);
        return solution;    // placeholder
    }
    /**
     * Get Conflicts Method
     * 
     * @return the evaluated number of conflicts found in the graph
     */
    private int getConflicts() {
        int conflicts = 0;
        // remove all from conflict list as conflicts change (do they?)
        conflictList.clear();
        // evaluate each element of graph and move conflicted vertices to conflict list
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).neighbors.size(); j++) {
                if (graph.get(i).getColor() == graph.get(i).neighbors.get(j).getColor()) {
                    conflicts += 1;
                    if (!conflictList.contains(graph.get(i))) {
                        conflictList.add(graph.get(i));
                    } else {
                        break;
                    }
                }
            }
        }
        //System.out.println(conflictList.size());
        return conflicts;
    }
    
    /**
     * Get Random Vertex Method
     * 
     * @param list an ArrayList of vertices
     * @return a randomly selected vertex from the list passed in based on size
     */
    private Vertex getRandomVertex(ArrayList<Vertex> list) {
        // generate a random integer for index based on remaining size of list
        int index = randomGenerator.nextInt(list.size());
        // System.out.println(list.size());
        // remove a random vertex from the conflict list and return it
        Vertex selection = list.remove(index);
        return selection;
    }
    
    /**
     * Minimize Conflicts Method
     * 
     * @param v a Vertex object to have conflicts minimized
     */
    private void minimizeConflicts(Vertex v) {
        // minimize the conflicts for the vertex
        ArrayList<Vertex> neighbors = v.getNeighbors();
        // System.out.println(v.getNeighbors().size());
        int min = 100;
        Color minColor = v.getColor();
        // add all neighbors to temporary list
        // pick color that minimizes the color conflicts with neighbors
        for (int i = 0; i < colorList.size(); i++) {
            int count = 0; 
            v.setColor(colorList.get(i));
            for (int j = 0; j < neighbors.size(); j++) {
                if (neighbors.get(j).getColor() == v.getColor()) {
                    count += 1;
                }
            }
            if (count < min) {
                min = count;
                minColor = v.getColor();
            }
        }
        v.setColor(minColor);
        // end.
    }
    
    /**
     * Get Max Attempts Method
     * @return the current value for maximum attempts
     */
    public int getMaxAttempts() {
        return max_attempts;
    }
    
    /**
     * Set Max Attempts Method
     * @param an integer for the new maximum attempts for the algorithm
     */
    public void setMaxAttempts(int n) {
        max_attempts = n;
    }
    
    /**
     * Get Graph Method
     * @return the state of the graph after a solution was found, or after max_attempts
     */
    public ArrayList<Vertex> getGraph() {
        return graph;
    }
    
    /**
     * Get Attempts Used Method
     * @return number of conflict minimizations used in the attempt to solve the graph
     */
    public int getAttemptsUsed() {
        return attemptsUsed;
    }
}
