package com.kodilla.sudoku.board;

public final class SudokuMove {

    private final int row;
    private final int colum;
    private final int value;

    public SudokuMove(int row, int colum, int value) {
        this.row = row;
        this.colum = colum;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColum() {
        return colum;
    }

    public int getValue() {
        return value;
    }
}
