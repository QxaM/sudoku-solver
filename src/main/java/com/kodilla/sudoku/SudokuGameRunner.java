package com.kodilla.sudoku;

import com.kodilla.sudoku.board.BoardPrinter;
import com.kodilla.sudoku.board.SudokuBoard;
import com.kodilla.sudoku.board.SudokuMove;
import com.kodilla.sudoku.input.UserInput;
import com.kodilla.sudoku.input.UserInputConverter;
import com.kodilla.sudoku.presentation.UserMessages;
import com.kodilla.sudoku.solver.SudokuSolver;
import com.kodilla.sudoku.validation.UserValidator;

public class SudokuGameRunner {
    public static void main(String[] args) {
        UserMessages.greeting();

        UserInput userInput = new UserInput();
        UserValidator userValidator = new UserValidator();

        SudokuBoard sudokuBoard = new SudokuBoard();

        boolean isEnd = false;
        do {
            do {
                UserMessages.help();
                UserMessages.enterElementBelow();
                String input = userInput.getUserEntry();
                boolean isValidInput = userValidator.validateUserInput(input);
                if (!isValidInput) {
                    UserMessages.wrongInputMessage();
                }
            } while (!userValidator.isValid());

            switch(userValidator.getUserEntry()) {
                case END -> isEnd = true;
                case START -> {
                    SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard);
                    sudokuSolver.solveSudoku();
                }
                case ELEMENT_INPUT -> {
                    SudokuMove sudokuMove = UserInputConverter.toSudokuMove(userValidator.getElement());
                    sudokuBoard.addElement(sudokuMove);
                }
            }
            BoardPrinter.printBoard(sudokuBoard);
        } while (!isEnd);

        userInput.cleanUp();
    }
}