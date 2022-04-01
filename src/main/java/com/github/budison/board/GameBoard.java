package com.github.budison.board;

import com.github.budison.Player;
import com.github.budison.PlayerX;
import com.github.budison.io.IOController;

import java.util.Map;

/**
 * @author Kevin Nowak
 */
public class GameBoard {
    BoardMap boardMap;
    BoardVisualization boardVisualization;
    IOController ioController;

    public GameBoard(BoardDimension boardDimension) {
        this.boardMap = new BoardMap(boardDimension);
        this.boardVisualization = new BoardVisualization(this.boardMap, boardDimension);
        //this.ioController = new IOController();
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

    public Map<Integer, String> getCopyOfBoardMap() {
        return this.boardMap.getCopy();
    }

    public boolean checkWinningCondition(Player starter, int winningCondition) {
        return this.boardMap.check(starter, winningCondition);
    }

    public GameBoard reset() {
        this.boardMap = this.boardMap.reset();
        this.boardVisualization = this.boardVisualization.updateBoard(this.boardMap);
        return this;
    }
}
