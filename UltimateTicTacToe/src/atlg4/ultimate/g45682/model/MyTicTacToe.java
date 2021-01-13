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
public class MyTicTacToe {

    private final Letters ticTacToe[][];
    private Letters winner;

    public MyTicTacToe(Letters[][] ticTacToe, Letters winner) {
        this.ticTacToe = ticTacToe;
        this.winner = winner;
    }

    public MyTicTacToe() {
        this.ticTacToe = new Letters[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                ticTacToe[x][y] = Letters.NONE;
            }
        }
        this.winner = Letters.NONE;
    }

    public void putLetter(Letters letter, Point position) {
        if (!isInside(position)) {
            throw new GameException("TicTacToe error : Position not inside");
        }
        if (!(ticTacToe[position.getX()][position.getY()].getLetter().equals(Letters.NONE.getLetter()))) {
            System.out.println("pkpas" + ticTacToe[position.getX()][position.getY()].getLetter());
            throw new GameException("TicTacToe error : Position chosen not empty");
        }
        ticTacToe[position.getX()][position.getY()] = letter;
        if (hasWon(letter, position)) {
            winner = letter;
        }

    }

    public void putLetterWithJoker(Letters letter, Point position) {
        if (!isInside(position)) {
            throw new GameException("TicTacToe error : Position not inside");
        }
        ticTacToe[position.getX()][position.getY()] = letter;
        if (hasWon(letter, position)) {
            winner = letter;
        }

    }

    public boolean hasWon(Letters letter, Point position) {
        return (ticTacToe[position.getX()][0].getLetter().equals(letter.getLetter()) // 3-in-the-row
                && ticTacToe[position.getX()][1].getLetter().equals(letter.getLetter())
                && ticTacToe[position.getX()][2].getLetter().equals(letter.getLetter())
                || ticTacToe[0][position.getY()].getLetter().equals(letter.getLetter()) // 3-in-the-column
                && ticTacToe[1][position.getY()].getLetter().equals(letter.getLetter())
                && ticTacToe[2][position.getY()].getLetter().equals(letter.getLetter())
                || position.getX() == position.getY() // 3-in-the-diagonal
                && ticTacToe[0][0].getLetter().equals(letter.getLetter())
                && ticTacToe[1][1].getLetter().equals(letter.getLetter())
                && ticTacToe[2][2].getLetter().equals(letter.getLetter())
                || position.getX() + position.getY() == 2 // 3-in-the-opposite-diagonal
                && ticTacToe[0][2].getLetter().equals(letter.getLetter())
                && ticTacToe[1][1].getLetter().equals(letter.getLetter())
                && ticTacToe[2][0].getLetter().equals(letter.getLetter()));
    }

    public Letters[][] getTicTacToe() {
        return ticTacToe;
    }

    public Letters getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        String outPut = "";
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (ticTacToe[x][y].getLetter().equals(Letters.NONE.getLetter())) {

                    outPut += (y < 2) ? "   |" : "  ";
                } else {
                    outPut += (y < 2) ? " " + ticTacToe[x][y].getLetter() + " |" : " " + ticTacToe[x][y].getLetter();
                }
            }
            outPut += (x < 2) ? "\n-----------\n" : "";
        }

        return outPut;
    }

    public Letters getLetterAt(Point position) {
        return ticTacToe[position.getX()][position.getY()];
    }

    public boolean isInside(Point position) {
        return (position.getX() >= 0 && position.getX() < 3
                && position.getY() >= 0 && position.getY() < 3);
    }

    public boolean gameOver() {
        return !(winner.getLetter().equals(Letters.NONE.getLetter()));
    }

    public void setWinner(Letters lettre) {
        winner = lettre;
    }

}
