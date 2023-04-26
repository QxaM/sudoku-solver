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
        sudokuBoard.addElement(new SudokuMove(3, 2, 5));
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
        sudokuBoard.addElement(new SudokuMove(3, 2, 5));
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
        sudokuBoard.addElement(new SudokuMove(3, 2, 5));
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
        sudokuBoard.addElement(new SudokuMove(0, 1, 5));
        sudokuBoard.addElement(new SudokuMove(0, 2, 4));
        sudokuBoard.addElement(new SudokuMove(1, 4, 3));
        sudokuBoard.addElement(new SudokuMove(1, 8, 9));
        sudokuBoard.addElement(new SudokuMove(2, 4, 7));
        sudokuBoard.addElement(new SudokuMove(2, 7, 2));
        sudokuBoard.addElement(new SudokuMove(2, 8, 1));
        sudokuBoard.addElement(new SudokuMove(3, 4, 8));
        sudokuBoard.addElement(new SudokuMove(3, 5, 2));
        sudokuBoard.addElement(new SudokuMove(4, 0, 7));
        sudokuBoard.addElement(new SudokuMove(4, 6, 9));
        sudokuBoard.addElement(new SudokuMove(4, 7, 5));
        sudokuBoard.addElement(new SudokuMove(5, 0, 3));
        sudokuBoard.addElement(new SudokuMove(5, 7, 6));
        sudokuBoard.addElement(new SudokuMove(6, 1, 3));
        sudokuBoard.addElement(new SudokuMove(6, 3, 4));
        sudokuBoard.addElement(new SudokuMove(6, 5, 2));
        sudokuBoard.addElement(new SudokuMove(7, 1, 4));
        sudokuBoard.addElement(new SudokuMove(7, 2, 9));
        sudokuBoard.addElement(new SudokuMove(7, 3, 1));
        sudokuBoard.addElement(new SudokuMove(8, 3, 6));
        sudokuBoard.addElement(new SudokuMove(8, 6, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        try{
            sudokuSolver.sudokuFill();
        }catch (NoMoreMovesException e) {}

        //Then
        assertEquals(3, sudokuBoard.getElement(4, 3).getValue());
        assertEquals(5, sudokuBoard.getElement(4, 7).getValue());
        assertEquals(7, sudokuBoard.getElement(8, 5).getValue());
    }

    @Test
    void testSolveSudokuUntilBacktrack() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(0, 1, 5));
        sudokuBoard.addElement(new SudokuMove(0, 2, 4));
        sudokuBoard.addElement(new SudokuMove(1, 4, 3));
        sudokuBoard.addElement(new SudokuMove(1, 8, 9));
        sudokuBoard.addElement(new SudokuMove(2, 4, 7));
        sudokuBoard.addElement(new SudokuMove(2, 7, 2));
        sudokuBoard.addElement(new SudokuMove(2, 8, 1));
        sudokuBoard.addElement(new SudokuMove(3, 4, 8));
        sudokuBoard.addElement(new SudokuMove(3, 5, 2));
        sudokuBoard.addElement(new SudokuMove(4, 0, 7));
        sudokuBoard.addElement(new SudokuMove(4, 6, 9));
        sudokuBoard.addElement(new SudokuMove(4, 7, 5));
        sudokuBoard.addElement(new SudokuMove(5, 0, 3));
        sudokuBoard.addElement(new SudokuMove(5, 7, 6));
        sudokuBoard.addElement(new SudokuMove(6, 1, 3));
        sudokuBoard.addElement(new SudokuMove(6, 3, 4));
        sudokuBoard.addElement(new SudokuMove(6, 5, 2));
        sudokuBoard.addElement(new SudokuMove(7, 1, 4));
        sudokuBoard.addElement(new SudokuMove(7, 2, 9));
        sudokuBoard.addElement(new SudokuMove(7, 3, 1));
        sudokuBoard.addElement(new SudokuMove(8, 3, 6));
        sudokuBoard.addElement(new SudokuMove(8, 6, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        try{
            sudokuSolver.solveSudoku();
        }catch (NoMoreMovesException e){}

        //Then
        assertEquals(5, sudokuBoard.getElement(7, 4).getValue());
        assertEquals(9, sudokuBoard.getElement(8, 4).getValue());
        assertEquals(7, sudokuBoard.getElement(7, 7).getValue());
        assertEquals(3, sudokuBoard.getElement(4, 3).getValue());
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
        sudokuBoard.addElement(new SudokuMove(0, 1, 5));
        sudokuBoard.addElement(new SudokuMove(0, 2, 4));
        sudokuBoard.addElement(new SudokuMove(1, 4, 3));
        sudokuBoard.addElement(new SudokuMove(1, 8, 9));
        sudokuBoard.addElement(new SudokuMove(2, 4, 7));
        sudokuBoard.addElement(new SudokuMove(2, 7, 2));
        sudokuBoard.addElement(new SudokuMove(2, 8, 1));
        sudokuBoard.addElement(new SudokuMove(3, 4, 8));
        sudokuBoard.addElement(new SudokuMove(3, 5, 2));
        sudokuBoard.addElement(new SudokuMove(4, 0, 7));
        sudokuBoard.addElement(new SudokuMove(4, 6, 9));
        sudokuBoard.addElement(new SudokuMove(4, 7, 5));
        sudokuBoard.addElement(new SudokuMove(5, 0, 3));
        sudokuBoard.addElement(new SudokuMove(5, 7, 6));
        sudokuBoard.addElement(new SudokuMove(6, 1, 3));
        sudokuBoard.addElement(new SudokuMove(6, 3, 4));
        sudokuBoard.addElement(new SudokuMove(6, 5, 2));
        sudokuBoard.addElement(new SudokuMove(7, 1, 4));
        sudokuBoard.addElement(new SudokuMove(7, 2, 9));
        sudokuBoard.addElement(new SudokuMove(7, 3, 1));
        sudokuBoard.addElement(new SudokuMove(8, 3, 6));
        sudokuBoard.addElement(new SudokuMove(8, 6, 8));
        sudokuBoard.addElement(new SudokuMove(8, 8, 3));
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);

        //When
        BoardPrinter.printBoard(sudokuBoard);
        try{
            sudokuSolver.solveSudoku();
        }catch (NoMoreMovesException e){}

        //Then
        assertNotEquals(-1, sudokuBoard.getElement(0, 0).getValue());
    }
}
