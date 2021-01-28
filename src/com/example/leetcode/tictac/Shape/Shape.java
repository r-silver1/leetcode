package com.example.leetcode.tictac.Shape;

public class Shape {
    int col;
    int row;
    String letter;

    public Shape(int c, int r, String l){
        col = c;
        row = r;
        letter = l;
    }

    public void print(){
        System.out.println("shape: col: " + col + ", row: " + row + ", letter: " + letter);
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public String getLetter(){
        return letter;
    }

}
