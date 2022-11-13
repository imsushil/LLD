package com.systemdesign;

public class Board {
    private static final Board board = new Board();

    private static final int ROW = 3;
    private static final int COL = 3;
    private static final int N = 3;
    private final char[][] box;

    private Board() {
        this.box = new char[ROW][COL];
        initialise();
    }

    private void initialise() {
        for (int row_i = 0; row_i < ROW; ++row_i) {
            for (int col_j = 0; col_j < COL; ++col_j) {
                this.box[row_i][col_j] = Symbol.BLANK.getValue();
            }
        }
    }

    private boolean hasPlayerWon(int row, int col, Player player) {
        boolean winRow = true;
        boolean winCol = true;
        boolean winDiag = true;
        boolean winRevDiag = true;
        char symbolValue = player.getSymbol().getValue();

        for (int i = 0; i < N; ++i) {
            if (this.box[row][i] != symbolValue) {
                winRow = false;
            }
            if (this.box[i][col] != symbolValue) {
                winCol = false;
            }
            if (this.box[i][i] != symbolValue) {
                winDiag = false;
            }
            if (this.box[i][N - 1 - i] != symbolValue) {
                winRevDiag = false;
            }
        }

        return winRow || winCol || winDiag || winRevDiag;
    }

    public char makeMove(int row, int col, Player player) {
        if (player.getSymbol() == Symbol.BLANK) {
            throw new IllegalArgumentException("_ is the symbol for unoccupied box. Players cannot use it.");
        }
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException("Move out of board boundary");
        }
        if (this.box[row][col] != Symbol.BLANK.getValue()) {
            throw new IllegalArgumentException("Box is already occupied.");
        }

        this.box[row][col] = player.getSymbol().getValue();

        player.setWinner(hasPlayerWon(row, col, player));
        return this.box[row][col];
    }


    public void print() {
        for (int row_i = 0; row_i < ROW; ++row_i) {
            for (int col_j = 0; col_j < COL; ++col_j) {
                System.out.print(this.box[row_i][col_j] + " ");
            }
            System.out.println();
        }
    }

    public static Board getInstance() {
        return board;
    }
}
