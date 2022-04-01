package com.github.budison.game;

import com.github.budison.io.IOController;

import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
class GameConfigurator {
    GameConfigDataHolder gameConfigDataHolder;
    IOController ioController;

    GameConfigDataHolder configureGame(Scanner scanner) {
        this.ioController = new IOController(scanner);
        return this.gameConfigDataHolder = new GameConfigDataHolder(
          this.ioController.readLanguageType(),
          this.ioController.readPlayerX() ,
          this.ioController.readPlayerO(),
          this.ioController.readStartingPlayer(),
          this.ioController.readBoardDimension(),
          this.ioController.readWinningCondition()
        );
    }
}
