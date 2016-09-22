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
public class GeneticAlgorithm {
    private ArrayList<ArrayList<Vertex>> graphList = new ArrayList<>();
    private ArrayList<Vertex> mostFitChild = new ArrayList<>();
    private int max_attempts = 0;
    private double mutateProb = 0;
    
    /**
     * Constructor
     */
    public GeneticAlgorithm(ArrayList<ArrayList<Vertex>> graphList, int max_attempts) {
        this.graphList = graphList;
        this.max_attempts = max_attempts;
    }
    
    /**
     * Search Method
     */
    public boolean search() {
        ArrayList<Vertex> parentA = new ArrayList<>();
        ArrayList<Vertex> parentB = new ArrayList<>();
        for (int i = 0; i < max_attempts; i++) {
                    // select parents through a tournament
                   parentA = tournament();
                   parentB = tournament();
                   // reproduce selected participants
                   reproduce(parentA, parentB);
        }
 
        
        
        return false;
    }
    /**
     * Reproduce Method
     */
    public ArrayList<Vertex> reproduce(ArrayList<Vertex> a, ArrayList<Vertex> b) {
        ArrayList<Vertex> child = new ArrayList<>();
        return child;
    }
    /**
     * Tournament Method
     */
    private ArrayList<Vertex> tournament() {
        return null;
    }
    
    /**
     * Get Mutation Probability Method
     */
    
    /**
     * Set Mutation Probability Method
     */
    
    /**
     * Repair Function Method
     */
    
    /**
     * Mutate Method
     */
    
    /**
     * Evaluate Fitness Method
     */
    
}
