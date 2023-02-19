package com.kodilla.sudoku.validation;

public final class UserValidator {

    private UserEntry userEntry;
    private String element = "0,0,-1";

    public boolean validateUserInput(String userEntry) {
        switch(userEntry) {
            case "new" -> this.userEntry = UserEntry.NEW;
            case "end" -> this.userEntry = UserEntry.END;
            case "sudoku" -> this.userEntry = UserEntry.START;
            default -> {
                if(userEntry.matches("[1-9],[1-9],[1-9]")){
                    this.userEntry = UserEntry.ELEMENT_INPUT;
                    this.element = userEntry;
                } else {
                    this.userEntry = UserEntry.WRONG_INPUT;
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() {
        return this.userEntry != UserEntry.WRONG_INPUT;
    }

    public boolean isNew() {
        return this.userEntry == UserEntry.NEW;
    }

    public boolean isEnd() {
        return this.userEntry == UserEntry.END;
    }

    public boolean isStart() {
        return this.userEntry == UserEntry.START;
    }

    public boolean isElementInput() {
        return this.userEntry == UserEntry.ELEMENT_INPUT;
    }

    public UserEntry getUserEntry() {
        return userEntry;
    }

    public String getElement() {
        return element;
    }
}
