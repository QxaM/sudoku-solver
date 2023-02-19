package com.kodilla.sudoku.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTestSuite {

    @Test
    void testValidateUserInput() {
        //Given
        UserValidator userValidator = new UserValidator();
        UserEntry validateEnd;
        UserEntry validateNew;
        UserEntry validateStart;
        UserEntry validateUnknown;
        UserEntry validateAddElement;

        //When
        boolean isValidEnd = userValidator.validateUserInput("end");
        validateEnd = userValidator.getUserEntry();

        boolean isValidNew = userValidator.validateUserInput("new");
        validateNew = userValidator.getUserEntry();

        boolean isValidStart = userValidator.validateUserInput("sudoku");
        validateStart = userValidator.getUserEntry();

        boolean isInvalid = userValidator.validateUserInput("chess");
        validateUnknown = userValidator.getUserEntry();

        boolean isValidEntry = userValidator.validateUserInput("1,1,1");
        validateAddElement = userValidator.getUserEntry();

        //Then
        assertAll(() -> assertEquals(UserEntry.END, validateEnd),
                () -> assertEquals(UserEntry.NEW, validateNew),
                () -> assertEquals(UserEntry.START, validateStart),
                () -> assertEquals(UserEntry.WRONG_INPUT, validateUnknown),
                () -> assertEquals(UserEntry.ELEMENT_INPUT, validateAddElement),
                () -> assertTrue(isValidNew),
                () -> assertTrue(isValidStart),
                () -> assertTrue(isValidEntry),
                () -> assertTrue(isValidEnd),
                () -> assertFalse(isInvalid));
    }

    @Test
    void testIsValid() {
        //Given
        UserValidator userValidator = new UserValidator();

        //When
        userValidator.validateUserInput("new");
        boolean isValid = userValidator.isValid();

        userValidator.validateUserInput("chess");
        boolean isInvalid = userValidator.isValid();

        //Then
        assertAll(() -> assertTrue(isValid),
                () -> assertFalse(isInvalid));
    }

    @Test
    void testIsNew() {
        //Given
        UserValidator userValidator = new UserValidator();
        userValidator.validateUserInput("new");

        //When
        boolean isNew = userValidator.isNew();

        //Then
        assertTrue(isNew);
    }

    @Test
    void testIsEnd() {
        //Given
        UserValidator userValidator = new UserValidator();
        userValidator.validateUserInput("end");

        //When
        boolean isEnd = userValidator.isEnd();

        //Then
        assertTrue(isEnd);
    }

    @Test
    void testIsStart() {
        //Given
        UserValidator userValidator = new UserValidator();
        userValidator.validateUserInput("sudoku");

        //When
        boolean isStart = userValidator.isStart();

        //Then
        assertTrue(isStart);
    }

    @Test
    void testIsElementInput() {
        //Given
        UserValidator userValidator = new UserValidator();
        userValidator.validateUserInput("1,1,1");

        //When
        boolean isElementInput = userValidator.isElementInput();

        //Then
        assertTrue(isElementInput);
    }
}
