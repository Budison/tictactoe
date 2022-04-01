package com.github.budison;

import com.github.budison.board.BoardDimension;

/**
 * @author Kevin Nowak
 * Class for reading input form the String[] args
 */
public class ArgsReader {
    BoardDimension boardDimension;
    int winningCondition;
    Player startingPlayer;
    Player winningPlayer;
    WinningType winningType;
    int winningLine;

    ArgsReader(String[] args) {
        if(args.length != 6) {
            throw new IllegalArgumentException("Exactly 6 parameters are required!");
        }
        this.boardDimension = new BoardDimension(checkBd(createIntByString(args[0])));
        this.winningCondition = checkWc(Integer.parseInt(args[1]));
        this.startingPlayer = createPlayerByString(args[2]);
        this.winningPlayer = createPlayerByString(args[3]);
        this.winningType = createWinningTypeByString(args[4]);
        this.winningLine = checkWl(Integer.parseInt(args[5]));
    }

    private int checkBd(int i) {
        if( i >= 3 && i <= 31) return i;
        else {
            throw new IllegalArgumentException("boardDimension must be within 3 and 31!");
        }
    }

    private int checkWc(int i) {
        if( i >= 3 && i <= this.boardDimension.value()) return i;
        else {
            throw new IllegalArgumentException("winningCondition must be within board dimension!");
        }
    }

    private int createIntByString(String arg) {
        int ret = 3;
        try {
            ret = Integer.parseInt(arg);
        } catch (NumberFormatException numberFormatException) {
            System.err.println("BoardDimension: Provided String could not be formatted to int.");
            throw new NumberFormatException(); // throw for test needed
        }
        return ret;
    }

    private int checkWl(int i) {
        if(i >= 1 && i <= this.boardDimension.value()) return i;
        else {
            throw new IllegalArgumentException("winningLine must be within board dimension!");
        }
    }

    Player createPlayerByString(String player) {
        if(player.equalsIgnoreCase("O")) {
            return new PlayerO("PlayerO", 'O');
        }
        else if(player.equalsIgnoreCase("X")) {
            return new PlayerX("PlayerX", 'X');
        }
        else {
            throw new IllegalArgumentException("startingPlayer or winningPlayer argument is either X nor O!");
        }
    }

    WinningType createWinningTypeByString(String winningType) {
        return switch (winningType.toLowerCase()) {
            case "horizontal" -> WinningType.HORIZONTAL;
            case "vertical" -> WinningType.VERTICAL;
            case "diagonal" -> WinningType.DIAGONAL;
            case "antidiagonal", "anti diagonal", "anti-diagonal" -> WinningType.ANTI_DIAGONAL;
            default -> throw new IllegalArgumentException("winningType argument is not valid!");
        };
    }

    // Getters (only) used for testing purposes

    public BoardDimension getBoardDimension() { return boardDimension; }

    public int getWinningCondition() { return winningCondition; }

    public Player getStartingPlayer() { return startingPlayer; }

    public Player getWinningPlayer() { return winningPlayer; }

    public WinningType getWinningType() { return winningType; }

    public int getWinningLine() { return winningLine; }
}
