package com.example.leetcode;
import com.example.leetcode.tictac.Shape.Shape;
import com.example.leetcode.tictac.board.board;
import com.example.leetcode.tictac.toe;

public class base {

    public static void initShapes(){
        Shape O1 = new Shape(3, 1, "O");
        Shape O2 = new Shape(3, 2, "O");
        Shape O3 = new Shape(3, 3, "O");
        Shape X1 = new Shape(1, 1, "X");
        Shape X2 = new Shape(2, 2, "X");

        board.shapeList.add(O1);
        board.shapeList.add(O2);
        board.shapeList.add(O3);
        board.shapeList.add(X1);
        board.shapeList.add(X2);

    }

    public static void main(String arg[]){
        System.out.println("base file for leetcode practice");
        String[] argList = {""};
        toe.main(new String[] {""});
        initShapes();
    }
}
