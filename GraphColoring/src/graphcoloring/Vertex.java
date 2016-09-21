/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Vertex {
    final Point2D point; 
    final ArrayList<Vertex> neighbors = new ArrayList<>();
    Color color;
    boolean visited;
    int[] colorsTried = new int[3];
    
    public Vertex(Point2D current, Color color){
        this.point = current; 
        this.color = color;
        this.visited = false;
        this.colorsTried[0] = 0;
        this.colorsTried[1] = 0;
        this.colorsTried[2] = 0;
        
    }
    
    public int[] getColorsTried() {
        return this.colorsTried;
    }
    
    public void setColorsTried(int i){
        this.colorsTried[i] = 1;
    }
    
    public void addNeighbor(Vertex n){
        this.neighbors.add(n);
    }
    
    public ArrayList<Vertex> getNeighbors() {
        return this.neighbors; 
    }
    
    public Point2D getPoint() {
        return this.point;
    }
    
   public boolean isVisited() {
       
       return this.visited;
   }
   
   public void setVisited(boolean v) {
       this.visited = v;
   }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color color) {
        this.color = color;     
    }
    
}
