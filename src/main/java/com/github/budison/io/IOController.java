package com.github.budison.io;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;

/**
 * @author Kevin Nowak
 */
public class IOController {
    static LanguageType languageType = LanguageType.EN;
    private final IOGameConfiguration ioGameConfiguration;

    public IOController() {
        this.ioGameConfiguration = new IOGameConfiguration();
    }

    //--------- IO-Methods for GameConfigurator ---------//

    public LanguageType readLanguageType() { return languageType = ioGameConfiguration.getLanguageType(); }

    public PlayerX readPlayerX() { return this.ioGameConfiguration.getPlayerX(); }

    public int readWinningCondition() { return this.ioGameConfiguration.getWinningCondition(); }

    public PlayerO readPlayerO() { return this.ioGameConfiguration.getPlayerO(); }

    public Player readStartingPlayer() { return this.ioGameConfiguration.getStartingPlayer(); }

    public BoardDimension readBoardDimension() { return this.ioGameConfiguration.getBaordDimension(); }
}
