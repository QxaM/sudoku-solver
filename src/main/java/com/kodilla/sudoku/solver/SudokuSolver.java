package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.backtrack.Backtrack;
import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.board.SudokuMove;

import java.util.stream.IntStream;

public final class SudokuSolver {

    private SudokuBoard sudokuBoard;
    private final Backtrack backtrack = new Backtrack();

    public SudokuSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public boolean solveSudoku() throws NoMoreMovesException{
        boolean isSolved = false;
        do{
            boolean wasFilled;
            do{
                wasFilled = sudokuFill();
            }while(wasFilled);
            if (isSolved()) {
                isSolved = true;
            } else {
                guessValue();
            }
        }while(!isSolved);
        return true;
    }

    public boolean sudokuFill() throws NoMoreMovesException {
        boolean elementSet = false;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuElement elementToAnalise = sudokuBoard.getElement(i, j);
                if (elementToAnalise.getValue() == -1) {
                    if(elementToAnalise.getPossibleValues().size() == 0) {
                        sudokuBoard = stepBack();
                        return true;
                    }

                    analiseRow(elementToAnalise, i);
                    analiseColumn(elementToAnalise, j);
                    analiseSection(elementToAnalise, i, j);

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

    public void analiseColumn(SudokuElement sudokuElement, int column) {
        IntStream.range(0,9)
                .map(i -> sudokuBoard.getElement(i, column).getValue())
                .filter(i -> i != -1)
                .forEach(sudokuElement::removePossibleValue);
    }

    public void analiseRow(SudokuElement sudokuElement, int row) {
        IntStream.range(0,9)
                .map(i -> sudokuBoard.getElement(row, i).getValue())
                .filter(i -> i != -1)
                .forEach(sudokuElement::removePossibleValue);
    }

    public void analiseSection(SudokuElement sudokuElement, int row, int column) {
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

    public boolean isSolved() {
        return sudokuBoard.getSudokuRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getSudokuElements().stream())
                .map(SudokuElement::getValue)
                .noneMatch(value -> value == -1);
    }

    public void guessValue() throws NoMoreMovesException {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(sudokuBoard.getElement(i, j).getValue() == -1) {
                    if (sudokuBoard.getElement(i, j).getPossibleValues().size() == 0) {
                        throw new NoMoreMovesException();
                    }

                    int value = sudokuBoard.getElement(i, j).getPossibleValues().get(0);
                    SudokuMove nextMove = new SudokuMove(i, j, value);

                    try {
                        backtrack.getPreviousBoards().push(sudokuBoard.deepCopy());
                        backtrack.setPreviousMove(nextMove);
                    }catch (CloneNotSupportedException e) {}
                    sudokuBoard.getElement(i, j).setValue(value);

                    BoardPrinter.printBoard(sudokuBoard);
                    return;
                }
            }
        }
    }

    public SudokuBoard stepBack() throws NoMoreMovesException {
        if(backtrack.getPreviousBoards().isEmpty()) {
            throw new NoMoreMovesException();
        }
        SudokuBoard previousBoard = backtrack.getPreviousBoards().pop();
        int row = backtrack.getPreviousMove().row();
        int column = backtrack.getPreviousMove().column();
        int value = backtrack.getPreviousMove().value();
        previousBoard.getElement(row, column).removePossibleValue(value);
        return previousBoard;
    }
}
