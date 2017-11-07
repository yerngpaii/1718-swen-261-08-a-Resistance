package com.webcheckers.model;
import java.util.logging.Logger;

public class Square {

    private int index;
    private String color;
    private Piece piece;


    public Square(String color, Piece piece, int index){
        this.index=index;
        this.piece=piece;
        this.color=color;
    }

    /*
    Returns the color of the square
     */
    public String getSquare(){
        return color;
    }

    /*
    Returns true if there's a piece on the square, false if not
     */
    public boolean hasPiece(){
        return piece != null;
    }

    /*
    Returns the piece at the square
     */
    public Piece getPiece(){
      return piece;
    }

    /*
    Sets a piece at the square
     */
    public void setPiece(Piece piece){
      this.piece = piece;
    }

    /*
    Removes the piece on the square and returns it
     */
    public Piece removePiece(){
      Piece p = this.piece;
      this.piece = null;
      return p;
    }

    /*
    Returns the piece type on the square
     */
    public String getPieceType(){
        return piece.getType();
    }

    /*
    Returns the piece color on the square
     */
    public String getPieceColor(){
        return piece.getColor();
    }

    /*
    Returns if the square is a valid square(black)
     */
    public boolean isValid(){
        return color.equals("black");
    }

    /*
    Returns the index
     */
    public int getIndex(){
        return index;
    }

}
