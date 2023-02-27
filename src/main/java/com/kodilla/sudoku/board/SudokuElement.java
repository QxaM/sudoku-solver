package com.kodilla.sudoku.board;

import com.kodilla.sudoku.prototype.Prototype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SudokuElement extends Prototype<SudokuElement> {

    private final static int EMPTY = -1;
    private final static Set<Integer> ALL_POSSIBLE_VALUES = new HashSet<> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    private int value = EMPTY;
    private Set<Integer> possibleValues = new HashSet<>(ALL_POSSIBLE_VALUES);

    public boolean removePossibleValue(int value) {
        return this.possibleValues.remove(value);
    }

    public SudokuElement deepCopy() throws CloneNotSupportedException {
        SudokuElement clonedElement = super.clone();
        clonedElement.possibleValues = new HashSet<>();
        clonedElement.possibleValues.addAll(possibleValues);
        return clonedElement;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues.stream().toList();
    }

    public void setValue(int value) {
        this.value = value;
        this.possibleValues.clear();
    }
}
