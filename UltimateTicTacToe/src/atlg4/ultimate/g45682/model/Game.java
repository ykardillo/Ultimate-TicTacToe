/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.model;

import atlg4.ultimate.g45682.exception.GameException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Younes Kardillo
 */
public class Game {

    private List<Player> players;
    private UltimateTicTacToe ultimateTicTacToe;
    private Player currentPlayer;
    private Point lastMove;

    public Game(List<Player> players, UltimateTicTacToe ultimateTicTacToe, Player currentPlayer, Point lastMove) {
        this.players = players;
        this.ultimateTicTacToe = ultimateTicTacToe;
        this.currentPlayer = currentPlayer;
        this.lastMove = lastMove;
    }

    public Game() {
        this.players = new ArrayList<>();
        this.ultimateTicTacToe = new UltimateTicTacToe();
        this.lastMove = new Point(-1, -1);
    }

    public Game(List<Player> players) {
        this.players = players;
        this.ultimateTicTacToe = new UltimateTicTacToe();
        this.currentPlayer = players.get(0);
        this.lastMove = new Point(-1, -1);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public UltimateTicTacToe getUltimateTicTacToe() {
        return ultimateTicTacToe;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Point getLastMove() {
        return lastMove;
    }

    public void nextPlayer() {
        Player tmpPlayer = players.get(0);
        players.set(0, players.get(1));
        players.set(1, tmpPlayer);
        currentPlayer = players.get(0);
    }

    public void play(Point bigPosition, Point littlePosition) {
        System.out.println("joker = " + currentPlayer.isUsedJoker());
        System.out.println("usedJoker = " + currentPlayer.hasBeenUsedJoker());
        if (!currentPlayer.hasBeenUsedJoker() && currentPlayer.isUsedJoker()) {
            ultimateTicTacToe.putLetterWithJoker(currentPlayer.getRole(), bigPosition, littlePosition);
            currentPlayer.usedJoker();
            if (ultimateTicTacToe.getUltimateTicTacToe()[littlePosition.getX()][littlePosition.getY()].gameOver()) {
                lastMove = new Point(-1, -1);
            } else {
                lastMove = littlePosition;
            }
        }else if (lastMove.getX() == -1 && lastMove.getY() == -1) {
            ultimateTicTacToe.putLetter(currentPlayer.getRole(), bigPosition, littlePosition);
            if (ultimateTicTacToe.getUltimateTicTacToe()[littlePosition.getX()][littlePosition.getY()].gameOver()) {
                lastMove = new Point(-1, -1);
            } else {
                lastMove = littlePosition;
            }

        } else if (bigPosition.getX() != lastMove.getX() || bigPosition.getY() != lastMove.getY()) {
            throw new GameException("Game error : Bad TicTacToe chosen");
        } else {
            ultimateTicTacToe.putLetter(currentPlayer.getRole(), bigPosition, littlePosition);
            if (ultimateTicTacToe.getUltimateTicTacToe()[littlePosition.getX()][littlePosition.getY()].gameOver()) {
                lastMove = new Point(-1, -1);
            } else {
                lastMove = littlePosition;
            }
        }
    }

    public void initialize() {
        players.get(0).setRole(Letters.O);
        players.get(1).setRole(Letters.X);
        this.currentPlayer = players.get(0);
    }

    public boolean gameOver() {
        return !(ultimateTicTacToe.getWinner().getLetter().equals(Letters.NONE.getLetter()));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void putToAllGoodProposition() {
        this.lastMove = new Point(-1, -1);
    }

    public Player getWinner() {
        if (gameOver()) {
            if (ultimateTicTacToe.getWinner().getLetter().equals(players.get(0).getRole().getLetter())) {
                return players.get(0);
            } else {
                return players.get(1);
            }
        }
        return null;

    }

    public Player getLooser() {
        if (gameOver()) {
            if (ultimateTicTacToe.getWinner().getLetter().equals(players.get(0).getRole().getLetter())) {
                return players.get(1);
            } else {
                return players.get(0);
            }
        }
        return null;
    }

    public void addToHistory() {
        getLooser().addScoreToHistory(getWinner(), Resultat.LOSE);
        getWinner().addScoreToHistory(getLooser(), Resultat.WIN);
    }

    public void addToHistoryExaeco() {
        players.get(0).addScoreToHistory(players.get(1), Resultat.EXAEQUO);
        players.get(1).addScoreToHistory(players.get(0), Resultat.EXAEQUO);
    }

    public boolean isUsedJoker() {
        return currentPlayer.isUsedJoker();
    }

    public void useJoker() {
        currentPlayer.useJoker();
    }

}
