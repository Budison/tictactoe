package com.github.budison.io;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.board.CellType;
import com.github.budison.board.GameBoard;

import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class IOGamePlay {
    private int boardSize;

    public int getPlayerMove(Player player, int boardSize) {
        this.boardSize = boardSize;
        StringBuilder prompt = new StringBuilder();
        if(IOController.languageType == LanguageType.DE) {
            prompt.append(player.getPlayerName()).append(" (" + player.getPlayerSign() + ")").append(" ist dran:");
        } else {
            prompt.append(player.getPlayerName() + "'s").append(" (" + player.getPlayerSign() + ")").append(" turn:");

        }
        System.out.print(prompt);
        return getPlayerMoveScanner();
    }

    int getPlayerMoveScanner() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String cellString = in.next();
            try {
                int cellInt = Integer.parseInt(cellString);
                if(1 <= cellInt && cellInt <= this.boardSize) {
                    return cellInt;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input! Your move must be within the board size!");
            }
        }
    }
}

