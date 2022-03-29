package com.github.budison.io;

import com.github.budison.LanguageType;

/**
 * @author Kevin Nowak
 */
public class ioController {
    LanguageType languageType = LanguageType.EN;

    public int readWinningCondition() {
        return ioGameConfiguration.winningCondition();
    }

    public LanguageType readLanguageType() {
        return this.languageType = ioGameConfiguration.readLanguageType();
    }
}
