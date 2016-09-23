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

    Random r = new Random();
    final Point2D point;
    final ArrayList<Vertex> neighbors = new ArrayList<>();
    Color color;
    boolean visited;
    ArrayList<Color> colorsTried = new ArrayList<>();

    public Vertex(Point2D current, Color color, int size) {
        this.point = current;
        this.color = color;
        this.visited = false;
        this.colorsTried.add(Color.BLUE);
        this.colorsTried.add(Color.GREEN);
        this.colorsTried.add(Color.RED);
        if (size == 4){
        this.colorsTried.add(Color.BLACK);
        }
    }

    public void resetColors(int size) {
        colorsTried.clear();
        this.colorsTried.add(Color.BLUE);
        this.colorsTried.add(Color.GREEN);
        this.colorsTried.add(Color.RED);
        if (size == 4) {
            this.colorsTried.add(Color.BLACK);
        }
    }

    public void removeColor(Color color) {
        this.colorsTried.remove(color);
    }

    public void addColor(Color color) {
        this.colorsTried.add(color);
    }

    public Color getFCColor() {
        return this.colorsTried.get(r.nextInt(this.colorsTried.size()));

    }

    public void addNeighbor(Vertex n) {
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
