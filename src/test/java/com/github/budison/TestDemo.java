package com.github.budison;

import com.github.budison.board.BoardDimension;
import com.github.budison.game.DemoConfigDataHolder;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Kevin Nowak
 */
@Test
public class TestDemo {
    public void testDemoConfigDataHolder() {
        DemoConfigDataHolder data = new DemoConfigDataHolder(
                new PlayerX("PlayerX", 'X'),
                new PlayerO("PlayerO", 'O'),
                new BoardDimension(3),
                3,
                new PlayerX("PlayerX", 'X'),
                new PlayerX("PlayerX", 'X'),
                WinningType.DIAGONAL,
                3);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(data.boardDimension(), new BoardDimension(3));
        softAssert.assertEquals(data.startingPlayer(), data.startingPlayer());
        softAssert.assertEquals(data.playerO(), data.playerO());
    }
}
