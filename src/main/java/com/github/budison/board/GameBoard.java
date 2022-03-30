package com.github.budison.board;


import com.github.budison.Player;
import com.github.budison.PlayerO;

/**
 * @author Kevin Nowak
 */
public class GameBoard {
    BoardMap boardMap;
    BoardVisualization boardVisualization;

    public GameBoard(BoardDimension boardDimension) {
        this.boardMap = new BoardMap(boardDimension);
        this.boardVisualization = new BoardVisualization(this.boardMap, boardDimension);
    }
}
