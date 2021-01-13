/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Younes Kardillo
 */
public class Player {
    private final String name;
    private int gamesWon;
    private int gamesPlayed;
    private Letters role;
    private final List<Score> scoresHistory;
    private boolean joker;
    private boolean usedJoker;

    public Player(String name, int gamesWon, int gamesPlayed, Letters role, List<Score> scoresHistory, boolean joker, boolean usedJoker) {
        this.name = name;
        this.gamesWon = gamesWon;
        this.gamesPlayed = gamesPlayed;
        this.role = role;
        this.scoresHistory = scoresHistory;
        this.joker = joker;
        this.usedJoker = usedJoker;
    }

    public Player(String name, Letters role){
        
        this.name = name;
        this.gamesWon = 0;
        this.gamesPlayed = 0;
        this.role = role;
        this.scoresHistory = new ArrayList<>();
        this.joker = false;
        this.usedJoker = false;
    }
    
    public String getName() {
        return name;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public Letters getRole() {
        return role;
    }

    public List<Score> getScoresHistory() {
        return scoresHistory;
    }
    
    public void addScoreToHistory(Player opponent, Resultat result){
        scoresHistory.add(new Score(opponent, result));
        gamesPlayed++;
        if(result == Resultat.WIN){
            gamesWon++;
        }
    }
    
    public void setRole(Letters letter){
        this.role = letter;
    }
    
    
    public void useJoker(){
        this.joker = true;
    }
    
    public void usedJoker(){
        this.usedJoker = true;
    }
    public boolean isUsedJoker(){
        return joker;
    }
    
    public boolean hasBeenUsedJoker(){
        return usedJoker;
    }
    
    
    
}
