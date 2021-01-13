/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.model;

/**
 *
 * @author Younes Kardillo
 */
public class Score {
    private final Player opponent;
    private final Resultat result;

    public Score(Player opponent, Resultat result) {
        this.opponent = opponent;
        this.result = result;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Resultat getResult() {
        return result;
    }
    
    
    
}
