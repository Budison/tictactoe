package com.github.budison.io;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;
import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class IOGameConfiguration {

    LanguageType getLanguageType() {
        String prompt = """
                [1] English
                [2] Deutsch
                Please choose a language / Bitte wähle eine Sprache: 
                """;
        System.out.print(prompt);
        return getInputInt() == 2 ? LanguageType.DE : LanguageType.EN;
    }

    private int getInputInt() {
        Scanner in = new Scanner(System.in);
        int inputInt = 1;
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            String input = in.next();
            try {
                inputInt = Integer.parseInt(input);
                if(inputInt > 2 || inputInt < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input! Try again.");
                repeat = true;
            }
        }
        return inputInt;
    }

    PlayerX getPlayerX() {
        String prompt;
        if(IOController.languageType == LanguageType.DE) {
            prompt = """
                    Gib den Namen von Spieler X ein:
                    """;
        } else {
            prompt = """
                    Enter the name of player X:
                    """;
        }
        System.out.print(prompt);
        return new PlayerX(new Scanner(System.in).next(), 'X');
    }

    PlayerO getPlayerO() {
        String prompt;
        if(IOController.languageType == LanguageType.DE) {
            prompt = """
                    Gib den Namen von Spieler O ein:
                    """;
        } else {
            prompt = """
                    Enter the name of player O:
                    """;
        }
        System.out.print(prompt);
        return new PlayerO(new Scanner(System.in).next(), 'O');
    }

    int getWinningCondition() {
        // TODO
        return 0;
    }

    Player getStartingPlayer() {
        // TODO
        return null;
    }

    BoardDimension getBoardDimension() {
        // TODO
        return null;
    }
}
