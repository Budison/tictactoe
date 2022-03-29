package com.github.budison.game;

import com.github.budison.LanguageType;
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
    }

    public Game() {}

    public void configureGame() {
        this.gameConfigDataHolder = new GameConfigurator().configureGame();
        this.languageType = this.gameConfigDataHolder.languageType();
    }

    // Getters (only) used for testing purposes

    public LanguageType getLanguageType() {
        return languageType;
    }
}
