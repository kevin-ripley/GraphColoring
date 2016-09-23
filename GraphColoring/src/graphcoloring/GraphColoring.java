/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author q46p749
 */
public class GraphColoring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        int verticies = 0;
        int colorSize = 0;
        int graphType = 0;

        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.RED);
        colorList.add(Color.BLACK);

        verticies = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of vertices for the graph: "));
        colorSize = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of colors (3 or 4): "));
        graphType = Integer.parseInt(JOptionPane.showInputDialog("Choose the graph type \n 0: Simple Backtracking \n 1: Backtracking with Forward Checking \n 2: Backtracking with Constraint Propagation \n 3: Min Conflicts \n 4: Genetic Algorithm "));

        GraphGenerator points = new GraphGenerator(colorList, verticies, colorSize, graphType);
        JFrame frame = new JFrame("Graph Coloring Problem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(points);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
