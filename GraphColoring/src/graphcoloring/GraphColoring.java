/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author q46p749
 */
public class GraphColoring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
    ArrayList<Color> colorList = new ArrayList<>();
    colorList.add(Color.BLUE);
    colorList.add(Color.GREEN);
    colorList.add(Color.RED);
//    colorList.add(Color.BLACK);
    
    GraphGenerator points = new GraphGenerator(colorList);
    JFrame frame = new JFrame("Graph Coloring Problem");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.add(points);
    }
}
