package com.kodilla.sudoku.input;

import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.board.SudokuMove;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInputTestSuite {

    @Nested
    public class UserInputConverterTestSuite {

        @Test
        void testToSudokuElement() {
            //Given
            String inputElement = "2,3,4";

            //When
            SudokuMove sudokuMove = UserInputConverter.toSudokuMove(inputElement);

            //Then
            assertAll(() -> assertEquals(2, sudokuMove.getColumn()),
                    () -> assertEquals(3, sudokuMove.getRow()),
                    () -> assertEquals(4, sudokuMove.getValue()));
        }
    }
}
