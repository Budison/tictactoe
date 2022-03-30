package com.github.budison.board;

import com.github.budison.Player;
import com.github.budison.PlayerX;

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

    public GameBoard makeMove(Player player, int index) {
        if(player instanceof PlayerX) {
            this.boardVisualization = this.boardVisualization.updateBoard((this.boardMap.changeCellValue(CellType.X, index)));
        }
        else {
            this.boardVisualization = this.boardVisualization.updateBoard((this.boardMap.changeCellValue(CellType.O, index)));
        }
        return this;
    }

    @Override
    public String toString() {
        return this.boardVisualization.toString();
    }
}
