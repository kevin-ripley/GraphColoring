/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.util.ArrayList;

/**
 *
 * @author bryan
 */
public class MinConflicts {
    private ArrayList<Vertex> graph;
    private int max_attempts;
    
    /**
     * Constructor
     * 
     * @param graph A randomly and completely colored graph type ArrayList<Vertex>
     * @param max_attempts integer maximum number of iterations through the algorithm
     */
    public MinConflicts(ArrayList<Vertex> graph, int max_attempts) {
        this.graph = graph;
        this.max_attempts = max_attempts;
    }
    
    /**
     * Find Solution Method
     */
    public boolean findSolution() {
        return false;
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
     * Get Graph Function
     * @return the state of the graph after a solution was found, or after max_attempts
     */
    public ArrayList<Vertex> getGraph() {
        return graph;
    }
}
