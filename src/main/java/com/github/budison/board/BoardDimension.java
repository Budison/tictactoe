package com.github.budison.board;

/**
 * @author Kevin Nowak
 */
public class BoardDimension {
    int boardDimension;
    public BoardDimension(String boardDimensionString) {
        this.boardDimension = Integer.parseInt(boardDimensionString);
    }
}
