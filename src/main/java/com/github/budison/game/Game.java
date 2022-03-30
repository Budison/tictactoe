package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.board.GameBoard;
import com.github.budison.io.IOController;

/**
 * @author Kevin Nowak
 */
public class Game {
    public GameConfigDataHolder gameConfigDataHolder;
    LanguageType languageType;
    GameState gameState;
    GameBoard gameBoard;
    IOController ioController;

    public Game(LanguageType languageType) {
        this.languageType = languageType;
        this.gameState = GameState.CONFIGURATION;
        this.ioController = new IOController();
    }

    public Game() {
        this.gameState = GameState.CONFIGURATION;
        this.languageType = LanguageType.EN;
        this.ioController = new IOController();
    }

    public void startConfiguration() {
        this.gameState = GameState.CONFIGURATION;
        this.gameConfigDataHolder = new GameConfigurator().configureGame();
        this.languageType = this.gameConfigDataHolder.languageType();
        this.gameBoard = new GameBoard(this.gameConfigDataHolder.boardDimension());
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    public void makeMove(Player player, int index) {
        this.gameBoard.makeMove(player, index);
    }

    @Override
    public String toString() {
        return this.gameBoard.toString();
    }

    // Getters (only) used for testing purposes

    public LanguageType getLanguageType() { return languageType; }
}
