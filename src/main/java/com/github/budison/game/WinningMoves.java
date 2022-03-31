package com.github.budison.game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Nowak
 */
class WinningMoves {
    DemoConfigDataHolder demoConfigDataHolder;
    List<Integer> winnerMoves;
    List<Integer> loserMoves;

    public WinningMoves(DemoConfigDataHolder demoConfigDataHolder) {
        this.demoConfigDataHolder = demoConfigDataHolder;
    }

    void calculateHorizontalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int start = 1;
        for (int i = 1; i < this.demoConfigDataHolder.winningLine(); i++) {
            start += this.demoConfigDataHolder.boardDimension().value();
        }
        for(int i = 0; i < this.demoConfigDataHolder.winningCondition(); i++) {
            ret.add(start++);
        }
        this.winnerMoves = ret;
    }

    void calculateHorizontalMovesLoser(List<Integer> winnerMoves) {
        List<Integer> ret = new ArrayList<>();
        int size = this.demoConfigDataHolder.boardDimension().value() * this.demoConfigDataHolder.boardDimension().value();
        for(int i = 1; i <= size; i++) {
            ret.add(i);
        }
        for(Integer i : winnerMoves) {
            if(ret.contains(i)) {
                ret.remove(i);
            }
        }
        this.loserMoves = ret;
    }

    void calculateVerticalMovesWinner() {
        List<Integer> ret = new ArrayList<>();
        int dimension = this.demoConfigDataHolder.boardDimension().value();
        int condition = this.demoConfigDataHolder.winningCondition();
        int winningLine = this.demoConfigDataHolder.winningLine();
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
        this.winnerMoves = ret;
    }
}
