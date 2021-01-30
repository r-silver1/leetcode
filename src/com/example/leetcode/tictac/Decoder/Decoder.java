package com.example.leetcode.tictac.Decoder;

public class Decoder {
    public static int[] cellDecode(int x, int y, int w, int h){
        System.out.println("cellDecode: " + x + ", " + y);
        //left third
        int row;
        int col;
        if(x <= w/3) {
            //middle third
            col = 1;
        }else if(x > w/3 && x <= 2*w/3){
            col = 2;
        }else{
            //right third
            col = 3;
        }

        //top half
        if(y <= h/3){
            row = 1;
        }else if(y > h/3 && y <= 2*h/3){
            row = 2;
        }else{
            //bottom half
            row = 3;
        }

        int[] retArr = {col, row};
        return (retArr);
    }

    public static int[][] initBoard(){
        int[][] temp = new int[3][3];
        for(int[] col : temp){
            for(int row : col){
                row = 0;
            }
        }
        return temp;
    }

    public static void printBoard(int[][] temp){
        System.out.println("Board: ");
        for(int[] col : temp){
            System.out.print("[");
            for(int row : col){
                System.out.print(row + ",");
            }
            System.out.println("]");
        }
    }


}
