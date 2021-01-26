package com.example.leetcode.tictac.board;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import javax.swing.JFrame;

/*
2D graphics with java: https://books.trinket.io/thinkjava/appendix-b.html
 */
public class board extends Canvas {
    public static void main(String[] arg){
        //application ends when JFrame closes
        JFrame frame = new JFrame("GameBoard");
        //create a new canvas which is really just this class?
        Canvas canvas = new board();
        canvas.setSize(300, 300);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g){
        //render using 2d graphics
        Graphics2D g2 = (Graphics2D) g;
        //draw tic tac toe lines using line2d primitive https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html
        for(int i = 1; i<3; i+=1){
            g2.draw(new Line2D.Double(this.getWidth()*i/3.0, 0, this.getWidth()*i/3.0, this.getHeight()));
            g2.draw(new Line2D.Double(0, this.getHeight()*i/3.0, this.getWidth(), this.getHeight()*i/3.0));
        }
    }
}
