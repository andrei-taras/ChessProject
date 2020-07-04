package com.solarwindsmsp.chess;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void move(MovementType movementType, int newX, int newY) {
    	if (!isLegalMove(newX, newY)) {
    		return;
    	}
    	setXCoordinate(newX);
    	setYCoordinate(newY);
    }

    private boolean isLegalMove(int newX, int newY) {
    	// Check that we're not moving outside the board
    	if (!chessBoard.isLegalBoardPosition(newX, newY)) {
    		return false;
    	}
    	if (pieceColor == PieceColor.WHITE) {
	    	return (xCoordinate +1 == newX && yCoordinate == newY) || (xCoordinate +1 == newX && yCoordinate +1 == newY) || (xCoordinate +1 == newX && yCoordinate -1 == newY);
    	} else {
    		return (xCoordinate -1 == newX && yCoordinate == newY) || (xCoordinate -1 == newX && yCoordinate +1 == newY) || (xCoordinate -1 == newX && yCoordinate -1 == newY);
    	}
    }
    
    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
