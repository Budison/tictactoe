package com.github.budison;

import com.github.budison.board.BoardDimension;
import com.github.budison.player.PlayerO;
import com.github.budison.player.PlayerX;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.github.budison.Main.selectRunMode;


@Test
public class ConfigurationTest {

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
}
