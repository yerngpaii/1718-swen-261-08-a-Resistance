package com.webcheckers.model;

import java.util.Arrays;
import java.util.Collections;

public class Board {

    private int width;
    private int height;

    private Row[] board;

    public Board(int width, int height){
        this.width=width;
        this.height=height;
        board=new Row[height];
        boardInit();
    }

    private void boardInit(){
        for(int i=0; i<height; i++){
            if(i%2==0){
                board[i]=new Row(width, "white",i);
            }else{
                board[i]=new Row(width,"black",i);
            }
        }
    }

    public Row[] getBoard(String opponent, String summoner){
        if(opponent.equals(summoner)){
            Row[] reverseBoard=new Row[height];
            System.arraycopy( board, 0, reverseBoard, 0, height );
            Collections.reverse(Arrays.asList(reverseBoard));
            return reverseBoard;
        }
        return board;
    }

    public Piece removePiece(int row, int col){
        return board[row].removePiece(col);
    }

    public void setPiece(Piece piece, int row, int col){
      board[row].setPiece(piece ,col);
    }

    public Row getRow(int index){
      return board[index];
    }


}
