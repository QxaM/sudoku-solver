package com.kodilla.sudoku.presentation;

public final class UserMessages {

    public static void greeting() {
        System.out.println("Hello there!\n" +
                "Welcome to Sudoku Solver");
    }

    public static void help() {
        System.out.println("""
                Enter sudoku to solve. Enter elements one a time in format as below:
                column,row,value - eg. 2,3,9
                All values have to in range of 1-9.
                If you want to solve sudoku enter SUDOKU. Have fun!""");
    }

    public static void enterElementBelow() {
        System.out.println("Enter element below: ");
    }

    public static void wrongInputMessage() {
        System.out.println("Wou have entered wrong input!");
    }
}
