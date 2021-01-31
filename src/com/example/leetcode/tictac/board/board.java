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
    public static int[][] boardTiles = new int[3][3];
    public static Boolean globalWin = false;

    public static void initBoard(){
        //application ends when JFrame closes
        JFrame frame = new JFrame("GameBoard");
        //create a new canvas which is really just this class?
        canvas.setSize(300, 300);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        boardTiles = Decoder.initBoardArray();
        Decoder.printBoard(boardTiles);
    }

    public static boolean checkTile(int row, int col){
        if(boardTiles[col-1][row-1] == 0){
            System.out.println("no double");
            return true;
        }else{
            System.out.println("double");
            return false;
        }
    }

    public static void updateTiles(Shape s){
        int tempInt = 0;
        if(s.getLetter() == "X"){
            tempInt = -1;
        }else{
            tempInt = 1;
        }
        boardTiles[s.getRow()-1][s.getCol()-1] = tempInt;
    }

    //return int array: pos 0: 0 for no win; 1 for win
    //                  pos 1: -1 for X, 1 for O
    //                  pos 1-5: for drawing win line: C1R1, C2R2

    public static int[] checkWin(){
        int[] retArray;

        int[] colSum = new int[boardTiles.length];
        //row wins
        for(int i = 0; i<boardTiles.length; i+=1){
            int rowSum = 0;
            for(int j = 0; j<boardTiles[i].length; j+=1){
                rowSum+=boardTiles[i][j];
                colSum[j] +=  boardTiles[i][j];
            }
            //win O
            if(rowSum == boardTiles.length){
//                System.out.println("row win: " + (i+1) + " letter: O");
//                retArray = new int[]{1, 1, i, 1, i, boardTiles.length};
                retArray = new int[]{1, 1, 1, i+1, boardTiles.length, i+1};
                return retArray;
            }else if(rowSum == -boardTiles.length){
//                System.out.println("row in: " + (i+1) + "letter: X");
                retArray = new int[]{1, 1, 1, i+1, boardTiles.length, i+1};
                return retArray;
            }
        }

        //column wins
        for(int i = 0; i < colSum.length; i+=1){
            if(colSum[i] == boardTiles.length){
//                System.out.println("col win: " + (i+1) + " letter: O");
//                retArray = new int[]{1, 1, 1, i+1, boardTiles.length, i+1};
                retArray = new int[]{1, 1, i+1, 1, i+1, boardTiles.length};
                return retArray;
            }else if(colSum[i] == -boardTiles.length){
//                System.out.println("col win: " + (i+1) + " letter: X");
                retArray = new int[]{1, 1, i+1, 1, i+1, boardTiles.length};
                return retArray;
            }
        }

        //diag wins
        int diagTopLeft = 0;
        int diagTopRight = 0;
        for(int i = 0; i < boardTiles.length; i+=1){
            diagTopLeft += boardTiles[i][i];
            diagTopRight += boardTiles[i][boardTiles.length-1-i];
        }
        if(diagTopLeft == boardTiles.length){
//            System.out.println("diag win: TL, letter: O");
//            retArray = new int[]{1, 1, 1, 1, boardTiles.length, boardTiles.length};
            retArray = new int[]{1, 1, 1, 1, boardTiles.length, boardTiles.length};
            return retArray;
        }else if(diagTopLeft == -boardTiles.length){
//            System.out.println("diag win: TL, letter: X");
            retArray = new int[]{1, -1, 1, 1, boardTiles.length, boardTiles.length};
            return retArray;
        }

        if(diagTopRight == boardTiles.length){
//            System.out.println("diag win: TR, letter: O");
//            retArray = new int[]{1, 1, 1, boardTiles.length, boardTiles.length, 1};
            retArray = new int[]{1, 1, boardTiles.length, 1, 1, boardTiles.length};
            return retArray;
        }else if(diagTopRight == -boardTiles.length){
//            System.out.println("diag win: TR, letter: X");
            retArray = new int[]{1, 1, boardTiles.length, 1, 1, boardTiles.length};
            return retArray;
        }

        retArray = new int[]{0,0,0,0,0,0};
        return retArray;
    }

    //https://stackoverflow.com/questions/1692677/how-to-create-a-jbutton-with-a-menu/1693326#1693326
    public static void initMouse(){
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] colRow = Decoder.cellDecode(e.getX(), e.getY(), canvas.getWidth(), canvas.getHeight());
//                System.out.println("col, row: " + colRow[0] + "," + colRow[1]);
                if(!globalWin) {
                    if (checkTile(colRow[0], colRow[1])) {
                        String thisLetter;
                        if (xTurn) {
                            thisLetter = "X";
                        } else {
                            thisLetter = "O";
                        }
                        xTurn = !xTurn;
                        Shape thisTurn = new Shape(colRow[0], colRow[1], thisLetter);
                        shapeList.add(thisTurn);
                        updateTiles(thisTurn);
                        Decoder.printBoard(boardTiles);
                    }
                }
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
            g2.setStroke(new BasicStroke(5));
            g2.draw(new Line2D.Double(this.getWidth()*i/3.0, 0, this.getWidth()*i/3.0, this.getHeight()));
            g2.draw(new Line2D.Double(0, this.getHeight()*i/3.0, this.getWidth(), this.getHeight()*i/3.0));
        }
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        for(Shape curr : shapeList){
//            curr.print();
            g2.drawString(curr.getLetter(), Math.round((curr.getCol()*(this.getWidth()/3.0)-this.getWidth()/5.0)), Math.round((curr.getRow()*(this.getHeight()/2.7)-this.getHeight()/5.0)));
        }
        int[] winCheck = checkWin();
        if(winCheck[0] == 1){
            globalWin = true;
            System.out.print("win! : [");
            for(int i : winCheck){
                System.out.print(i +" ,");
            }
            System.out.println("]");
            g2.setStroke(new BasicStroke(10));
            g2.setColor(new Color(255, 0, 0));
            g2.draw(new Line2D.Double(-this.getWidth()/6 + this.getWidth()*winCheck[2]/3.0, -this.getHeight()/6 + this.getHeight()*winCheck[3]/3.0, -this.getWidth()/6 + this.getWidth()*winCheck[4]/3.0, -this.getHeight()/6 + this.getHeight()*winCheck[5]/3.0));
//            g2.draw(new Line2D.Double(50.0, 50.0, 130.0, 130.3));

        }
    }
}
