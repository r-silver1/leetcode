package com.example.leetcode.tictac.board;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import com.example.leetcode.tictac.Shape.Shape;

/*
2D graphics with java: https://books.trinket.io/thinkjava/appendix-b.html
 */
public class board extends Canvas {
//    public static ArrayList<Line2D> shapeList = new ArrayList<Line2D>();
    public static ArrayList<Shape> shapeList = new ArrayList<Shape>();
    public static Canvas canvas = new board();

    public static void initBoard(){
        //application ends when JFrame closes
        JFrame frame = new JFrame("GameBoard");
        //create a new canvas which is really just this class?
        canvas.setSize(300, 300);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public static void initShapes(){
//        Line2D[] lineArr = {};
//        lineArr.
//        shapeList.add(new Line2D.Double(this.getWidth()*i/3.0, 0, this.getWidth()*i/3.0, this.getHeight()))
        Shape O1 = new Shape(3, 1, "O");
        Shape O2 = new Shape(3, 2, "O");
        Shape O3 = new Shape(3, 3, "O");
//        O1.print();
        shapeList.add(O1);
        shapeList.add(O2);
        shapeList.add(O3);
        shapeList.get(0).print();

    }

    public static void main(String[] arg){
        initBoard();
        initShapes();
    }

    public void paint(Graphics g){
        //render using 2d graphics
        Graphics2D g2 = (Graphics2D) g;
        //draw tic tac toe lines using line2d primitive https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html
        for(int i = 1; i<3; i+=1){
            g2.draw(new Line2D.Double(this.getWidth()*i/3.0, 0, this.getWidth()*i/3.0, this.getHeight()));
            g2.draw(new Line2D.Double(0, this.getHeight()*i/3.0, this.getWidth(), this.getHeight()*i/3.0));
        }
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        for(Shape curr : shapeList){
            curr.print();
//            g2.drawString(shapeList.get(0).getLetter(), Math.round((shapeList.get(0).getCol()*(this.getWidth()/3.0)-this.getWidth()/5.0)), Math.round((shapeList.get(0).getCol()*(this.getHeight()/2.7)-this.getHeight()/5.0)));
            g2.drawString(curr.getLetter(), Math.round((curr.getCol()*(this.getWidth()/3.0)-this.getWidth()/5.0)), Math.round((curr.getRow()*(this.getHeight()/2.7)-this.getHeight()/5.0)));
        }
//        g2.drawString(shapeList.get(0).getLetter(), Math.round((shapeList.get(0).getCol()*(this.getWidth()/3.0)-this.getWidth()/5.0)), Math.round((shapeList.get(0).getCol()*(this.getHeight()/2.7)-this.getHeight()/5.0)));
    }
}
