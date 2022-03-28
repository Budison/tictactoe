package com.github.budison;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.github.budison.Main.selectRunMode;


@Test
public class ConfigurationTest {

    public void testRunModeCreation() {
        // Given
        String[] args1 = { };
        String[] args2 = {"demo"};
        String[] args3 = {"chance", "family", "treat", "death", "envelope"};
        // When
        RunMode runMode1 = selectRunMode(args1);
        RunMode runMode2 = selectRunMode(args2);
        RunMode runMode3 = selectRunMode(args3);
        // Then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(runMode1, RunMode.NORMAL);
        softAssert.assertEquals(runMode2, RunMode.DEMO_NO_INPUT);
        softAssert.assertEquals(runMode3, RunMode.DEMO_WITH_INPUT);
        softAssert.assertAll();
    }
}
