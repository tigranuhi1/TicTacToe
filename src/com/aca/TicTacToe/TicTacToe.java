package com.aca.TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Board gameBoard = createAndConfigureGameBoard();

        Player player1 = new Player(MoveType.X);
        Player player2 = new Player(MoveType.O);
        boolean isFirstPlayersTurn = false;
        InputErrorType inputErrorType = InputErrorType.CORRECT;
        MoveType moveType = null;

        do {
            gameBoard.drawGameBoard();
            if (inputErrorType.equals(InputErrorType.CORRECT)) {
                isFirstPlayersTurn = !isFirstPlayersTurn;
            }
            if (isFirstPlayersTurn) {
                System.out.println("The first player's turn:");
                inputErrorType = player1.tryMakeMove(gameBoard);
            } else {
                System.out.println("The second player's turn:");
                inputErrorType = player2.tryMakeMove(gameBoard);
            }
            if (!inputErrorType.equals(InputErrorType.CORRECT)) {
                printErrorMessage(inputErrorType);
                continue;
            }
            moveType = isFirstPlayersTurn ? player1.getMoveType() : player2.getMoveType();
        } while (!gameBoard.isGameFinished(moveType));

        gameBoard.drawGameBoard();
        System.out.println(String.format("%s player has won! Congratulations!", isFirstPlayersTurn ? "The first" : "The second"));
        System.out.println(String.format("The first player's moves count: %s", player1.getMovesCount()));
        System.out.println(String.format("The second player's moves count: %s", player2.getMovesCount()));
    }

    private static void printErrorMessage(InputErrorType inputErrorType) {
        switch (inputErrorType) {
            case ROW_INCORRECT:
                System.out.println("Row index is out of board bounds. Try again..");
                break;
            case COL_INCORRECT:
                System.out.println("Column index is out of board bounds. Try again..");
                break;
            case FIELD_HAS_VALUE:
                System.out.println("The cell already has value. Try again..");
                break;
            default:
                System.out.println("Incorrect input. Try again..");
                break;
        }
    }

    private static Board createAndConfigureGameBoard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter board size: ");
        int boardSize = sc.nextInt();
        System.out.print("Enter winning sequence size: ");
        int winSequencesize = sc.nextInt();
        return new Board(boardSize, winSequencesize);
    }
}
