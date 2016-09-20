/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author q46p749
 */
public class GraphGenerator extends JPanel {

    private ArrayList<Vertex> allPoints = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Line2D> edges = new ArrayList<>();
        Graphics2D g2d = (Graphics2D) g;
        Color color;
        for (int i = 0; i < 100; i++) {
            Dimension size = getSize();
            int w = size.width;
            int h = size.height;
            Random r = new Random();
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;

            Point2D p = new Point2D.Double((double) x, (double) y);

            if (x % 3 == 0) {
                color = Color.BLUE;
            } else if (x % 3 > 1) {
                color = Color.GREEN;
            } else {
                color = Color.RED;
            }
            Vertex v = new Vertex(p, color);
            allPoints.add(v);
        }

        connectLines(g2d, allPoints);

        // printNeighbors(allPoints);
    }

    public void connectLines(Graphics2D g2d, ArrayList<Vertex> neighbors) {
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
            // Color the graph based of the Vertex's color
            g2d.setStroke(new BasicStroke(6));
            if (point.getX() > 0) {
                g2d.setColor(neighbors.get(i).getColor());
                g2d.draw(new Line2D.Double(neighbors.get(i).getPoint(), neighbors.get(i).getPoint()));
                g2d.draw(new Line2D.Double(point, point));

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));

                Line2D line = new Line2D.Double(neighbors.get(i).getPoint(), point);

                edges.add(line);
                g2d.draw(line);

                if (!neighbors.get(i).getNeighbors().contains(point)) {
                    neighbors.get(i).addNeighbor(point);
                    neighbors.get(vertIndex).addNeighbor(neighbors.get(i).getPoint());
                }
            }
        }
        connectRemainingLines(g2d, neighbors, edges);
    }

    public void connectRemainingLines(Graphics2D g2d, ArrayList<Vertex> neighbors, ArrayList<Line2D> edges) {

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
                    g2d.setStroke(new BasicStroke(6));
                    if (point.getX() > 0) {
                        g2d.setColor(neighbors.get(i).getColor());
                        g2d.draw(new Line2D.Double(neighbors.get(i).getPoint(), neighbors.get(i).getPoint()));
                        g2d.draw(new Line2D.Double(point, point));

                        g2d.setColor(Color.BLACK);
                        g2d.setStroke(new BasicStroke(1));

                        Line2D line = new Line2D.Double(neighbors.get(i).getPoint(), point);

                        edges.add(line);
                        g2d.draw(line);

                        if (!neighbors.get(i).getNeighbors().contains(point)) {
                            neighbors.get(i).addNeighbor(point);
                            neighbors.get(vertIndex).addNeighbor(neighbors.get(i).getPoint());
                        }
                    }
                }
            }
            // Color the graph based of the Vertex's color

        }

    }

    public boolean xIntersect(Line2D line1, Line2D line2) {

        if ( (line1.getX1() == line2.getX1() && line1.getY1() == line2.getY1())|| (line1.getX1() == line2.getX2() && line1.getY1() == line2.getY2()) ) {
            return true;
        } else return (line1.getX2() == line2.getX1() && line1.getY2() == line2.getY1())|| (line1.getX2() == line2.getX2() && line1.getY2() == line2.getY2());
    }

//    public boolean yIntersect(Line2D line1, Line2D line2) {
//
//        if (line1.getY1() == line2.getY1() || line1.getY1() == line2.getY2()) {
//            return true;
//        } else return line1.getY2() == line2.getY1() || line1.getY2() == line2.getY2();
//    }

    public boolean intersect(ArrayList<Line2D> edges, Line2D line) {

        for (int i = 0; i < edges.size(); i++) {
            if (line.intersectsLine(edges.get(i)) && !xIntersect(edges.get(i), line) ) {
                return true;
            }
        }

        return false;
    }

    public void printNeighbors(ArrayList<Vertex> n) {

        for (int i = 0; i < n.size(); i++) {
            System.out.println("THE NEIGHBORS FOR: " + n.get(i).getPoint());
            for (int j = 0; j < n.get(i).getNeighbors().size(); j++) {

                System.out.println(" " + n.get(i).getNeighbors().get(j));

            }

        }
    }
}
