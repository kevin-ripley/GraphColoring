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
    private ArrayList<ArrayList<Vertex>> children = new ArrayList<>();
    private final ArrayList<Vertex> graph;
    private ArrayList<Vertex> child = new ArrayList<>();
    private final ArrayList<Color> colorList;
    private int populationSize;
    private int max_attempts = 0;
    private int mutateProb = 0;
    private Random selection = new Random();
    
    /**
     * Constructor
     * 
     * @param graph an ArrayList<Vertex> object of connected vertices
     * @param populationSize an integer value for the initial population of graph colorings to generate
     * @param colorList a list of colors to use in the coloring of the graph
     * @param max_attempts the maximum number of children to be spawned
     */
    public GeneticAlgorithm(ArrayList<Vertex> graph, int populationSize, ArrayList<Color> colorList, int max_attempts) {
        this.graph = graph;
        this.populationSize = populationSize;
        this.colorList = colorList;
        this.max_attempts = max_attempts;
    }
    
    /**
     * Search Method
     * 
     * @return true if a solution is found with 0 conflicts, false if no solution found
     */
    public boolean search() {
        generatePopulation(populationSize);
        ArrayList<Vertex> parentA;
        ArrayList<Vertex> parentB;
        for (int i = 0; i < max_attempts; i++) {
                    // select parents through a tournament
            parentA = tournament();
            parentB = tournament();
            // reproduce selected participants
            if (isSolution(reproduce(parentA, parentB))) 
                return true;
            removeLeastFit();
        }
        child = getMostFit();
        return false;
    }
    /**
     * Reproduce Method
     * 
     * @return the child of parent a and b
     * @param a parent a to be crossed
     * @param b parent b to be crossed
     */
    private ArrayList<Vertex> reproduce(ArrayList<Vertex> a, ArrayList<Vertex> b) {
        child = a;
        int crossover = selection.nextInt(child.size());
        // swaps the colors of the first half of vertices in A with those in B
        for (int i = 0; i < crossover; i++) {
            child.get(i).setColor(b.get(i).getColor());
        }
        child = mutate(child, 9);
        population.add(child);
//        if (selection.nextInt(1) == 0)
//            population.remove(a);
//        else
//            population.remove(b);
        return child;
    }
    
    /**
     * Tournament Method
     * 
     * @return the more fit of two randomly selected graphs
     */
    private ArrayList<Vertex> tournament() {
        ArrayList<Vertex> graphA;
        ArrayList<Vertex> graphB;
        
        // get a random member from graph list
        graphA = population.get(selection.nextInt(population.size()));
        // get a second random member from graph list
        graphB = population.get(selection.nextInt(population.size()));
        
        // get the fitness of each randomly selected graph and compare them
        // a higher fitness score is better
        if (getFitness(graphA) > getFitness(graphB))
            return graphA;
        else
            return graphB;
    }
    
    /**
     * Get Mutation Probability Method
     * 
     * @return probability of mutation represented as 1 in 1+(returned value) probability
     */
    public int getMutationProb() {
        return mutateProb;
    }
    
    /**
     * Set Mutation Probability Method
     * 
     * @param n the new probability of mutation as 1 in 1+n chance of mutation (n=0 is 100% chance)
     */
    public void setMutationProb(int n) {
        mutateProb = n;
    }

    
    /**
     * Mutate Method
     * 
     * @param child a recently reproduced child to have a vertex mutated with probability 1 in 1+n
     * @param n probability of mutation as 1 in 1+n chance
     * @return child with possibility of mutation at a single vertex
     */
    private ArrayList<Vertex> mutate(ArrayList<Vertex> child, int n) {
        Random ran = new Random();
        if (ran.nextInt(n) == 0) {
            child.get(ran.nextInt(child.size())).setColor(colorList.get(ran.nextInt(colorList.size())));
        }
        return child;
    }

    /**
     * Fitness Function
     * 
     * @param graph the graph to be evaluated for fitness
     * @return integer value for graph fitness, a higher number is more fit
     */
    private int getFitness(ArrayList<Vertex> graph) {
        int count = 0;
        // iterate once per vertex in the graph -- OUTER LOOP
        for (int i = 0; i < graph.size(); i++) {
            // iterate once per neighbor of the vertex at i -- INNER LOOP
            for (int j = 0; j < graph.get(i).getNeighbors().size(); j++) {
                if (graph.get(i).getColor() != graph.get(i).getNeighbors().get(j).getColor())
                    count +=1;
            }
        }
        return count;
    }
    
    /**
     * Is Solution Method
     * 
     * @param graph a graph object to be evaluated for solution
     * @return true if child is a solution
     */
    private boolean isSolution(ArrayList<Vertex> graph) {
        // iterate once per vertex in the graph -- OUTER LOOP
        for (int i = 0; i < graph.size(); i++) {
            // iterate once per neighbor of the vertex at i -- INNER LOOP
            for (int j = 0; j < graph.get(i).getNeighbors().size(); j++) {
                if (graph.get(i).getColor() == graph.get(i).getNeighbors().get(j).getColor())
                    return false;
            }
        }
        return true;
    }

    
    /**
     * Get Graph Method
     * 
     * @return the most recent child produced
     */
    public ArrayList<Vertex> getGraph() {
        return child;
    }
    
        // recolors graph to create an array population for the GA
    /**
     * Generate Population Method
     * 
     * @param n the integer number of randomly colored graphs for the initial population
     * @return a population represented as an ArrayList of ArrayList<Vertex> objects
     */
    private ArrayList<ArrayList<Vertex>> generatePopulation(int n) {
        Random rGen = new Random();
        ArrayList<Vertex> tempgraph = new ArrayList<>();
        tempgraph = graph;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tempgraph.size(); j++) {
                int colorIndex = rGen.nextInt(colorList.size());
                tempgraph.get(j).setColor(colorList.get(colorIndex));
            }
            population.add(tempgraph);
        }
        return population;
    }
    
    /**
     * Get Population Method
     * 
     * @return Size of the Population when called
     */
    public int getPopulationSize() {
        return populationSize;
    }
    
    /**
     * Get Most Fit Method
     * 
     * @return member of the population with the highest fitness
     */
    private ArrayList<Vertex> getMostFit() {
        ArrayList<Vertex> mostFit = population.get(0);
        for (int i = 0; i < population.size(); i++) {
            if (getFitness(population.get(i)) > getFitness(mostFit))
                mostFit = population.get(i);                
        }
        return mostFit;
    }
    
    private void removeLeastFit() {
        int leastIndex = 0;
        ArrayList<Vertex> leastFit = population.get(0);
        for (int i = 0; i < population.size(); i++) {
            if (getFitness(population.get(i)) < getFitness(leastFit))
                leastFit = population.get(i);
        }
        population.remove(leastFit);
    }
}
