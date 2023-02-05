package com.kodilla.sudoku;

import com.kodilla.sudoku.input.UserInput;
import com.kodilla.sudoku.presentation.UserMessages;

public class SudokuGameRunner {
    public static void main(String[] args) {
        UserMessages.greeting();
        UserMessages.help();
        UserMessages.enterElementBelow();

        UserInput userInput = new UserInput();


    }
}