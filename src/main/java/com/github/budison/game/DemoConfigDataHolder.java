package com.github.budison.game;

import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.WinningType;
import com.github.budison.board.BoardDimension;

/**
 * @author Kevin Nowak
 */
public record DemoConfigDataHolder(
        PlayerX playerX,
        PlayerO playerO,
        BoardDimension boardDimension,
        int winningCondition,
        Player startingPlayer,
        Player winningPlayer,
        WinningType winningType,
        int winningLine
) {}
