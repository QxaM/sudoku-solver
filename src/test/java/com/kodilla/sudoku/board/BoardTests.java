package com.kodilla.sudoku.board;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Nested
    public class SudokuElementTestSuite {

        @Test
        void testRemoveFromPossibleValues() {
            //Given
            SudokuElement sudokuElement = new SudokuElement();

            //When
            int sizeBeforeRemoval = sudokuElement.getPossibleValues().size();
            boolean isRemoved = sudokuElement.removePossibleValue(8);
            int sizeAfterRemoval = sudokuElement.getPossibleValues().size();

            //Then
            assertAll(() -> assertTrue(isRemoved),
                    () -> assertEquals(9, sizeBeforeRemoval),
                    () -> assertEquals(8, sizeAfterRemoval));
        }

        @Test
        void testDeepCopy() {
            //Given
            SudokuElement sudokuElement = new SudokuElement();
            sudokuElement.setValue(5);

            SudokuElement sudokuElement1 = new SudokuElement();
            sudokuElement1.removePossibleValue(7);

            //When
            SudokuElement copiedElement = null;
            try{
                copiedElement = sudokuElement.deepCopy();
            } catch (CloneNotSupportedException e) {
                //
            }
            copiedElement.setValue(4);

            SudokuElement copiedElement1 = null;
            try{
                copiedElement1 = sudokuElement1.deepCopy();
            } catch (CloneNotSupportedException e) {
                //
            }
            copiedElement1.removePossibleValue(4);

            //Then
            assertEquals(5, sudokuElement.getValue());
            assertEquals(8, sudokuElement1.getPossibleValues().size());
            assertEquals(4, copiedElement.getValue());
            assertEquals(7, copiedElement1.getPossibleValues().size());
        }

        @Test
        void testSetValue() {
            //Given
            SudokuElement sudokuElement = new SudokuElement();

            //When
            sudokuElement.setValue(5);

            //Then
            assertAll(() -> assertEquals(5, sudokuElement.getValue()),
                    () -> assertEquals(0, sudokuElement.getPossibleValues().size()));
        }
    }

    @Nested
    public class SudokuRowTestSuite {

        @Test
        void testAddElement() {
            //Given
            SudokuRow sudokuRow = new SudokuRow();

            //When
            sudokuRow.addElement(5, 6);
            SudokuElement changedElement = sudokuRow.getSudokuElements().get(5);

            //Then
            assertAll(() -> assertEquals(6, changedElement.getValue()),
                    () -> assertEquals(0, changedElement.getPossibleValues().size()));
        }

        @Test
        void testDeepCopy() {
            //Given
            SudokuRow sudokuRow = new SudokuRow();

            SudokuElement sudokuElement1 = new SudokuElement();
            sudokuElement1.setValue(3);

            SudokuElement sudokuElement2 = new SudokuElement();
            sudokuElement2.removePossibleValue(7);

            sudokuRow.getSudokuElements().set(3, sudokuElement1);
            sudokuRow.getSudokuElements().set(7, sudokuElement2);

            //When
            SudokuRow copiedRow = null;
            try {
                copiedRow = sudokuRow.deepCopy();
            } catch (CloneNotSupportedException e) {
                //
            }
            copiedRow.getSudokuElements().get(3).setValue(5);
            copiedRow.getSudokuElements().get(7).removePossibleValue(3);

            //Then
            assertEquals(9, sudokuRow.getSudokuElements().size());
            assertEquals(9, copiedRow.getSudokuElements().size());

            assertEquals(3, sudokuRow.getSudokuElements().get(3).getValue());
            assertEquals(5, copiedRow.getSudokuElements().get(3).getValue());

            assertEquals(8, sudokuRow.getSudokuElements().get(7).getPossibleValues().size());
            assertEquals(7, copiedRow.getSudokuElements().get(7).getPossibleValues().size());
        }
    }

    @Nested
    public class SudokuBoardTestSuite {

        @Test
        void testAddElement() {
            //Given
            SudokuBoard sudokuBoard = new SudokuBoard();

            //When
            SudokuMove sudokuMove = new SudokuMove(6, 5, 7);
            sudokuBoard.addElement(sudokuMove);
            SudokuElement changedElement = sudokuBoard.getSudokuRows().get(5)
                                                        .getSudokuElements().get(6);

            //Then
            assertAll(() -> assertEquals(7, changedElement.getValue()),
                    () -> assertEquals(0, changedElement.getPossibleValues().size()));
        }

        @Test
        void testDeepCopy() {
            //Given
            SudokuBoard sudokuBoard = new SudokuBoard();

            SudokuRow sudokuRow1 = new SudokuRow();
            SudokuRow sudokuRow2 = new SudokuRow();

            SudokuElement sudokuElement1 = new SudokuElement();
            sudokuElement1.setValue(4);
            SudokuElement sudokuElement2 = new SudokuElement();
            sudokuElement2.removePossibleValue(5);
            SudokuElement sudokuElement3 = new SudokuElement();
            sudokuElement3.setValue(6);
            SudokuElement sudokuElement4 = new SudokuElement();
            sudokuElement4.removePossibleValue(7);
            sudokuElement4.removePossibleValue(9);

            sudokuRow1.getSudokuElements().set(0, sudokuElement1);
            sudokuRow1.getSudokuElements().set(2, sudokuElement2);
            sudokuRow2.getSudokuElements().set(3, sudokuElement3);
            sudokuRow2.getSudokuElements().set(4, sudokuElement4);

            sudokuBoard.getSudokuRows().set(1, sudokuRow1);
            sudokuBoard.getSudokuRows().set(5, sudokuRow2);

            //When
            SudokuBoard copiedBoard = null;
            try {
                copiedBoard = sudokuBoard.deepCopy();
            }catch (CloneNotSupportedException e) {
                //
            }
            copiedBoard.getSudokuRows().set(1, sudokuRow2);

            //Then
            assertEquals(9, sudokuBoard.getSudokuRows().size());
            assertEquals(9, copiedBoard.getSudokuRows().size());

            assertEquals(9, sudokuBoard.getSudokuRows().get(0).getSudokuElements().size());
            assertEquals(9, copiedBoard.getSudokuRows().get(0).getSudokuElements().size());

            assertEquals(4, sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(0).getValue());
            assertEquals(8, sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(2).getPossibleValues().size());

            assertEquals(6, copiedBoard.getSudokuRows().get(1).getSudokuElements().get(3).getValue());
            assertEquals(7, copiedBoard.getSudokuRows().get(1).getSudokuElements().get(4).getPossibleValues().size());
        }
    }
}
