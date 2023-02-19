package com.kodilla.sudoku;

import com.kodilla.sudoku.input.UserInput;
import com.kodilla.sudoku.presentation.UserMessages;
import com.kodilla.sudoku.validation.UserValidator;

public class SudokuGameRunner {
    public static void main(String[] args) {
        UserMessages.greeting();

        UserInput userInput = new UserInput();
        UserValidator userValidator = new UserValidator();

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
        } while (!userValidator.isEnd());
    }
}