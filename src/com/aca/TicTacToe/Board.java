package com.aca.TicTacToe;

public class Board {
    private MoveType[][] board;
    private int winSequenceSize;

    public Board(int size, int winSequence) {
        board = new MoveType[size][size];
        winSequenceSize = winSequence;
    }

    void drawGameBoard() {
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(String.format("  %s ", i));
        }
        System.out.println();

        String line = "  " + new String(new char[(board.length * 4) + 1]).replace("\0", "-");
        System.out.println(line);
        for (int i = 0; i < board.length; i++) {
            System.out.print(String.format("%s | ", i));
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null)
                    System.out.print("  | ");
                else {
                    System.out.print(String.format("%s | ", board[i][j]));
                }
            }
            System.out.println();
            System.out.println(line);
        }
    }

    InputErrorType trySetValue(int row, int col, MoveType moveType) {
        if (row < 0 || row >= board.length) {
            return InputErrorType.ROW_INCORRECT;
        }
        if (col < 0 || col >= board[0].length) {
            return InputErrorType.COL_INCORRECT;
        }
        if (board[row][col] != null)
            return InputErrorType.FIELD_HAS_VALUE;
        board[row][col] = moveType;
        return InputErrorType.CORRECT;
    }

    boolean isGameFinished(MoveType moveType) {
        if (isGameFinishedHorizontally(moveType))
            return true;
        if (isGameFinishedVertically(moveType))
            return true;
        if (isGameFinishedDiagonally(moveType))
            return true;
        return false;
    }

    private boolean isGameFinishedHorizontally(MoveType moveType) {
        int sequenceCount;
        for (int row = 0; row < board.length; row++) {
            sequenceCount = 0;
            for (int col = 0; col < board[row].length && winSequenceSize <= board[row].length - col + sequenceCount; col++) {
                if (board[row][col] != null && board[row][col].equals(moveType))
                    sequenceCount++;
                else
                    sequenceCount = 0;

                if (sequenceCount == winSequenceSize)
                    return true;
            }
        }
        return false;
    }

    private boolean isGameFinishedVertically(MoveType moveType) {
        int sequenceCount;
        for (int col = 0; col < board[0].length; col++) {
            sequenceCount = 0;
            for (int row = 0; row < board.length && winSequenceSize <= board.length - row  + sequenceCount; row++) {
                if (board[row][col] != null && board[row][col].equals(moveType))
                    sequenceCount++;
                else
                    sequenceCount = 0;

                if (sequenceCount == winSequenceSize)
                    return true;
            }
        }
        return false;
    }

    private boolean isGameFinishedDiagonally(MoveType moveType) {
        int sequenceCount;
        //check diagonals from right to left
        int row = 0;
        int col = winSequenceSize - 1;
        while (row < winSequenceSize - 1) {
            sequenceCount = 0;
            for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
                if (board[i][j] != null && board[i][j].equals(moveType))
                    sequenceCount++;
                else
                    sequenceCount = 0;

                if (sequenceCount == winSequenceSize)
                    return true;
            }
            if (col < board[row].length - 1)
                col++;
            else
                row++;
        }
        //check diagonals from left to right
        row = 0;
        col = winSequenceSize - 1;
        while (row < winSequenceSize - 1) {
            sequenceCount = 0;
            for (int i = row, j = col; i < board.length && j < board[i].length; i++, j++) {
                if (board[i][j] != null && board[i][j].equals(moveType))
                    sequenceCount++;
                else
                    sequenceCount = 0;

                if (sequenceCount == winSequenceSize)
                    return true;
            }
            if (col > 0)
                col--;
            else
                row++;
        }
        return false;
    }
}
