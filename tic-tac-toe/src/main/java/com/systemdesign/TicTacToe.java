package com.systemdesign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TicTacToe {
    private static final Logger logger = LoggerFactory.getLogger(TicTacToe.class);
    private final Board board = Board.getInstance();
    private Player player1;
    private Player player2;

    private boolean hasAnyPlayerWon() {
        return this.player1.isWinner() || this.player2.isWinner();
    }

    private Player getWinner() {
        return this.player1.isWinner() ? this.player1 : this.player2;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of player 1 [X]: ");
        this.player1 = new Player(sc.nextLine(), Symbol.X);
        System.out.print("Enter the name of player 2 [O]: ");
        this.player2 = new Player(sc.nextLine(), Symbol.O);

        Player currentPlayer = this.player1;
        this.board.print();

        while (!hasAnyPlayerWon()) {
            System.out.print(currentPlayer.getName() + "> Enter co-ordinates for [" + currentPlayer.getSymbol() + "]: ");

            try {
                String[] input = sc.nextLine().split(" ");
                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);

                this.board.makeMove(row, col, currentPlayer);

                this.board.print();

                currentPlayer = currentPlayer == player1 ? player2 : player1;
            } catch (IllegalArgumentException ex) {
                logger.error(ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.error("Please enter co-ordinates(space separated) in the format:<row-no> <col-no>");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        System.out.println(getWinner().getName() + " won. Congratulations!");
    }


    public static void main(String[] args) {
        new TicTacToe().start();
    }
}
