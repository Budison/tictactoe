package com.github.budison.io;

import com.github.budison.LanguageType;
import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;
import com.github.budison.board.BoardDimension;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Kevin Nowak
 */
public class IOController {
    static LanguageType languageType = LanguageType.EN;
    private final IOGameConfiguration ioGameConfiguration;
    private final IOGamePlay ioGamePlay;

    public IOController(Scanner scanner) {
        this.ioGameConfiguration = new IOGameConfiguration(scanner);
        this.ioGamePlay = new IOGamePlay(scanner);
    }

    public String getStateStartedMessage(String gameState) {
        return languageType == LanguageType.EN ? System.lineSeparator() + gameState + " STARTED!" + System.lineSeparator() : System.lineSeparator().repeat(2);
    }

    public String getStateFinishedMessage(String gameState) {
        return languageType == LanguageType.EN ? System.lineSeparator() + gameState + " FINISHED!" + System.lineSeparator() : System.lineSeparator().repeat(2);
    }

    //--------- IO-Methods for GameConfigurator ---------//

    public LanguageType readLanguageType() { return languageType = ioGameConfiguration.getLanguageType(); }

    public PlayerX readPlayerX() { return this.ioGameConfiguration.getPlayerX(); }

    public int readWinningCondition() { return this.ioGameConfiguration.getWinningCondition(); }

    public PlayerO readPlayerO() { return this.ioGameConfiguration.getPlayerO(); }

    public Player readStartingPlayer() { return this.ioGameConfiguration.getStartingPlayer(); }

    public BoardDimension readBoardDimension() { return this.ioGameConfiguration.getBoardDimension(); }

    //--------- IO-Methods for GamePlay ---------//

    public int readPlayerMove(Player player, Map<Integer, String> boardMap) {
        return this.ioGamePlay.getPlayerMove(player, boardMap);
    }

    public String printWinningMessage(Player starter) {
        return this.ioGamePlay.getWinningMessage(starter);
    }

    public String printDrawMessage() {
        return this.ioGamePlay.getDrawMessage();
    }

    public boolean playMore() {
        return this.ioGamePlay.playMore();
    }
}
