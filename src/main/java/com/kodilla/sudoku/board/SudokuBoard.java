package com.kodilla.sudoku.board;

import com.kodilla.sudoku.prototype.Prototype;

import java.util.ArrayList;
import java.util.List;

public final class SudokuBoard extends Prototype<SudokuBoard> {

    private List<SudokuRow> sudokuRows = new ArrayList<>();

    public SudokuBoard() {
        for(int i = 1; i <= 9; i++) {
            sudokuRows.add(new SudokuRow());
        }
    }

    public void addElement(SudokuMove sudokuMove) {
        sudokuRows.get(sudokuMove.row())
                    .addElement(sudokuMove.column(), sudokuMove.value());
    }

    public SudokuElement getElement(int row, int column) {
        return sudokuRows.get(row).getSudokuElements().get(column);
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard copiedBoard = super.clone();
        copiedBoard.sudokuRows = new ArrayList<>();
        for(SudokuRow theRow : sudokuRows) {
            SudokuRow copiedRow = theRow.deepCopy();
            copiedBoard.sudokuRows.add(copiedRow);
        }
        return copiedBoard;
    }

    public List<SudokuRow> getSudokuRows() {
        return sudokuRows;
    }
}
