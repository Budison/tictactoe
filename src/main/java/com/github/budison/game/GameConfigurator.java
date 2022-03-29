package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;
import com.github.budison.io.IOController;

/**
 * @author Kevin Nowak
 */
class GameConfigurator {
    GameConfigDataHolder gameConfigDataHolder;
    IOController ioController;

    GameConfigDataHolder configureGame() {
        this.ioController = new IOController();
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
