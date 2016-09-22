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
public class GeneticAlgorithm {
    private ArrayList<ArrayList<Vertex>> population = new ArrayList<>();
    private ArrayList<Vertex> graph;
    private ArrayList<Vertex> youngestChild = new ArrayList<>();
    private ArrayList<Color> colorList;
    private int max_attempts = 0;
    private double mutateProb = 0;
    private Random selection = new Random();
    
    /**
     * Constructor
     */
    public GeneticAlgorithm(ArrayList<Vertex> graph, int n, ArrayList<Color> colorList, int max_attempts) {
        this.graph = graph;
        createPopulation(n);
        this.colorList = colorList;
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
                   if (getFitness(reproduce(parentA, parentB)) == 0)
                       return true;
        }
        return false;
    }
    /**
     * Reproduce Method
     */
    public ArrayList<Vertex> reproduce(ArrayList<Vertex> a, ArrayList<Vertex> b) {
        ArrayList<Vertex> child = a;
        // swaps the colors of the first half of vertices in A with those in B
        for (int i = 0; i < a.size()/2; i++) {
            child.get(i).setColor(b.get(i).getColor());
        }
        youngestChild = mutate(youngestChild, 19);
        population.add(youngestChild);
        return youngestChild;
    }
    
    /**
     * Tournament Method
     */
    private ArrayList<Vertex> tournament() {
        ArrayList<Vertex> graphA = new ArrayList<>();
        ArrayList<Vertex> graphB = new ArrayList<>();
        
        // get a random member from graph list
        graphA = population.get(selection.nextInt(population.size()));
        // get a second random member from graph list
        graphB = population.get(selection.nextInt(population.size()));
        
        // get the fitness of each randomly selected graph and compare them
        // a lower fitness score is better
        if (getFitness(graphA) < getFitness(graphB))
            return graphA;
        else
            return graphB;
    }
    
    /**
     * Get Mutation Probability Method
     */
    
    /**
     * Set Mutation Probability Method
     */

    
    /**
     * Mutate Method
     */
    public ArrayList<Vertex> mutate(ArrayList<Vertex> child, int n) {
        Random ran = new Random();
        if (ran.nextInt(n) == 0) {
            child.get(ran.nextInt(child.size())).setColor(colorList.get(ran.nextInt(colorList.size())));
        }
        return child;
    }
    
    /**
     * Evaluate Fitness Method
     */
    public int getFitness(ArrayList<Vertex> graph) {
        int count = 0;
        // iterate once per vertex in the graph -- OUTER LOOP
        for (int i = 0; i < graph.size(); i++) {
            // iterate once per neighbor of the vertex at i -- INNER LOOP
            for (int j = 0; j < graph.get(i).getNeighbors().size(); j++) {
                if (graph.get(i).getColor() == graph.get(i).getNeighbors().get(j).getColor())
                    count +=1;
                    break;
            }
        }
        return count;
    }
    
    public ArrayList<Vertex> getGraph() {
        return youngestChild;
    }
    
        // recolors graph to create an array population for the GA
    private ArrayList<ArrayList<Vertex>> createPopulation(int n) {
        Random rGen = new Random();
        ArrayList<Vertex> tempgraph = new ArrayList<>();
        tempgraph = graph;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tempgraph.size(); j++) {
                tempgraph.get(j).setColor(colorList.get(rGen.nextInt(colorList.size())));
            }
            population.add(tempgraph);
        }
        return population;
    }
}
