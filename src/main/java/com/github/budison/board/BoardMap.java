package com.github.budison.board;

import com.github.budison.Player;
import com.github.budison.PlayerO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kevin Nowak
 */
class BoardMap {
    private final int boardSize;
    private final BoardDimension boardDimension;
    Map<Integer, Cell> boardMap;


    BoardMap(BoardDimension boardDimension) {
        this.boardDimension = boardDimension;
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

    BoardMap changeCellValue(CellType cellType, int index) {
        boardMap.computeIfPresent(index, (integer, cell) -> new Cell(cellType, index));
        return this;
    }

    public Map<Integer, String> getCopy() {
        Map<Integer, String> ret = new TreeMap<>();
        for(Map.Entry<Integer, Cell> entry : boardMap.entrySet()) {
            ret.put(entry.getKey(), entry.getValue().cellType().toString());
        }
        return ret;
    }

    boolean check(Player starter, int winningCondition) {
        if(starter instanceof PlayerO) {
            return this.checkHorizontalO(winningCondition) || this.checkVerticalO(winningCondition) || this.checkDiagonalO(winningCondition) || this.checkAntiDiagonalO(winningCondition);
        } else {
            return this.checkHorizontalX(winningCondition) || this.checkVerticalX(winningCondition) || this.checkDiagonalX(winningCondition) || this.checkAntiDiagonalX(winningCondition);
        }
    }

    private boolean checkAntiDiagonalX(int winningCondition) {
        for (String s : getAllAntiDiagonals()) {
            if(s.contains("  X  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalX(int winningCondition) {
        for (String s : getAllDiagonals()) {
            if(s.contains("  X  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalX(int winningCondition) {
        for(String s : getAllColumns()) {
            if (s.contains("  X  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalX(int winningCondition) {
        for (String s : getAllRows()) {
            if (s.contains("  X  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAntiDiagonalO(int winningCondition) {
        for (String s : getAllAntiDiagonals()) {
            if(s.contains("  O  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalO(int winningCondition) {
        for (String s : getAllDiagonals()) {
            if(s.contains("  O  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalO(int winningCondition) {
        for(String s : getAllColumns()) {
            if (s.contains("  O  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalO(int winningCondition) {
        for (String s : getAllRows()) {
            if (s.contains("  O  ".repeat(winningCondition))) {
                return true;
            }
        }
        return false;

    }

    private List<String> getAllColumns() {
        List<Cell> allEntries = new ArrayList<>();
        for (Map.Entry<Integer, Cell> entry : boardMap.entrySet()) {
            allEntries.add(entry.getValue());
        }
        List<String> allColumns = new ArrayList<>();
        for (int i = 0; i < boardDimension.value(); i++) {
            int j = i;
            StringBuilder columnToOneString = new StringBuilder();
            while(j < boardSize) {
                columnToOneString.append(allEntries.get(j).toString());
                j += boardDimension.value();
            }
            allColumns.add(columnToOneString.toString());
        }
        return allColumns;
    }

    private List<String> getAllRows() {
        List<Cell> allEntries = new ArrayList<>();
        for (Map.Entry<Integer, Cell> entry : boardMap.entrySet()) {
            allEntries.add(entry.getValue());
        }
        List<String> allRows = new ArrayList<>();
        int i = 0;
        while (i < allEntries.size()) {
            List<Cell> row = allEntries.subList(i, i + boardDimension.value());
            StringBuilder rowToOneString = new StringBuilder();
            for(Cell c : row) {
                rowToOneString.append(c.toString());
            }
            allRows.add(rowToOneString.toString());
            i += boardDimension.value();
        }
        return allRows;
    }

    private List<String> getAllDiagonals() {
        List<Cell> allEntries = new ArrayList<>();
        for (Map.Entry<Integer, Cell> entry : boardMap.entrySet()) {
            allEntries.add(entry.getValue());
        }
        List<String> allDiagonals = new ArrayList<>();
        for (int i = boardDimension.value(); i > 0; i--) {
            StringBuilder diagonalToOneString = new StringBuilder();
            int range = i - 1;
            int jumper = i - 1;
            while (range >= 0) {
                diagonalToOneString.append(allEntries.get(jumper).toString());
                jumper += boardDimension.value() - 1;
                range--;
            }
            allDiagonals.add(diagonalToOneString.toString());
        }
        int help = boardDimension.value() - 2;
        for (int i = boardDimension.value() * 2; i <= boardSize; i += boardDimension.value()) {
            StringBuilder diagonalToOneString = new StringBuilder();
            int range = help;
            int jumper = i - 1;
            while (range >= 0) {
                diagonalToOneString.append(allEntries.get(jumper).toString());
                jumper += boardDimension.value() - 1;
                range--;
            }
            allDiagonals.add(diagonalToOneString.toString());
            help--;
        }
        return allDiagonals;
    }

    private List<String> getAllAntiDiagonals() {
        List<Cell> allEntries = new ArrayList<>();
        for (Map.Entry<Integer, Cell> entry : boardMap.entrySet()) {
            allEntries.add(entry.getValue());
        }
        List<String> allDiagonals = new ArrayList<>();
        for (int i = 1; i <= boardDimension.value(); i++) {
            StringBuilder diagonalToOneString = new StringBuilder();
            int range = boardDimension.value() - i;
            int jumper = i - 1;
            while (range >= 0) {
                diagonalToOneString.append(allEntries.get(jumper).toString());
                jumper += boardDimension.value() + 1;
                range--;
            }
            allDiagonals.add(diagonalToOneString.toString());
        }
        int help = boardDimension.value() - 2;
        for (int i = boardDimension.value() + 1; i <= boardSize; i += boardDimension.value()) {
            StringBuilder diagonalToOneString = new StringBuilder();
            int range = help;
            int jumper =  i - 1;
            while (range >= 0) {
                diagonalToOneString.append(allEntries.get(jumper).toString());
                jumper += boardDimension.value() + 1;
                range--;
            }
            allDiagonals.add(diagonalToOneString.toString());
            help--;
        }
        return allDiagonals;
    }

    BoardMap reset() {
        this.boardMap = this.createNewBoardMap();
        return this;
    }
}
