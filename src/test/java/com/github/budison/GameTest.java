package com.github.budison;

import com.github.budison.game.Game;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Kevin Nowak
 */
@Test
public class GameTest {
    @Test
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

    public void testGameRun() {
        // Given
        var list = List.of("1", "PlayerX", "PlayerO", "X", "4", "4",
                "1", "5", "2", "6", "3", "7", "4",
                "5", "1", "10", "2", "6", "3", "7", "4",
                "1", "5", "2", "6", "3", "7", "4",
                "n"
        );
        var line = String.join("\n", list);
        var inputByteArray = line.getBytes(StandardCharsets.UTF_8);
        var byteArray = new ByteArrayInputStream(inputByteArray);
        System.setIn(byteArray);
        Main.main(new String[0]);
        System.setIn(System.in);
    }
}
