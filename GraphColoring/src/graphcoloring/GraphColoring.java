/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

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
    GraphGenerator points = new GraphGenerator();
    JFrame frame = new JFrame("Graph Coloring Problem");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(points);
    frame.setSize(600, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }
}
