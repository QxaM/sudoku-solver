package com.kodilla.sudoku.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTestSuite {

    @Test
    void testValidateUserInput() {
        //Given
        UserValidator userValidator = new UserValidator();

        //When
        UserEntry validateEnd = userValidator.validateUserInput("end");
        UserEntry validateNew = userValidator.validateUserInput("new");
        UserEntry validateStart = userValidator.validateUserInput("sudoku");
        UserEntry validateUnknown = userValidator.validateUserInput("chess");

        //Then
        assertAll(() -> assertEquals(validateEnd, UserEntry.END),
                () -> assertEquals(validateNew, UserEntry.NEW),
                () -> assertEquals(validateStart, UserEntry.START),
                () -> assertEquals(validateUnknown, UserEntry.WRONG_INPUT));
    }

    @Test
    void testIsValid() {
        //Given
        UserValidator userValidator = new UserValidator();

        //When
        boolean isValid = userValidator.isValid(UserEntry.START);
        boolean isInvalid = userValidator.isValid(UserEntry.WRONG_INPUT);

        //Then
        assertAll(() -> assertTrue(isValid),
                () -> assertFalse(isInvalid));
    }
}
