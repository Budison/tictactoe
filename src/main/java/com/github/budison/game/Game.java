package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.GameBoard;

/**
 * @author Kevin Nowak
 */
public class Game {
    public GameConfigDataHolder gameConfigDataHolder;
    LanguageType languageType;
    GameState gameState;
    GameBoard gameBoard;

    public Game(LanguageType languageType) {
        this.languageType = languageType;
        this.gameState = GameState.CONFIGURATION;
    }

    public Game() {
        this.gameState = GameState.CONFIGURATION;
        this.languageType = LanguageType.EN;
    }

    public void startConfiguration() {
        this.gameState = GameState.CONFIGURATION;
        this.gameConfigDataHolder = new GameConfigurator().configureGame();
        this.languageType = this.gameConfigDataHolder.languageType();
        this.gameBoard = new GameBoard(this.gameConfigDataHolder.boardDimension());
    }

    // Getters (only) used for testing purposes

    public LanguageType getLanguageType() { return languageType; }
}
