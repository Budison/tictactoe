package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerX;
import com.github.budison.board.GameBoard;
import com.github.budison.io.IOController;

import java.util.Map;

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
    private Scoreboard scoreboard;

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
        this.scoreboard = new Scoreboard(this.gameConfigDataHolder.playerO(), this.gameConfigDataHolder.playerX());
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    public void startPlaying() {
        roundCount = 1;
        while(this.roundCount <= 3) {
            this.gameState = GameState.values()[roundCount];
            System.out.println(this.ioController.getStateStartedMessage(this.gameState.toString()));
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
            // Print current board
            System.out.println(this.gameBoard.toString());

            // Make move on the board, assign returning board to this.gameBoard
            this.gameBoard = this.makeMove(starter, ioController.readPlayerMove(starter, this.getCopyOfBoardMap()));

            // Check if starter has a win
            if(this.gameBoard.checkWinningCondition(starter, this.gameConfigDataHolder.winningCondition())) {
                System.out.println(System.lineSeparator() + ioController.printWinningMessage(starter));
                this.scoreboard = this.scoreboard.addWin(starter);
                break;
            }

            // Check for draw
            if(turnCount == this.boardSize) {
                System.out.println(System.lineSeparator() + this.ioController.printDrawMessage());
                this.scoreboard = this.scoreboard.addDraw();
            }

            // Increase turnCount
            turnCount++;

            // Change turn
            starter = this.changeTurn(starter);
        }
        // Print final board
        System.out.println(this.gameBoard.toString());

        // Print scoreboard
        System.out.println(this.scoreboard);

        // reset the board
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
}
