package com.github.budison;

import com.github.budison.board.BoardDimension;
import com.github.budison.game.Game;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.github.budison.Main.selectRunMode;
import static org.testng.Assert.*;


@Test
public class GameConfigurationTest {

    public void testRunModeCreation() {
        // Given
        String[] args1 = { };
        String[] args2 = {"demo"};
        String[] args3 = {"3", "3", "X", "O", "diagonal", "3"};
        // When
        RunMode runMode1 = selectRunMode(args1);
        RunMode runMode2 = selectRunMode(args2);
        RunMode runMode3 = selectRunMode(args3);
        // Then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(runMode1, RunMode.NORMAL);
        softAssert.assertEquals(runMode2, RunMode.DEMO_NO_ARGS);
        softAssert.assertEquals(runMode3, RunMode.DEMO_WITH_ARGS);
        softAssert.assertAll();
    }

    public void testArgsReaderCreation() {
        // Given
        String[] args = {"3", "3", "X", "O", "diagonal", "3"};
        // When
        ArgsReader argsReader = new ArgsReader(args);
        // Then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(argsReader.getBoardDimension().equals(new BoardDimension(3)));
        softAssert.assertEquals(argsReader.getWinningCondition(), 3);
        softAssert.assertTrue(argsReader.getStartingPlayer() instanceof PlayerX);
        softAssert.assertTrue(argsReader.getWinningPlayer() instanceof PlayerO);
        softAssert.assertEquals(argsReader.getWinningType(), WinningType.DIAGONAL);
        softAssert.assertEquals(argsReader.getWinningLine(), 3);
        softAssert.assertAll();
    }
    
    public void testPlayerOCreation() {
        // Given
        PlayerO playerO = new PlayerO("Kevin", 'O');
        // When
        String playerName = playerO.name();
        char playerSign = playerO.sign();
        // Then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(playerName, "Kevin");
        softAssert.assertNotEquals(playerSign, 'X');
        softAssert.assertAll();
    }

    public void testPlayerXCreation() {
        // Given
        PlayerX playerX = new PlayerX("Kevin", 'X');
        // When
        String playerName = playerX.name();
        char playerSign = playerX.sign();
        // Then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(playerName, "Kevin");
        softAssert.assertNotEquals(playerSign, 'O');
        softAssert.assertAll();
    }

    public void testIfGameHasCorrectLanguage() {
        // Given
        Game gameEn = new Game(LanguageType.EN);
        Game gameDe = new Game(LanguageType.DE);
        // When
        LanguageType english = LanguageType.EN;
        LanguageType german = LanguageType.DE;
        // Then
        assertEquals(gameEn.getLanguageType(), english);
        assertEquals(gameDe.getLanguageType(), german);
    }
}
