/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author q46p749
 */
public class GraphGenerator extends JPanel {

    private ArrayList<Vertex> allPoints = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    Random r = new Random();
    int totalVert = 0;
    int colorSize = 3;
    int graphType = 0;

    public GraphGenerator(ArrayList<Color> colorList, int totalVert, int c, int g) {
        this.colorList = colorList;
        this.totalVert = totalVert;
        this.colorSize = c;
        this.graphType = g;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<ArrayList<Vertex>> list = new ArrayList<>();
        ArrayList<Line2D> edges = new ArrayList<>();
        Graphics2D g2d = (Graphics2D) g;
        Color color;
            allPoints = generatePoints();
            try {
                connectLines(g2d, allPoints);
            } catch (IOException ex) {
                Logger.getLogger(GraphGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public ArrayList<Vertex> generatePoints() {
        allPoints.clear();
        for (int i = 0; i < this.totalVert; i++) {
            Dimension size = getSize();
            int w = size.width;
            int h = size.height;
            int x = Math.abs(this.r.nextInt()) % w;
            int y = Math.abs(this.r.nextInt()) % h;

            Point2D p = new Point2D.Double((double) x, (double) y);
            Vertex v = new Vertex(p, Color.YELLOW, this.colorSize);
            allPoints.add(v);
        }
        return allPoints;
    }

    public ArrayList<Vertex> connectLines(Graphics2D g2d, ArrayList<Vertex> neighbors) throws IOException {
        ArrayList<Line2D> edges = new ArrayList<>();
        int vertIndex = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            double min = 9999;
            Point2D point = new Point2D.Double();
            for (int j = i; j < neighbors.size(); j++) {
                // We need to get the distance from neighbors.get(i).getPoint() and then compare it with our next point
                // For each point if the distance is shorter set the temp variable point to the new nearest Vertex
                double distance = neighbors.get(i).getPoint().distance(neighbors.get(j).getPoint().getX(), neighbors.get(j).getPoint().getY());
                if (distance != 0 && distance < min) {
                    if (!intersect(edges, new Line2D.Double(neighbors.get(i).getPoint(), neighbors.get(j).getPoint()))) {
                        min = distance;
                        point = neighbors.get(j).getPoint();
                        vertIndex = j;
                    }
                }
            }
            if (point.getX() > 0) {

                Line2D line = new Line2D.Double(neighbors.get(i).getPoint(), point);

                edges.add(line);

                if (!neighbors.get(i).getNeighbors().contains(point)) {
                    neighbors.get(i).addNeighbor(neighbors.get(vertIndex));
                    neighbors.get(vertIndex).addNeighbor(neighbors.get(i));
                }
            }
        }
        return connectRemainingLines(g2d, neighbors, edges);
    }

    public ArrayList<Vertex> connectRemainingLines(Graphics2D g2d, ArrayList<Vertex> neighbors, ArrayList<Line2D> edges) throws IOException {

        int vertIndex = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            Point2D point = new Point2D.Double();
            for (int j = i; j < neighbors.size(); j++) {
                // We need to get the distance from neighbors.get(i).getPoint() and then compare it with our next point
                // For each point if the distance is shorter set the temp variable point to the new nearest Vertex
                double distance = neighbors.get(i).getPoint().distance(neighbors.get(j).getPoint().getX(), neighbors.get(j).getPoint().getY());
                if (!intersect(edges, new Line2D.Double(neighbors.get(i).getPoint(), neighbors.get(j).getPoint()))) {
                    point = neighbors.get(j).getPoint();
                    vertIndex = j;

                    if (point.getX() > 0) {

                        Line2D line = new Line2D.Double(neighbors.get(i).getPoint(), point);

                        edges.add(line);

                        if (!neighbors.get(i).getNeighbors().contains(neighbors.get(vertIndex)) && neighbors.get(i).getPoint() != point) {
                            neighbors.get(i).addNeighbor(neighbors.get(vertIndex));
                            neighbors.get(vertIndex).addNeighbor(neighbors.get(i));
                        }
                    }
                }
            }
        }
        switch (this.graphType) {
            case 0:
                SimpleBacktracking sb = new SimpleBacktracking();
                System.out.println(sb.solve(neighbors.get(0), neighbors, this.totalVert, this.colorSize));
                break;
            case 1:
                BacktrackingFC fc = new BacktrackingFC();
                System.out.println(fc.solve(neighbors.get(0), neighbors, this.totalVert, this.colorSize));
                break;
            case 2:
                MAC mac = new MAC();
                System.out.println(mac.solve(neighbors.get(0), neighbors.get(1), neighbors, this.totalVert, this.colorSize));
                break;
            case 3:
                colorGraph(neighbors, this.colorSize);
                MinConflicts mc = new MinConflicts(neighbors, colorList, 10000);
                System.out.println(mc.findSolution(this.colorSize));
                break;
            case 4:
                colorGraph(neighbors, this.colorSize);
                GeneticAlgorithm ga = new GeneticAlgorithm(neighbors, this.totalVert, colorList, 100000);
                System.out.println(ga.search());
                neighbors = ga.getGraph();
                break;
            default:
                System.out.println("Error");
        }
        paintLines(g2d, edges, neighbors);
        return neighbors;
    }

    public void paintLines(Graphics2D g2d, ArrayList<Line2D> edges, ArrayList<Vertex> points) {
        g2d.setStroke(new BasicStroke(6));
        for (int j = 0; j < points.size(); j++) {
            g2d.setColor(points.get(j).getColor());
            g2d.draw(new Line2D.Double(points.get(j).getPoint(), points.get(j).getPoint()));
        }
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < edges.size(); i++) {
            g2d.setColor(Color.BLACK);
            g2d.draw(edges.get(i));
        }
        g2d.dispose();
    }

