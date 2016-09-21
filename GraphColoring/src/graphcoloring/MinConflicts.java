/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author bryan
 */
public class MinConflicts {
    private ArrayList<Vertex> graph;
    private ArrayList<Vertex> conflictList;
    private ArrayList<Color> colorList;
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
            if (getConflicts() == 0) {
                solution = true;
                attemptsUsed = i;
                break;
            }
            // sets the current vertex to a random conflicted vertex
            currentVert = getRandomVertex(conflictList);
            // minimizes conflicts for the current vertex
            minimizeConflicts(currentVert);
        }
        return solution;    // placeholder
    }
    /**
     * Get Conflicts Method
     * 
     * @return the evaluated number of conflicts found in the graph
     */
    public int getConflicts() {
        int conflicts = 0;
        // remove all from conflict list as conflicts change (do they?)
        conflictList.clear();
        // evaluate each element of graph and move conflicted vertices to conflict list
        // for (int i = 0; i < graph.getSize(); i++)
        //      check color against color of connected neighbors
        //      if conflicted add to conflict list
        //      increment conflicts by 1
        return conflicts;
    }
    
    /**
     * Get Random Vertex Method
     * 
     * @param list an ArrayList of vertices
     * @return a randomly selected vertex from the list passed in based on size
     */
    public Vertex getRandomVertex(ArrayList<Vertex> list) {
        // generate a random integer for index based on remaining size of list
        int index = randomGenerator.nextInt(list.size());     
        // remove a random vertex from the conflict list and return it
        Vertex selection = list.remove(index);
        return selection;
    }
    
    /**
     * Minimize Conflicts Method
     * 
     * @param v a Vertex object to have conflicts minimized
     */
    public void minimizeConflicts(Vertex v) {
        // minimize the conflicts for the vertex
        ArrayList<Vertex> neighbors = v.getNeighbors();
        int min = 100;
        ListIterator<Color> colorITR = colorList.listIterator();
        // add all neighbors to temporary list
        // pick color that minimizes the color conflicts with neighbors
        for (int i = 0; i < colorList.size(); i++) {
            int count = 0;
            Color minColor;
            for (int j = 0; j < neighbors.size(); j++) {
                if (neighbors.get(j).getColor() == v.getColor()) {
                    count += 1;
                }
            }
            if (count < min) {
                minColor = v.getColor();
            }
            v.setColor(colorITR.next());
        }
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
    
    public int getAttemptsUsed() {
        return attemptsUsed;
    }
}
