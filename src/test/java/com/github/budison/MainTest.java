package com.github.budison;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.budison.Main.main;

/**
 * @author Kevin Nowak
 */
public class MainTest {
    @DataProvider(name = "main-method-run-provider")
    public Object[][] mainMethodRunProvider() {
        return new Object[][] {
                { List.of("1", "PlayerX", "PlayerO", "X", "4", "4",
                        "1", "5", "2", "6", "3", "7", "4",
                        "5", "1", "10", "2", "6", "3", "7", "4",
                        "1", "5", "2", "6", "3", "7", "4",
                        "n"
                        )
                }
        };
    }

    @Test
    public void testMainMethodForDemo() {
        Main main = new Main();
        main(new String[] {"4", "4", "X", "O", "horizontal", "1"});
        main(new String[] {"4", "4", "X", "O", "vertical", "1"});
        main(new String[] {"4", "4", "O", "X", "diagonal", "1"});
        main(new String[] {"4", "4", "X", "O", "antidiagonal", "1"});
        main(new String[] {"demo"});
    }
}
