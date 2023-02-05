package com.kodilla.sudoku.validation;

import com.kodilla.sudoku.validation.dto.UserValidationDto;

public final class UserValidator {

    public static UserValidationDto validateUserInput(String userEntry) {
        return switch(userEntry) {
            case "new" -> new UserValidationDto(true, false, true, false);
            case "end" -> new UserValidationDto(true, false, false, true);
            case "sudoku" -> new UserValidationDto(true, true, false, false);
            default ->  new UserValidationDto(false, false, false, false);
        };
    }
}
