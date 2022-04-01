package com.github.budison;

import com.github.budison.board.BoardDimension;
import com.github.budison.game.Game;
import com.github.budison.game.GameState;
import com.github.budison.io.IOController;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.github.budison.Main.selectRunMode;
import static org.testng.Assert.*;

/**
 * @author Kevin Nowak
 */

@Test
public class IOControllerTest {
    public void testGetStartedMessage() {
        // Given
        IOController ioController = new IOController();
        // When
        GameState gameState = GameState.GAME;
        // Then
    }
}
