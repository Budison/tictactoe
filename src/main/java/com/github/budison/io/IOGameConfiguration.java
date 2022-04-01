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
    private PlayerX playerX;
    private PlayerO playerO;
    int boardDimension;

    //--------- Read LanguageType form user ---------//

    LanguageType getLanguageType() {
        String prompt = """
                [1] English
                [2] Deutsch
                Please choose a language / Bitte wähle eine Sprache:
                """;
        System.out.print(prompt);
        return getLanguageTypeScanner() == 2 ? LanguageType.DE : LanguageType.EN;
    }

    private int getLanguageTypeScanner() {
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

    //--------- Read player names form user ---------//

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
        return this.playerX = new PlayerX(new Scanner(System.in).next(), 'X');
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
        return this.playerO = new PlayerO(new Scanner(System.in).next(), 'O');
    }

    //--------- Read startingPlayer form user ---------//

    Player getStartingPlayer() {
        String prompt;
        if(IOController.languageType == LanguageType.DE) {
            prompt = """
                    Welcher Spieler beginnt - X oder O?
                    """;
        } else {
            prompt = """
                    Which player is starting - X or O?
                    """;
        }
        System.out.print(prompt);
        return getPlayerScanner();
    }

    private Player getPlayerScanner() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String playerString = in.next();
            try {
                return switch (playerString.toLowerCase()) {
                    case "x" -> this.playerX;
                    case "o" -> this.playerO;
                    default -> throw new IllegalArgumentException();
                };
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input! Should be X or O... Try again.");
            }
        }
    }

    //--------- Read value form user ---------//

    BoardDimension getBoardDimension() {
        String prompt;
        if(IOController.languageType == LanguageType.DE) {
            prompt = """
                    Gib die gewünschte Feldgröße an (z. B. 3 für ein 3x3 Feld):
                    """;
        } else {
            prompt = """
                    Enter the boards dimension you wish to play with (e.g. enter 3 for a 3x3 board):
                    """;
        }
        System.out.print(prompt);
        return getBoardDimensionScanner();
    }

    private BoardDimension getBoardDimensionScanner() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String boardDimensionIn = in.next();
            try {
                int boardDimension = Integer.parseInt(boardDimensionIn);
                if(3 <= boardDimension && boardDimension <= 31) {
                    this.boardDimension = boardDimension;
                    return new BoardDimension(boardDimension);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input! Boards dimension must be min. 3x3 and max. 31x31... Try again.");
            }
        }
    }

    //--------- Read winningCondition form user ---------//

    int getWinningCondition() {
        String prompt;
        if(IOController.languageType == LanguageType.DE) {
            prompt = """
                    Gib die gewünschte Gewinnbedingung ein (min. 3 und max. Feldgröße):
                    """;
        } else {
            prompt = """
                    Enter the winning condition (min. 3 and max. board dimension):
                    """;
        }
        System.out.print(prompt);
        return getWinningConditionScanner();
    }

    private int getWinningConditionScanner() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String winningConditionIn = in.next();
            try {
                int winningCondition = Integer.parseInt(winningConditionIn);
                if(3 <= winningCondition && winningCondition <= this.boardDimension) {
                    return winningCondition;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.err.println("Invalid input! Winning condition must be min. 3 and max. board dimension... Try again.");
            }
        }
    }
}
