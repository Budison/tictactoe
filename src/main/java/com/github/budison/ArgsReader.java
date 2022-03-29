package com.github.budison;

import com.github.budison.board.BoardDimension;
import com.github.budison.player.Player;
import com.github.budison.player.PlayerO;
import com.github.budison.player.PlayerX;

/**
 * @author Kevin Nowak
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
            return new PlayerO();
        }
        else if(player.equalsIgnoreCase("X")) {
            return new PlayerX();
        }
        else {
            throw new IllegalArgumentException("startingPlayer or winningPlayer argument is either X nor O!");
        }
    }

    WinningType createWinningTypeByString(String winningType) {
        if(winningType.equalsIgnoreCase("horizontal")) {
            return WinningType.HORIZONTAL;
        }
        else if(winningType.equalsIgnoreCase("vertical")) {
            return WinningType.VERTICAL;
        }
        else if(winningType.equalsIgnoreCase("diagonal")) {
            return WinningType.DIAGONAL;
        }
        else if(winningType.equalsIgnoreCase("antidiagonal") || winningType.equalsIgnoreCase("anti diagonal")) {
            return WinningType.ANTI_DIAGONAL;
        }
        else {
            throw new IllegalArgumentException("winningType argument is not valid!");
        }
    }

    // Getters (only) used for testing purposes

    BoardDimension getBoardDimension() {
        return boardDimension;
    }

    int getWinningCondition() {
        return winningCondition;
    }

    Player getStartingPlayer() {
        return startingPlayer;
    }

    Player getWinningPlayer() {
        return winningPlayer;
    }

    WinningType getWinningType() {
        return winningType;
    }

    int getWinningLine() {
        return winningLine;
    }
}
