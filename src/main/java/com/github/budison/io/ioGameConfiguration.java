package com.github.budison.io;

import com.github.budison.LanguageType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class ioGameConfiguration {

    public static int winningCondition() {
        // TODO
        return 0;
    }

    public static LanguageType readLanguageType() {
        String prompt = """
                [1] English
                [2] Deutsch
                Please choose your language / Wähle eine Sprache: 
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
        switch (inputInt) {
            case 2: return LanguageType.DE;
            default: return LanguageType.EN;
        }
    }
}
