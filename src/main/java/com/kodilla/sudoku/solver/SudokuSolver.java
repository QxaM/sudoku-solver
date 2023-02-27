package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;

import java.util.stream.IntStream;

public final class SudokuSolver {

    public boolean solveSudoku(SudokuBoard sudokuBoard) {
        sudokuFill(sudokuBoard);
        return false;
    }

    public boolean sudokuFill(SudokuBoard sudokuBoard) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuElement elementToAnalise = sudokuBoard.getElement(i, j);
                if (elementToAnalise.getValue() == -1) {
                    analiseColumns(sudokuBoard, elementToAnalise, i);
                    analiseRows(sudokuBoard, elementToAnalise, j);
                    analiseSection(sudokuBoard, elementToAnalise, i, j);
                }
                if(elementToAnalise.getPossibleValues().size() == 1) {
                    elementToAnalise.setValue(elementToAnalise.getPossibleValues().get(0));
                    BoardPrinter.printBoard(sudokuBoard);
                }
            }
        }
        return true;
    }

    public void analiseRows(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int column) {
        IntStream.range(0,9)
                .map(i -> sudokuBoard.getElement(i, column).getValue())
                .filter(i -> i != -1)
                .forEach(sudokuElement::removePossibleValue);
    }

    public void analiseColumns(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int row) {
        IntStream.range(0,9)
                .map(i -> sudokuBoard.getElement(row, i).getValue())
                .filter(i -> i != -1)
                .forEach(sudokuElement::removePossibleValue);
    }

    public void analiseSection(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int row, int column) {
        int rowSection = row / 3;
        int columnSection = column / 3;

        int rowStartingValue = rowSection * 3;
        int rowEndingValue = (rowSection + 1) * 3;
        int columnStartingValue = columnSection * 3;
        int columnEndingValue = (columnSection + 1) * 3;

        for(int i = rowStartingValue; i < rowEndingValue; i++) {
            for(int j = columnStartingValue; j < columnEndingValue; j++) {
                int nextValue = sudokuBoard.getElement(row, column).getValue();
                if (nextValue != -1) {
                    sudokuElement.removePossibleValue(nextValue);
                }
            }
        }
    }
}
