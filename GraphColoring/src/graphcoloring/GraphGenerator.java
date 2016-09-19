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
    private ArrayList<Point2D> neighbors = new ArrayList<Point2D>();
    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    Graphics d = (g);
  
    
    g2d.setColor(Color.BLUE);
   
    for (int i = 0; i <10; i++) { 
      Dimension size = getSize();
      int w = size.width ;
      int h = size.height;
      Random r = new Random();
      int x = Math.abs(r.nextInt()) % w;
      int y = Math.abs(r.nextInt()) % h;
     
      Point2D p = new Point2D.Double((double)x,(double)y);
      neighbors.add(p);
      
      connectLines(g2d, neighbors);
      
      
      
    }
    
  }

 public void connectLines(Graphics2D g2d, ArrayList<Point2D> neighbors){
      
     
     for(int i = 0; i < neighbors.size(); i++){
      double min = 999;
      Point2D point = new Point2D.Double(); 
       for(int j = 0; j < neighbors.size(); j++ ){
           double distance = neighbors.get(i).distance(neighbors.get(j).getX(),neighbors.get(j).getY());
           if(distance != 0 && distance < min){
               min = distance;
               point = neighbors.get(j);
           }
       }
      g2d.setStroke(new BasicStroke(3));
      g2d.draw(new Line2D.Double(neighbors.get(i), point));
      
     }
 
    }
    
}
