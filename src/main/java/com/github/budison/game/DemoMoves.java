package com.github.budison.game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Nowak
 */
class DemoMoves {
    final DemoConfigDataHolder demoConfigDataHolder;
    final int dimension;
    final int condition;
    final int winningLine;
    List<Integer> winnerMoves;
    List<Integer> loserMoves;

    public DemoMoves(DemoConfigDataHolder demoConfigDataHolder) {
        this.demoConfigDataHolder = demoConfigDataHolder;
        this.dimension = this.demoConfigDataHolder.boardDimension().value();
        this.condition = this.demoConfigDataHolder.winningCondition();
        this.winningLine = this.demoConfigDataHolder.winningLine();
    }

    List<Integer> calculateHorizontalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int start = 1;
        for (int i = 1; i < winningLine; i++) {
            start += dimension;
        }
        for(int i = 0; i < condition; i++) {
            ret.add(start++);
        }
        return ret;
    }

    List<Integer> calculateMovesLoser(List<Integer> winnerMoves) {
        List<Integer> ret = new ArrayList<>();
        int size = dimension * dimension;
        for (int i = 1; i <= size; i++) {
            ret.add(i);
        }
        for (Integer i : winnerMoves) {
            ret.remove(i);
        }
        return ret;
    }

    List<Integer> calculateVerticalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int start = 1;
        for (int i = 1; i < winningLine; i++) {
            start += dimension;
        }
        while(start + (condition - 1) * dimension >= dimension * dimension) {
            start -= dimension;
        }
        for(int i = 0; i < condition; i++) {
            ret.add(start);
            start += dimension;
        }
        return ret;
    }

    List<Integer> calculateDiagonalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int start = 1;
        for (int i = 1; i < winningLine; i++) {
            start += dimension;
        }

        while (start - (condition * (dimension - 1)) < 0) {
            start += dimension;
        }

        for (int i = 0; i < condition; i++) {
            ret.add(start);
            start -= (dimension - 1);
        }
        return ret;
    }

    List<Integer> calculateAntiDiagonalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int start = 1;
        for (int i = 1; i < winningLine; i++) {
            start += dimension;
        }
        System.out.println("start: " + start);

        while (start + (condition * (dimension - 1)) > dimension * dimension) {
            start -= dimension;
        }

        for (int i = 0; i < condition; i++) {
            ret.add(start);
            start += (dimension + 1);
        }
        return ret;
    }
}
