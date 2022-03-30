package com.github.budison.board;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kevin Nowak
 */
class BoardMap {
    private final int boardSize;
    Map<Integer, Cell> boardMap;

    BoardMap(BoardDimension boardDimension) {
        this.boardSize = boardDimension.value() * boardDimension.value();
        this.boardMap = createNewBoardMap();
    }

    private Map<Integer, Cell> createNewBoardMap() {
        Map<Integer, Cell> ret = new TreeMap<>();
        for (int i = 1; i <= boardSize; i++) {
            ret.put(i, new Cell(CellType.EMPTY, i));
        }
        return ret;
    }

    Map<Integer, Cell> changeCellValue(CellType cellType, int index) {
        boardMap.computeIfPresent(index, (integer, cell) -> new Cell(cellType, index));
        return boardMap;
    }
}
