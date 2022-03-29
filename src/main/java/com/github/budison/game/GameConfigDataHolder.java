package com.github.budison.game;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;

/**
 * @author Kevin Nowak
 */
record GameConfigDataHolder(
        LanguageType languageType,
        PlayerX playerX,
        PlayerO playerO,
        Player startingPlayer,
        BoardDimension boardDimension,
        int winningCondition
) {}
