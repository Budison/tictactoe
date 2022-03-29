package com.github.budison.board;

import java.util.Objects;

/**
 * @author Kevin Nowak
 */
public class BoardDimension {
    int boardDimension;
    public BoardDimension(String boardDimensionString) {
        try {
            this.boardDimension = Integer.parseInt(boardDimensionString);
        } catch (NumberFormatException numberFormatException) {
            System.err.println("BoardDimension: Provided String could not be formatted to int.");
        }

    }
    public BoardDimension(int boardDimension) { this.boardDimension = boardDimension; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDimension that = (BoardDimension) o;
        return boardDimension == that.boardDimension;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardDimension);
    }
}
