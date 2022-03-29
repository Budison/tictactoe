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
        return inputInt == 2 ? LanguageType.DE : LanguageType.EN;
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
        Scanner in = new Scanner(System.in);
        return new PlayerX(in.next(), 'X');
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
        Scanner in = new Scanner(System.in);
        return new PlayerO(in.next(), 'O');
    }

    int getWinningCondition() {
        return 0;
    }


    Player getStartingPlayer() {
        return null;
    }

    BoardDimension getBaordDimension() {
        return null;
    }
}
