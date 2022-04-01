package com.github.budison.game;

import com.github.budison.*;
import com.github.budison.board.GameBoard;
import com.github.budison.io.IOController;

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
    DemoMoves demoMoves;

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
        this.demoMoves = new DemoMoves(this.demoConfigDataHolder);
        this.gameState = GameState.CONFIGURATION;
        this.gameBoard = new GameBoard(this.demoConfigDataHolder.boardDimension());
        this.ioController = new IOController();
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    public void startPlaying() {
        this.playRound();
        System.out.println(this.gameBoard.toString());
        this.gameState = GameState.GAME;
        System.out.println(this.ioController.getStateFinishedMessage(this.gameState.toString()));
    }

    private void playRound() {
        switch (this.demoConfigDataHolder.winningType()) {
            case HORIZONTAL -> {
                this.demoMoves.winnerMoves = this.demoMoves.calculateHorizontalMovesWinner();
                this.demoMoves.loserMoves = this.demoMoves.calculateMovesLoser(this.demoMoves.winnerMoves);
            }
            case VERTICAL -> {
                this.demoMoves.winnerMoves = this.demoMoves.calculateVerticalMovesWinner();
                this.demoMoves.loserMoves = this.demoMoves.calculateMovesLoser(this.demoMoves.winnerMoves);
            }
            case DIAGONAL -> {
                this.demoMoves.winnerMoves = this.demoMoves.calculateDiagonalMovesWinner();
                this.demoMoves.loserMoves = this.demoMoves.calculateMovesLoser(this.demoMoves.winnerMoves);
            }
            case ANTI_DIAGONAL -> {
                this.demoMoves.winnerMoves = this.demoMoves.calculateAntiDiagonalMovesWinner();
                this.demoMoves.loserMoves = this.demoMoves.calculateMovesLoser(this.demoMoves.winnerMoves);
            }
        }



        for(Integer i : this.demoMoves.winnerMoves) {
            this.gameBoard.makeMove(demoConfigDataHolder.winningPlayer(), i);
        }
        for(int i = 0; i < demoConfigDataHolder.winningCondition(); i++) {
            Random rand = new Random();
            int randomElement = demoMoves.loserMoves.get(rand.nextInt(1, demoMoves.loserMoves.size()));
            if(demoConfigDataHolder.winningPlayer() instanceof PlayerO) {
                this.gameBoard.makeMove(demoConfigDataHolder.playerX(), randomElement);
            } else {
                this.gameBoard.makeMove(demoConfigDataHolder.playerO(), randomElement);
            }

        }
    }
}
