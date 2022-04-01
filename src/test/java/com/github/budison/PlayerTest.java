package com.github.budison;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Kevin Nowak
 */
@Test
public class PlayerTest {
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
}
