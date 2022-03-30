package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerX;
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
    private int roundCount = 1;
    private int boardSize;

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
        this.boardSize = this.gameConfigDataHolder.boardDimension().value() * this.gameConfigDataHolder.boardDimension().value();
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    public void startPlaying() {
        while(this.roundCount <= 3) {
            this.gameState = GameState.values()[roundCount];
            this.playRound();
            roundCount++;
            System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
        }
        this.gameState = GameState.GAME;
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    private void playRound() {
        Player starter = getRoundStarter();
        int turnCount = 1;
        while(turnCount <= this.boardSize) {
            System.out.println(this.gameBoard.toString());
            this.makeMove(starter, ioController.readPlayerMove(starter, this.boardSize));
        }



    }

    private Player getRoundStarter() {
        if(this.gameConfigDataHolder.startingPlayer() instanceof PlayerX) {
            return this.roundCount == 2 ? this.gameConfigDataHolder.playerO() : this.gameConfigDataHolder.playerX();
        } else {
            return this.roundCount == 2 ? this.gameConfigDataHolder.playerX() : this.gameConfigDataHolder.playerO();
        }
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

    public boolean playMore() {
        return false;
    }
}
