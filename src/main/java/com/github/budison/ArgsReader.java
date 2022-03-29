package com.github.budison;

import com.github.budison.board.BoardDimension;

import java.util.Locale;

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
        this.boardDimension = new BoardDimension(args[0]);
        this.winningCondition = Integer.parseInt(args[1]);
        this.startingPlayer = createPlayerByString(args[2]);
        this.winningPlayer = createPlayerByString(args[3]);
        this.winningType = createWinningTypeByString(args[4]);
        this.winningLine = Integer.parseInt(args[5]);
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

    BoardDimension getBoardDimension() { return boardDimension; }

    int getWinningCondition() { return winningCondition; }

    Player getStartingPlayer() { return startingPlayer; }

    Player getWinningPlayer() { return winningPlayer; }

    WinningType getWinningType() { return winningType; }

    int getWinningLine() { return winningLine; }
}
