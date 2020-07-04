package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;
    
    public static int MAX_PAWN_COUNT = 8;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
    	// Check that we're not exceeding the table bounds
    	if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
    		rejectPiece(pawn);
    		return;
    	}
    	// Check that we're not adding more pieces than legal
    	if (countPieces(pieceColor) >= MAX_PAWN_COUNT) {
    		rejectPiece(pawn);
    		return;
    	}
    	// Check that the desired position is free
    	if (pieces[xCoordinate][yCoordinate] != null) {
    		rejectPiece(pawn);
    		return;
    	}
    	
    	pawn.setXCoordinate(xCoordinate);
    	pawn.setYCoordinate(yCoordinate);
    	pawn.setChessBoard(this);
    	pieces[xCoordinate][yCoordinate] = pawn;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	if (!isLegalCoordinate(xCoordinate, MAX_BOARD_WIDTH) || !isLegalCoordinate(yCoordinate, MAX_BOARD_HEIGHT)) {
    		return false; //at least one coordinate was outside of table
    	}
    	
    	return true;
    }
    
    private boolean isLegalCoordinate(int coordinateToTest, int upperBound) {
    	return coordinateToTest >= 0 && coordinateToTest < upperBound;
    }

    /**
     * Counts the number of pieces of a given color, already on this table.
     */
    private int countPieces(PieceColor pieceColor) {
    	int count=0;
    	for (int x=0; x<MAX_BOARD_WIDTH; x++) {
    		for (int y=0; y<MAX_BOARD_HEIGHT; y++) {
    			if (pieces[x][y] != null && pieces[x][y].getPieceColor() == pieceColor) {
    				count++;
    			}
    		}
    	}
    	
    	return count;
    }
    
    /**
     * When a board operation fails, we indicate this by changing piece's (x, y) coordinates to (-1, -1)
     */
    private void rejectPiece(Pawn pawn) {
    	pawn.setXCoordinate(-1);
    	pawn.setYCoordinate(-1);
    }
}
