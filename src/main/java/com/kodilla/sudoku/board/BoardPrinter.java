package com.kodilla.sudoku.board;

import java.util.StringJoiner;

public final class BoardPrinter {

    public static void printBoard(SudokuBoard sudokuBoard) {
        for(SudokuRow row: sudokuBoard.getSudokuRows()) {
            StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
            for(SudokuElement element: row.getSudokuElements()) {
                String elementToAdd = " ";
                if(element.getValue() > 0) {
                    elementToAdd = element.toString();
                }
                stringJoiner.add(elementToAdd);
            }
            System.out.println(stringJoiner);
        }
        System.out.println("---------------------------");
    }
}
