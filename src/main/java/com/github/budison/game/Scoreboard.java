package com.github.budison.game;

import com.github.budison.Player;
import com.github.budison.PlayerO;
import com.github.budison.PlayerX;

/**
 * @author Kevin Nowak
 */
class Scoreboard {
    final PlayerX playerX;
    final PlayerO playerO;
    int scoreX;
    int scoreO;

    Scoreboard(PlayerO playerO, PlayerX playerX) {
        this.playerO = playerO;
        this.playerX = playerX;
        this.scoreO = 0;
        this.scoreX = 0;
    }


    Scoreboard addWin(Player starter) {
        if(starter instanceof PlayerX) {
            this.scoreX += 3;
        } else {
            this.scoreO += 3;
        }
        return this;
    }

    Scoreboard addDraw() {
        this.scoreX += 1;
        this.scoreO += 1;

        return this;
    }

    Player getWinnerPlayer() {
        return scoreO > scoreX ? playerO : playerX;
    }

    @Override
    public String toString() {
        return "Scoreboard" +
                System.lineSeparator() +
                this.playerX.name() + ": " + this.scoreX +
                System.lineSeparator() +
                this.playerO.name() + ": " + this.scoreO;
    }
}
