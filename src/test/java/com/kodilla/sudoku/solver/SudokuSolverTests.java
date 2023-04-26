package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.board.SudokuMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTests {

    @Test
    void testAnaliseColumns() {
        //Given

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseRow(analysingElement, 3);

        //Then
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testAnaliseRows() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseColumn(analysingElement, 2);

        //Then
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testAnaliseSection() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseSection(analysingElement, 4, 1);

        //Then
        assertEquals(8, analysingElement.getPossibleValues().size());
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testSudokuFill() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(1, 0, 5));
        sudokuBoard.addElement(new SudokuMove(2, 0, 4));
        sudokuBoard.addElement(new SudokuMove(4, 1, 3));
        sudokuBoard.addElement(new SudokuMove(8, 1, 9));
        sudokuBoard.addElement(new SudokuMove(4, 2, 7));
        sudokuBoard.addElement(new SudokuMove(7, 2, 2));
        sudokuBoard.addElement(new SudokuMove(8, 2, 1));
        sudokuBoard.addElement(new SudokuMove(4, 3, 8));
        sudokuBoard.addElement(new SudokuMove(5, 3, 2));
        sudokuBoard.addElement(new SudokuMove(0, 4, 7));
        sudokuBoard.addElement(new SudokuMove(6, 4, 9));
        sudokuBoard.addElement(new SudokuMove(7, 4, 5));
        sudokuBoard.addElement(new SudokuMove(0, 5, 3));
        sudokuBoard.addElement(new SudokuMove(7, 5, 6));
        sudokuBoard.addElement(new SudokuMove(1, 6, 3));
        sudokuBoard.addElement(new SudokuMove(3, 6, 4));
        sudokuBoard.addElement(new SudokuMove(4, 6, 2));
        sudokuBoard.addElement(new SudokuMove(1, 7, 4));
        sudokuBoard.addElement(new SudokuMove(2, 7, 9));
        sudokuBoard.addElement(new SudokuMove(3, 7, 1));
        sudokuBoard.addElement(new SudokuMove(3, 8, 6));
        sudokuBoard.addElement(new SudokuMove(6, 8, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        sudokuSolver.sudokuFill();

        //Then
        assertEquals(3, sudokuBoard.getElement(4, 3).getValue());
        assertEquals(5, sudokuBoard.getElement(7, 4).getValue());
        assertEquals(7, sudokuBoard.getElement(8, 5).getValue());
    }

    @Test
    void testSolveSudokuUntilBacktrack() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(1, 0, 5));
        sudokuBoard.addElement(new SudokuMove(2, 0, 4));
        sudokuBoard.addElement(new SudokuMove(4, 1, 3));
        sudokuBoard.addElement(new SudokuMove(8, 1, 9));
        sudokuBoard.addElement(new SudokuMove(4, 2, 7));
        sudokuBoard.addElement(new SudokuMove(7, 2, 2));
        sudokuBoard.addElement(new SudokuMove(8, 2, 1));
        sudokuBoard.addElement(new SudokuMove(4, 3, 8));
        sudokuBoard.addElement(new SudokuMove(5, 3, 2));
        sudokuBoard.addElement(new SudokuMove(0, 4, 7));
        sudokuBoard.addElement(new SudokuMove(6, 4, 9));
        sudokuBoard.addElement(new SudokuMove(7, 4, 5));
        sudokuBoard.addElement(new SudokuMove(0, 5, 3));
        sudokuBoard.addElement(new SudokuMove(7, 5, 6));
        sudokuBoard.addElement(new SudokuMove(1, 6, 3));
        sudokuBoard.addElement(new SudokuMove(3, 6, 4));
        sudokuBoard.addElement(new SudokuMove(4, 6, 2));
        sudokuBoard.addElement(new SudokuMove(1, 7, 4));
        sudokuBoard.addElement(new SudokuMove(2, 7, 9));
        sudokuBoard.addElement(new SudokuMove(3, 7, 1));
        sudokuBoard.addElement(new SudokuMove(3, 8, 6));
        sudokuBoard.addElement(new SudokuMove(6, 8, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        sudokuSolver.solveSudoku();

        //Then
        assertEquals(2, sudokuBoard.getElement(6, 4).getValue());
        assertEquals(9, sudokuBoard.getElement(8, 4).getValue());
        assertEquals(8, sudokuBoard.getElement(6, 5).getValue());
        assertEquals(3, sudokuBoard.getElement(7, 5).getValue());
    }

    @Test
    void testIsSolved() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.getSudokuRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getSudokuElements().stream())
                .forEach(sudokuElement -> sudokuElement.setValue(1));

        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        boolean isSolved = sudokuSolver.isSolved();

        //Then
        assertTrue(isSolved);
    }

    @Test
    void testGuessValue() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(1, 0, 5));
        sudokuBoard.addElement(new SudokuMove(2, 0, 4));
        sudokuBoard.addElement(new SudokuMove(4, 1, 3));
        sudokuBoard.addElement(new SudokuMove(8, 1, 9));
        sudokuBoard.addElement(new SudokuMove(4, 2, 7));
        sudokuBoard.addElement(new SudokuMove(7, 2, 2));
        sudokuBoard.addElement(new SudokuMove(8, 2, 1));
        sudokuBoard.addElement(new SudokuMove(4, 3, 8));
        sudokuBoard.addElement(new SudokuMove(5, 3, 2));
        sudokuBoard.addElement(new SudokuMove(0, 4, 7));
        sudokuBoard.addElement(new SudokuMove(6, 4, 9));
        sudokuBoard.addElement(new SudokuMove(7, 4, 5));
        sudokuBoard.addElement(new SudokuMove(0, 5, 3));
        sudokuBoard.addElement(new SudokuMove(7, 5, 6));
        sudokuBoard.addElement(new SudokuMove(1, 6, 3));
        sudokuBoard.addElement(new SudokuMove(3, 6, 4));
        sudokuBoard.addElement(new SudokuMove(4, 6, 2));
        sudokuBoard.addElement(new SudokuMove(1, 7, 4));
        sudokuBoard.addElement(new SudokuMove(2, 7, 9));
        sudokuBoard.addElement(new SudokuMove(3, 7, 1));
        sudokuBoard.addElement(new SudokuMove(3, 8, 6));
        sudokuBoard.addElement(new SudokuMove(6, 8, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        sudokuSolver.solveSudoku();

        //Then
        assertNotEquals(-1, sudokuBoard.getElement(0, 0).getValue());
    }
}
