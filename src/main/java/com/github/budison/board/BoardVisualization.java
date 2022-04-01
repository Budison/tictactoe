package com.github.budison.board;

/**
 * @author Kevin Nowak
 */
class BoardVisualization {
    BoardMap boardMap;
    String boardVisualization;
    final int boardSize;
    final BoardDimension boardDimension;
    BoardVisualization(BoardMap boardMap, BoardDimension boardDimension) {
        this.boardMap = boardMap;
        this.boardDimension = boardDimension;
        this.boardSize = boardDimension.value() * boardDimension.value();
        this.boardVisualization = createBoardVisualization(boardMap);
    }

    private String createBoardVisualization(BoardMap boardMap) {
        StringBuilder stringBuilder = new StringBuilder(makeDivider());

        for (int i = 0; i < boardSize; i++) {
            if(i % boardDimension.value() == 0 && i != 0) {
                stringBuilder.append("|").append(System.lineSeparator());
                stringBuilder.append(makeDivider());
            }
            stringBuilder.append("|").append(boardMap.boardMap.get(i + 1));
        }
        stringBuilder.append("|").append(System.lineSeparator()).append(makeDivider());
        return stringBuilder.toString();
    }
    
    BoardVisualization updateBoard(BoardMap boardMap) {
        this.boardVisualization = this.createBoardVisualization(boardMap);
        this.boardMap = boardMap;
        return this;
    }

    private String makeDivider() {
        return "+" + "-----+".repeat(Math.max(0, boardDimension.value())) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return this.boardVisualization;
    }
}
