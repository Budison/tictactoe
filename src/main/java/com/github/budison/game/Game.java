package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.board.GameBoard;
import com.github.budison.referee.Referee;
import com.github.budison.game.Game;

/**
 * @author Kevin Nowak
 */
public class Game {
    public GameConfigDataHolder gameConfigDataHolder;
    LanguageType languageType;
    GameState gameState;
    GameBoard gameBoard;
    Referee referee;

    public Game(LanguageType languageType) {
        this.languageType = languageType;
        this.referee = new Referee();
        this.gameState = GameState.CONFIGURATION;
    }

    public Game() {
        this.referee = new Referee();
        this.gameState = GameState.CONFIGURATION;
        this.languageType = LanguageType.EN;
    }

    public void startConfiguration() {
        this.gameState = GameState.CONFIGURATION;
        this.gameConfigDataHolder = new GameConfigurator().configureGame();
        this.languageType = this.gameConfigDataHolder.languageType();
        System.out.println(this.gameConfigDataHolder);
    }

    // Getters (only) used for testing purposes

    public LanguageType getLanguageType() { return languageType; }
}
