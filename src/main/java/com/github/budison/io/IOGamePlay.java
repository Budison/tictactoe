package com.github.budison.io;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class IOGamePlay {
    private int boardSize;
    final Scanner scanner;

    public IOGamePlay(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getPlayerMove(Player player, Map<Integer, String> boardMap) {
        this.boardSize = boardMap.size();
        StringBuilder prompt = new StringBuilder();
        if(IOController.languageType == LanguageType.DE) {
            if(player instanceof PlayerX) {
                prompt.append(((PlayerX) player).name()).append(" (").append(((PlayerX) player).sign()).append(")").append(" ist dran: ");
            } else {
                prompt.append(((PlayerO) player).name()).append(" (").append(((PlayerO) player).sign()).append(")").append(" ist dran: ");
            }
        } else {
            if(player instanceof PlayerX) {
                prompt.append(((PlayerX) player).name()).append("'s").append(" (").append(((PlayerX) player).sign()).append(")").append(" turn: ");
            } else {
                prompt.append(((PlayerO) player).name()).append("'s").append(" (").append(((PlayerO) player).sign()).append(")").append(" turn: ");
            }
        }
        System.out.print(prompt);
        return getPlayerMoveScanner(boardMap);
    }

    int getPlayerMoveScanner(Map<Integer, String> boardMap) {
        while (true) {
            String cellString = this.scanner.next();
            try {
                int cellInt = Integer.parseInt(cellString);
                if(1 <= cellInt && cellInt <= this.boardSize && boardMap.get(cellInt).equalsIgnoreCase("EMPTY")) {
                    return cellInt;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid move!");
            }
        }
    }

    public String getWinningMessage(Player starter) {
        StringBuilder message = new StringBuilder();
        if(IOController.languageType == LanguageType.DE) {
            if (starter instanceof PlayerX) {
                message.append(((PlayerX) starter).name()).append(" hat gewonnen!");
            } else {
                message.append(((PlayerO) starter).name()).append(" hat gewonnen!");
            }
        } else {
            if (starter instanceof PlayerX) {
                message.append(((PlayerX) starter).name()).append(" has won!");
            } else {
                message.append(((PlayerO) starter).name()).append(" has won!");
            }
        }
        return message.toString();
    }

    public String getDrawMessage() {
        StringBuilder message = new StringBuilder();
        if(IOController.languageType == LanguageType.DE) {
            message.append("Runde endet unentschieden.");
        } else {
            message.append("Round ends with a draw.");
        }
        return message.toString();
    }

    public boolean playMore() {
        StringBuilder prompt = new StringBuilder();
        if(IOController.languageType == LanguageType.DE) {
            prompt.append("Möchtest du nochmal spielen (j/n)? ");
        } else {
            prompt.append("Do you want to play again (y/n)? ");
        }
        System.out.println(prompt);
        return playMoreScanner();
    }

    private boolean playMoreScanner() {
        while (true) {
            String answer = scanner.next();
            try {
                if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("j")) {
                    return true;
                }
                else if(answer.equalsIgnoreCase("n")) {
                    return false;
                }
                else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input!");
            }
        }
    }
}

