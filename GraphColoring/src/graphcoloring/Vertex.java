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
    
    public Vertex(Point2D current, Color color){
        this.point = current; 
        this.color = color;
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
    
    public Color getColor() {
        return this.color;
    }
    
}
