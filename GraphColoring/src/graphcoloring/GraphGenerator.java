/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
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
   
    for (int i = 0; i < 5; i++) { 
      Dimension size = getSize();
      int w = size.width ;
      int h = size.height;
        System.out.println(i);
      Random r = new Random();
      int x = Math.abs(r.nextInt()) % w;
      int y = Math.abs(r.nextInt()) % h;
     
      Point2D p = new Point2D.Double((double)x,(double)y);
      //Point2D p2 = new Point2D.Double((double)y,(double)x);
      neighbors.add(p);
      
       // System.out.println(neighbors.get(i));
        
      g2d.setStroke(new BasicStroke(3));
      g2d.draw(new Line2D.Double(p, p));
      
    }
    
  }


}
