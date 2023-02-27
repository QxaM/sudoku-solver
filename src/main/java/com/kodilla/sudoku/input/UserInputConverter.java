package com.kodilla.sudoku.input;

import com.kodilla.sudoku.board.SudokuMove;

import java.util.Arrays;
import java.util.List;

public final class UserInputConverter {

    public static SudokuMove toSudokuMove(String userInput) {
        String[] userInputs = userInput.split(",");
        List<Integer> integerInputs = Arrays.stream(userInputs)
                                            .mapToInt(Integer::parseInt)
                                            .boxed().toList();
        return new SudokuMove((integerInputs.get(0)-1), (integerInputs.get(1)-1), integerInputs.get(2));
    }
}