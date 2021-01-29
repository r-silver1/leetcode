package com.example.leetcode.tictac.board;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import com.example.leetcode.tictac.Shape.Shape;
import com.example.leetcode.tictac.Decoder.Decoder;
import java.awt.event.MouseEvent;


/*
2D graphics with java: https://books.trinket.io/thinkjava/appendix-b.html
 */
public class board extends Canvas {
//    public static ArrayList<Line2D> shapeList = new ArrayList<Line2D>();
    public static ArrayList<Shape> shapeList = new ArrayList<>();
    public static Canvas canvas = new board();
    public static Boolean xTurn = true;

    public static void initBoard(){
        //application ends when JFrame closes
        JFrame frame = new JFrame("GameBoard");
        //create a new canvas which is really just this class?
        canvas.setSize(300, 300);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

    }

    //https://stackoverflow.com/questions/1692677/how-to-create-a-jbutton-with-a-menu/1693326#1693326
    public static void initMouse(){
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] colRow = Decoder.cellDecode(e.getX(), e.getY(), canvas.getWidth(), canvas.getHeight());
                System.out.println("col, row: " + colRow[0] + "," + colRow[1]);
                String thisLetter;
                if(xTurn){
                    thisLetter = "X";
                }else{
                    thisLetter = "O";
                }
                xTurn = !xTurn;
                shapeList.add(new Shape(colRow[0], colRow[1], thisLetter));
                canvas.repaint();

            }

        });
    }


    public static void main(String[] arg){
        initBoard();
//        initShapes();
        initMouse();
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
            g2.drawString(curr.getLetter(), Math.round((curr.getCol()*(this.getWidth()/3.0)-this.getWidth()/5.0)), Math.round((curr.getRow()*(this.getHeight()/2.7)-this.getHeight()/5.0)));
        }
    }
}
