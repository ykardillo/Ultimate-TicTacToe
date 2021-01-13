/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.model;

import atlg4.ultimate.g45682.exception.GameException;

/**
 *
 * @author Younes Kardillo
 */
public class UltimateTicTacToe {

    private final MyTicTacToe[][] ultimateTicTacToe;
    private Letters winner;

    public UltimateTicTacToe(MyTicTacToe[][] ultimateTicTacToe, Letters winner) {
        this.ultimateTicTacToe = ultimateTicTacToe;
        this.winner = winner;
    }

    public UltimateTicTacToe() {
        this.ultimateTicTacToe = new MyTicTacToe[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                ultimateTicTacToe[x][y] = new MyTicTacToe();
            }
        }
        this.winner = Letters.NONE;
    }

    public boolean isInside(Point position) {
        return (position.getX() >= 0 && position.getX() < 3
                && position.getY() >= 0 && position.getY() < 3);
    }

    public Letters getWinnerAt(Point position) {
        return ultimateTicTacToe[position.getX()][position.getY()].getWinner();
    }

    public void putLetter(Letters letter, Point bigPosition, Point littlePosition) {
        if (!isInside(bigPosition)) {
            throw new GameException("Ultimate TicTacToe error : Position not inside");
        }
        if (letter.getLetter().equals(Letters.NONE.getLetter())) {
            throw new GameException("Ultimate TicTacToe error : No letter chosen");
        }
        if (!(ultimateTicTacToe[bigPosition.getX()][bigPosition.getY()].getWinner().getLetter().equals(Letters.NONE.getLetter()))) {
            throw new GameException("Ultimate TicTacToe error : Position chosen not empty");
        }
        ultimateTicTacToe[bigPosition.getX()][bigPosition.getY()].putLetter(letter, littlePosition);
        if (hasWon(letter, bigPosition)) {
            System.out.println("ppsdgpsdpgpsdgpsdpgspdg");
            winner = letter;
            System.out.println("dfdfsqdfsdf");
        }
    }

    public void putLetterWithJoker(Letters letter, Point bigPosition, Point littlePosition) {
        if (!isInside(bigPosition)) {
            throw new GameException("Ultimate TicTacToe error : Position not inside");
        }
        if (letter.getLetter().equals(Letters.NONE.getLetter())) {
            throw new GameException("Ultimate TicTacToe error : No letter chosen");
        }
        ultimateTicTacToe[bigPosition.getX()][bigPosition.getY()].putLetterWithJoker(letter, littlePosition);
        if (hasWon(letter, bigPosition)) {
            System.out.println("ppsdgpsdpgpsdgpsdpgspdg");
            winner = letter;
            System.out.println("dfdfsqdfsdf");
        }
    }

    public boolean hasWon(Letters letter, Point position) {
        return (ultimateTicTacToe[position.getX()][0].getWinner().getLetter().equals(letter.getLetter()) // 3-in-the-row
                && ultimateTicTacToe[position.getX()][1].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[position.getX()][2].getWinner().getLetter().equals(letter.getLetter())
                || ultimateTicTacToe[0][position.getY()].getWinner().getLetter().equals(letter.getLetter()) // 3-in-the-column
                && ultimateTicTacToe[1][position.getY()].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[2][position.getY()].getWinner().getLetter().equals(letter.getLetter())
                || position.getX() == position.getY() // 3-in-the-diagonal
                && ultimateTicTacToe[0][0].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[1][1].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[2][2].getWinner().getLetter().equals(letter.getLetter())
                || position.getX() + position.getY() == 2 // 3-in-the-opposite-diagonal
                && ultimateTicTacToe[0][2].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[1][1].getWinner().getLetter().equals(letter.getLetter())
                && ultimateTicTacToe[2][0].getWinner().getLetter().equals(letter.getLetter()));
    }

    public MyTicTacToe[][] getUltimateTicTacToe() {
        return ultimateTicTacToe;
    }

    public Letters getWinner() {
        return winner;
    }

}