    public boolean xIntersect(Line2D line1, Line2D line2) {

        if ((line1.getX1() == line2.getX1() && line1.getY1() == line2.getY1()) || (line1.getX1() == line2.getX2() && line1.getY1() == line2.getY2())) {
            return true;
        } else {
            return (line1.getX2() == line2.getX1() && line1.getY2() == line2.getY1()) || (line1.getX2() == line2.getX2() && line1.getY2() == line2.getY2());
        }
    }

    public boolean intersect(ArrayList<Line2D> edges, Line2D line) {

        for (int i = 0; i < edges.size(); i++) {
            if (line.intersectsLine(edges.get(i)) && !xIntersect(edges.get(i), line)) {
                return true;
            }
        }

        return false;
    }

    public void printNeighbors(ArrayList<Vertex> n) {

        for (int i = 0; i < n.size(); i++) {
            System.out.println("THE NEIGHBORS FOR: " + n.get(i).getPoint());
            for (int j = 0; j < n.get(i).getNeighbors().size(); j++) {

                System.out.println(" " + n.get(i).getNeighbors().get(j).getPoint());

            }

        }
    }

    public int numColors() {
        return colorList.size();
    }

    public ArrayList<Color> getColors() {
        return colorList;
    }

    // recolors graph to create an array population for the GA
    public ArrayList<ArrayList<Vertex>> gaPopulation(int n) {
        ArrayList<ArrayList<Vertex>> population = new ArrayList<>();
        Random rGen = new Random();
        for (int i = 0; i < n; i++) {
            ArrayList<Vertex> tempGraph = allPoints;
            for (int j = 0; j < allPoints.size(); j++) {
                tempGraph.get(j).setColor(colorList.get(rGen.nextInt(colorList.size())));
            }
            population.add(tempGraph);
        }
        return population;
    }

    private void colorGraph(ArrayList<Vertex> graph, int size) {
        Random r = new Random();
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).setColor(colorList.get(this.r.nextInt(size)));

        }
    }
}
