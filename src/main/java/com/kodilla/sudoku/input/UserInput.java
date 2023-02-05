package com.kodilla.sudoku.input;

import java.util.Scanner;

public final class UserInput {

    private final Scanner scanner = new Scanner(System.in);

    public String getUserEntry() {
        return scanner.nextLine().toLowerCase();
    }

    public void cleanUp() {
        scanner.close();
    }
}
