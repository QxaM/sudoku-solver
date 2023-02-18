package com.kodilla.sudoku.validation;

public final class UserValidator {

    public UserEntry validateUserInput(String userEntry) {
        return switch(userEntry) {
            case "new" -> UserEntry.NEW;
            case "end" -> UserEntry.END;
            case "sudoku" -> UserEntry.START;
            default ->  UserEntry.WRONG_INPUT;
        };
    }

    public boolean isValid(UserEntry userEntry) {
        return userEntry != UserEntry.WRONG_INPUT;
    }
}
