package com.github.budison.board;

/**
 * @author Kevin Nowak
 */
record Cell(CellType cellType, int index) {
    @Override
    public String toString() {
        return switch (this.cellType) {
            case O -> "  O  ";
            case X -> "  X  ";
            default -> printIndex();
        };
    }

    private String printIndex() {
        return switch (String.valueOf(this.index).length()) {
            case 1 -> " ".repeat(2) + this.index + " ".repeat(2);
            case 2 -> " ".repeat(1) + this.index + " ".repeat(2);
            case 3 -> " ".repeat(1) + this.index + " ".repeat(1);
            case 4 ->  "".repeat(1) + this.index + " ".repeat(1);
            default -> " ".repeat(5);
        };
    }
    boolean checkIfFree() {
        return cellType == CellType.EMPTY;
    }
}
