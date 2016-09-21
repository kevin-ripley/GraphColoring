/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphcoloring;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public class SimpleBacktracking {

    public SimpleBacktracking() {

    }

    public boolean solve(ArrayList<Vertex> v) {
        int i = 0;
       
        
        
        for (int j = 0;j < v.size(); j++){
            v.get(j).setColor(Color.PINK);
        }
//            if (isGoal(v)) {
//              // for(int j = 0; j < v.neighbors.size(); j ++){
//                   solve(v.neighbors.get(0));
//               //}
//            }
//            else {
//            //    solve(v);
//            }
        return false;
    }

    public void changeColor(Vertex v) {
        Color blue = Color.BLUE;
        Color green = Color.GREEN;
        Color red = Color.RED;
        Color[] colors = new Color[3];
        colors[0] = blue;
        colors[1] = green;
        colors[2] = red;

        v.setColor(colors[2]);

    }

    public boolean isGoal(Vertex v) {

//        for (int i = 0; i < v.neighbors.size(); i++) {
//            if (v.color == v.neighbors.get(i).color) {
        changeColor(v);
        return true;
//            }
//
//        }
//        return true;
    }

}
