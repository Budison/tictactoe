package com.github.budison;

import com.github.budison.board.BoardDimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.github.budison.Main.selectRunMode;
import static org.testng.Assert.*;


@Test
public class ArgsReaderTest {

    //---------- Data providers ----------//
    @DataProvider (name = "runMode-provider")
    public Object[][] runModeProvider() {
        return new Object[][] {
                {new String[] {}, RunMode.NORMAL},
                {new String[] {"demo"}, RunMode.DEMO_NO_ARGS},
                {new String[] {"3", "3", "X", "O", "diagonal", "3"}, RunMode.DEMO_WITH_ARGS}
        };
    }

    @DataProvider (name = "args-provider")
    public Object[][] argsProvider() {
        return new Object[][] {
                {new String[] {"3", "3", "X", "O", "horizontal", "3"}, WinningType.HORIZONTAL},
                {new String[] {"3", "3", "X", "O", "vertical", "3"}, WinningType.VERTICAL},
                {new String[] {"3", "3", "X", "O", "antidiagonal", "3"}, WinningType.ANTI_DIAGONAL},
                {new String[] {"3", "3", "X", "O", "DIAGONAL", "3"}, WinningType.DIAGONAL},
                {new String[] {"3", "3", "X", "O", "AnTi Diagonal", "3"}, WinningType.ANTI_DIAGONAL},
                {new String[] {"3", "3", "X", "O", "anti-diagonal", "3"}, WinningType.ANTI_DIAGONAL}
        };
    }

    @DataProvider (name = "illegal-arguments-provider")
    public Object[][] illegalArgumentsProvider() {
        return new Object[][] {
                {new String[] {"3", "3", "X", "O", "horizontal", "0"}},
                {new String[] {"3", "3", "X", "O", "horizontal", "100"}}
        };
    }

    //---------- Tests ----------//
    @Test (dataProvider = "runMode-provider")
    public void testRunModeCreation(String[] args, RunMode expected) {
        // Given @DataProvider
        // When
        RunMode runMode = selectRunMode(args);
        // Then
        assertEquals(runMode, expected);
    }

    public void testCorrectArgsReaderCreation() {
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

    @Test (dataProvider = "args-provider")
    public void testCreateWinningTypeByString(String[] args, WinningType expected) {
        // Given @DataProvider
        // When
        ArgsReader argsReader = new ArgsReader(args);
        // Then
        assertEquals(argsReader.getWinningType(), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWinningTypeByStringException() {
        // Given
        String[] args = {"3", "3", "X", "O", "lol", "3"};
        // Then throws IllegalArgumentException
        ArgsReader argsReader = new ArgsReader(args);
    }

    public void testCreatePlayerByString() {
        // Given
        String[] args = {"3", "3", "x", "o", "diagonal", "3"};
        // When
        ArgsReader argsReader = new ArgsReader(args);
        // Then
        assertTrue(argsReader.getStartingPlayer() instanceof PlayerX);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testCreateIntByString() {
        // Given
        String[] args = {"a", "3", "x", "o", "diagonal", "3"};
        // When
        ArgsReader argsReader = new ArgsReader(args);
        // Then throws NumberFormatException
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePlayerByStringException() {
        // Given
        String[] args = {"3", "3", "A", "O", "diagonal", "3"};
        // Then throws IllegalArgumentException
        ArgsReader argsReader = new ArgsReader(args);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testArgsReaderConstructorException() {
        // Given
        String[] args = {"3", "3"};
        // Then throws IllegalArgumentException
        ArgsReader argsReader = new ArgsReader(args);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCheckBd() {
        // Given
        String[] args = {"2", "3", "X", "O", "diagonal", "3"};
        // Then
        ArgsReader argsReader = new ArgsReader(args);
    }

    @Test(dataProvider = "illegal-arguments-provider", expectedExceptions = IllegalArgumentException.class)
    public void testCheckWc(String[] args) {
        new ArgsReader(args);
    }

    @Test(dataProvider = "illegal-arguments-provider", expectedExceptions = IllegalArgumentException.class)
    public void testCheckWl(String[] args) {
        new ArgsReader(args);
    }
}
