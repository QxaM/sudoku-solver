package com.kodilla.sudoku.board;

public final class SudokuMove {

    private final int column;
    private final int row;
    private final int value;

    public SudokuMove(int column, int row, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }
}
