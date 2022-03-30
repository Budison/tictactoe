package com.github.budison;

/**
 * @author Kevin Nowak
 */
public record PlayerO(String name, char sign) implements Player {
    @Override
    public String getPlayerName() {
        return this.name();
    }

    @Override
    public char getPlayerSign() {
        return this.sign();
    }
}
