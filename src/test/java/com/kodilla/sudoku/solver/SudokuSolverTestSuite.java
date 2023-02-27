package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.board.SudokuMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSolverTestSuite {

    @Test
    void testAnaliseColumns() {
        //Given
        SudokuSolver sudokuSolver = new SudokuSolver();

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseColumns(sudokuBoard, analysingElement, 3);

        //Then
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testAnaliseRows() {
        //Given
        SudokuSolver sudokuSolver = new SudokuSolver();

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseRows(sudokuBoard, analysingElement, 2);

        //Then
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testAnaliseSection() {
        //Given
        SudokuSolver sudokuSolver = new SudokuSolver();

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.addElement(new SudokuMove(2, 3, 5));

        //When
        SudokuElement analysingElement = new SudokuElement();
        sudokuSolver.analiseSection(sudokuBoard, analysingElement, 3, 2);

        //Then
        assertFalse(analysingElement.getPossibleValues().contains(5));
    }

    @Test
    void testSudokuFill() {
        //Given
        SudokuSolver sudokuSolver = new SudokuSolver();

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

        //When
        BoardPrinter.printBoard(sudokuBoard);
        sudokuSolver.sudokuFill(sudokuBoard);

        //Then
        assertTrue(false);
    }
}
