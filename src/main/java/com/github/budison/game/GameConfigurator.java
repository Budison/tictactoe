package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;
import com.github.budison.io.ioController;

/**
 * @author Kevin Nowak
 */
class GameConfigurator {
    GameConfigDataHolder gameConfigDataHolder;
    ioController ioController;


    GameConfigDataHolder configureGame() {
        this.ioController = new ioController();
        return this.gameConfigDataHolder = new GameConfigDataHolder(
          readLanguageType(),
          readPlayerX(),
          readPlayerO(),
          readStartingPlayer(),
          readBoardDimension(),
          readWinningCondition()
        );
    }

    private int readWinningCondition() {
        return this.ioController.readWinningCondition();
    }

    private BoardDimension readBoardDimension() {
        // TODO
        return null;
    }

    private Player readStartingPlayer() {
        // TODO
        return null;
    }

    private PlayerO readPlayerO() {
        // TODO
        return null;
    }

    private PlayerX readPlayerX() {
        // TODO
        return null;
    }

    private LanguageType readLanguageType() {
        // TODO
        return this.ioController.readLanguageType();
    }
}
