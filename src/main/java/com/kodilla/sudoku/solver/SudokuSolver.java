package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.backtrack.Backtrack;
import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;

import java.util.stream.IntStream;

public final class SudokuSolver {

    private final Backtrack backtrack = new Backtrack();

    public boolean solveSudoku(SudokuBoard sudokuBoard) {
        boolean wasFilled;
        do{
            wasFilled = sudokuFill(sudokuBoard);
        }while (wasFilled);
        return false;
    }

    public boolean sudokuFill(SudokuBoard sudokuBoard) {
        boolean elementSet = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuElement elementToAnalise = sudokuBoard.getElement(i, j);
                if (elementToAnalise.getValue() == -1) {
                    analiseRow(sudokuBoard, elementToAnalise, i);
                    analiseColumn(sudokuBoard, elementToAnalise, j);
                    analiseSection(sudokuBoard, elementToAnalise, i, j);

                    if(elementToAnalise.getPossibleValues().size() == 1) {
                        elementToAnalise.setValue(elementToAnalise.getPossibleValues().get(0));
                        BoardPrinter.printBoard(sudokuBoard);
                        elementSet = true;
                    }
                }
            }
        }
        return elementSet;
    }

    public void analiseColumn(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int column) {
        IntStream.range(0,9)
                .map(i -> sudokuBoard.getElement(i, column).getValue())
                .filter(i -> i != -1)
                .forEach(sudokuElement::removePossibleValue);
    }

    public void analiseRow(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int row) {
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
            if(i == row) {
                continue;
            }
            for(int j = columnStartingValue; j < columnEndingValue; j++) {
                if(j == column) {
                    continue;
                }
                int nextValue = sudokuBoard.getElement(i, j).getValue();
                if (nextValue != -1) {
                    sudokuElement.removePossibleValue(nextValue);
                }
            }
        }
    }
}
