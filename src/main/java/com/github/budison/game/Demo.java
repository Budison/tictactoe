package com.github.budison.game;

import com.github.budison.*;
import com.github.budison.board.GameBoard;
import com.github.budison.io.IOController;

import java.util.List;
import java.util.Random;

/**
 * @author Kevin Nowak
 */
public class Demo {
    private final LanguageType languageType = LanguageType.EN;
    private final DemoConfigDataHolder demoConfigDataHolder;
    GameState gameState;
    GameBoard gameBoard;
    IOController ioController;
    private int roundCount = 1;
    private int boardSize;
    private Scoreboard scoreboard;
    WinningMoves winningMoves;

    public Demo(ArgsReader argsReader) {
        this.demoConfigDataHolder = new DemoConfigDataHolder(
                new PlayerX("PlayerX", 'X'),
                new PlayerO("PlayerO", 'O'),
                argsReader.getBoardDimension(),
                argsReader.getWinningCondition(),
                argsReader.getStartingPlayer(),
                argsReader.getWinningPlayer(),
                argsReader.getWinningType(),
                argsReader.getWinningLine()
        );
        this.winningMoves = new WinningMoves(this.demoConfigDataHolder);
        this.gameState = GameState.CONFIGURATION;
        this.gameBoard = new GameBoard(this.demoConfigDataHolder.boardDimension());
        this.ioController = new IOController();
        this.boardSize = this.demoConfigDataHolder.boardDimension().value() * this.demoConfigDataHolder.boardDimension().value();
        this.scoreboard = new Scoreboard(this.demoConfigDataHolder.playerO(), this.demoConfigDataHolder.playerX());
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    public void startPlaying() {
        this.playRound();
        System.out.println(this.gameBoard.toString());
        this.gameState = GameState.GAME;
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    private void playRound() {
        this.winningMoves.calculateHorizontalMovesWinner();
        this.winningMoves.calculateHorizontalMovesLoser(this.winningMoves.winnerMoves);

        for(Integer i : this.winningMoves.winnerMoves) {
            this.gameBoard.makeMove(demoConfigDataHolder.winningPlayer(), i);
        }
        for(int i = 0; i < demoConfigDataHolder.winningCondition(); i++) {
            Random rand = new Random();
            int randomElement = winningMoves.loserMoves.get(rand.nextInt(1, winningMoves.loserMoves.size()));
            if(demoConfigDataHolder.winningPlayer() instanceof PlayerO) {
                this.gameBoard.makeMove(demoConfigDataHolder.playerX(), randomElement);
            } else {
                this.gameBoard.makeMove(demoConfigDataHolder.playerO(), randomElement);
            }

        }
    }

    private Player changeTurn(Player starter) {
        return starter instanceof PlayerX ? this.demoConfigDataHolder.playerO() : this.demoConfigDataHolder.playerX();
    }
}
