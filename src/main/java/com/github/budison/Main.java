package com.github.budison;

import com.github.budison.game.Demo;
import com.github.budison.game.Game;

import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        switch (selectRunMode(args)) {
            case DEMO_NO_ARGS -> {
                ArgsReader argsReader = new ArgsReader(new String[] {"4", "4", "X", "O", "horizontal", "1"});
                Demo demo = new Demo(argsReader, scanner);
                demo.startPlaying();
                return;
            }
            case DEMO_WITH_ARGS -> {
                ArgsReader argsReader = new ArgsReader(args);
                Demo demo = new Demo(argsReader, scanner);
                demo.startPlaying();
                return;
            }
            default -> {
                Game game = new Game(scanner);
                game.startConfiguration(scanner);
                do {
                    game.startPlaying();
                } while (game.playMore());
                System.out.println("Ciao!");
            }
        }
    }

    static RunMode selectRunMode(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("demo")) {
                return RunMode.DEMO_NO_ARGS;
            }
            else {
                return RunMode.DEMO_WITH_ARGS;
            }
        }
        return RunMode.NORMAL;
    }
}
