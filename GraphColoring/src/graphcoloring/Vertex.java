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
    final ArrayList<Point2D> neighbors = new ArrayList<>();
    Graphics2D color;
    
    public Vertex(Point2D current, Graphics2D color){
        this.point = current; 
        this.color = color;
    }
    public void addNeighbor(Point2D n){
        this.neighbors.add(n);
    }
    
    public ArrayList<Point2D> getNeighbors() {
        return this.neighbors; 
    }
    
    public Point2D getPoint() {
        
        return this.point;
    }
    
}
