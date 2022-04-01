package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.GameBoard;
import com.github.budison.io.IOController;
import org.tinylog.Logger;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
public class Game {
    public GameConfigDataHolder gameConfigDataHolder;
    LanguageType languageType;
    GameState gameState;
    GameBoard gameBoard;
    public IOController ioController;
    private int roundCount = 1;
    private int boardSize;
    private Scoreboard scoreboard;

    public Game(LanguageType languageType) {
        this.languageType = languageType;
        this.gameState = GameState.CONFIGURATION;
        //this.ioController = new IOController(scanner);
    }

    public Game(Scanner scanner) {
        this.gameState = GameState.CONFIGURATION;
        this.languageType = LanguageType.EN;
        this.ioController = new IOController(scanner);
    }

    public void startConfiguration(Scanner scanner) {
        this.gameState = GameState.CONFIGURATION;
        this.gameConfigDataHolder = new GameConfigurator().configureGame(scanner);
        this.languageType = this.gameConfigDataHolder.languageType();
        this.gameBoard = new GameBoard(this.gameConfigDataHolder.boardDimension());
        this.boardSize = this.gameConfigDataHolder.boardDimension().value() * this.gameConfigDataHolder.boardDimension().value();
        this.scoreboard = new Scoreboard(this.gameConfigDataHolder.playerO(), this.gameConfigDataHolder.playerX());
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
        Logger.info("Configuration finished");
    }

    public void startPlaying() {
        roundCount = 1;
        while(this.roundCount <= 3) {
            Logger.info("Round " + roundCount + " started");
            this.gameState = GameState.values()[roundCount];
            System.out.println(this.ioController.getStateStartedMessage(this.gameState.toString()));
            this.playRound();
            roundCount++;
            System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
        }
        Logger.info("Game finished");
        Logger.info(getWinnerLog() + " has won");
        this.gameState = GameState.GAME;
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }



    private void playRound() {
        Player starter = getRoundStarter();
        int turnCount = 1;
        while(turnCount <= this.boardSize) {
            System.out.println(this.gameBoard.toString());
            this.gameBoard = this.makeMove(starter, ioController.readPlayerMove(starter, this.getCopyOfBoardMap()));
            if(this.gameBoard.checkWinningCondition(starter, this.gameConfigDataHolder.winningCondition())) {
                System.out.println(System.lineSeparator() + ioController.printWinningMessage(starter));
                this.scoreboard = this.scoreboard.addWin(starter);
                break;
            }
            if(turnCount == this.boardSize) {
                System.out.println(System.lineSeparator() + this.ioController.printDrawMessage());
                this.scoreboard = this.scoreboard.addDraw();
            }
            turnCount++;
            starter = this.changeTurn(starter);
        }
        System.out.println(this.gameBoard.toString());
        System.out.println(this.scoreboard);
        this.gameBoard = this.gameBoard.reset();
    }

    private Player changeTurn(Player starter) {
        return starter instanceof PlayerX ? this.gameConfigDataHolder.playerO() : this.gameConfigDataHolder.playerX();
    }

    private Player getRoundStarter() {
        if(this.gameConfigDataHolder.startingPlayer() instanceof PlayerX) {
            return this.roundCount == 2 ? this.gameConfigDataHolder.playerO() : this.gameConfigDataHolder.playerX();
        } else {
            return this.roundCount == 2 ? this.gameConfigDataHolder.playerX() : this.gameConfigDataHolder.playerO();
        }
    }

    public GameBoard makeMove(Player player, int index) {
        return this.gameBoard.makeMove(player, index);
    }

    @Override
    public String toString() {
        return this.gameBoard.toString();
    }

    // Getters (only) used for testing purposes

    private Map<Integer, String> getCopyOfBoardMap() {
        return this.gameBoard.getCopyOfBoardMap();
    }

    public LanguageType getLanguageType() { return languageType; }

    public boolean playMore() {
        return this.ioController.playMore();
    }

    private String getWinnerLog() {
        if(this.scoreboard.getWinnerPlayer() instanceof PlayerO) {
            return ((PlayerO) this.scoreboard.getWinnerPlayer()).name();
        } else {
            return ((PlayerX) this.scoreboard.getWinnerPlayer()).name();
        }
    }
}
