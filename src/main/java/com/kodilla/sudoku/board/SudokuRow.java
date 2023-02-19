package com.kodilla.sudoku.board;

import com.kodilla.sudoku.prototype.Prototype;

import java.util.ArrayList;
import java.util.List;

public final class SudokuRow extends Prototype<SudokuRow> {

    private List<SudokuElement> sudokuElements = new ArrayList<>();

    public SudokuRow() {
        for(int i = 1; i <= 9; i++) {
            sudokuElements.add(new SudokuElement());
        }
    }

    public SudokuRow deepCopy() throws CloneNotSupportedException {
        SudokuRow copiedRow = super.clone();
        copiedRow.sudokuElements = new ArrayList<>();
        for(SudokuElement theElement: sudokuElements) {
            SudokuElement copiedElement = theElement.deepCopy();
            copiedRow.sudokuElements.add(copiedElement);
        }
        return copiedRow;
    }

    public List<SudokuElement> getSudokuElements() {
        return sudokuElements;
    }
}
