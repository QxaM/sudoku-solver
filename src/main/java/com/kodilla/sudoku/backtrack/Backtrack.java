package com.kodilla.sudoku.backtrack;

import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.board.SudokuMove;

import java.util.ArrayDeque;
import java.util.Queue;

public final class Backtrack {

    private final ArrayDeque<SudokuBoard> previousBoards = new ArrayDeque<>();
    private SudokuMove previousMove;

    public ArrayDeque<SudokuBoard> getPreviousBoards() {
        return previousBoards;
    }

    public SudokuMove getPreviousMove() {
        return previousMove;
    }

    public void setPreviousMove(SudokuMove previousMove) {
        this.previousMove = previousMove;
    }
}
