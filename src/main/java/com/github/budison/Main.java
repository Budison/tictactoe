package com.github.budison;

import com.github.budison.demo.Demo;
import com.github.budison.game.Game;

/**
 * @author Kevin Nowak
 */
class Main {

    public static void main(String[] args) {
        switch (selectRunMode(args)) {
            case DEMO_NO_INPUT -> {
                Demo demo = new Demo();
                demo.run();
            }
            case DEMO_WITH_INPUT -> {
                ArgsReader argsReader = new ArgsReader(args);
                Demo demo = new Demo(argsReader);
                demo.run();
            }
            default -> {
                Game game = new Game();
                game.run();
            }
        }
    }

    static RunMode selectRunMode(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("demo")) {
                return RunMode.DEMO_NO_INPUT;
            }
            else {
                return RunMode.DEMO_WITH_INPUT;
            }
        }
        return RunMode.NORMAL;
    }
}	
