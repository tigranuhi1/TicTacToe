package com.aca.TicTacToe;

import java.util.Scanner;

public class Player {
    private MoveType moveType;
    private int movesCount;

    public Player(MoveType moveType) {
        this.moveType = moveType;
        movesCount = 0;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public int getMovesCount() {
        return movesCount;
    }

    private void incrementMovesCount() {
        movesCount++;
    }

    public InputErrorType tryMakeMove(Board gameBoard) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter row index:");
        int row = sc.nextInt();
        System.out.println("Enter column index:");
        int col = sc.nextInt();
        InputErrorType inputErrorType = gameBoard.trySetValue(row, col, this.moveType);
        if (inputErrorType.equals(InputErrorType.CORRECT)) {
            incrementMovesCount();
            return InputErrorType.CORRECT;
        }
        return inputErrorType;
    }
}
