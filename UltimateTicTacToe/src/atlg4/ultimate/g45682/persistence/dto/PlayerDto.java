package atlg4.ultimate.g45682.persistence.dto;

/**
 *
 * @author g45682
 */
public class PlayerDto 
{

    private int id;
    private String pseudo;
    private int nbWins;
    private int nbLoses;
    private int nbDraws;
    private int nbUsedJoker;

    public PlayerDto(String pseudo) {
        this.pseudo = pseudo;
        this.nbWins = 0;
        this.nbLoses = 0;
        this.nbDraws = 0;
        this.nbUsedJoker = 0;
    }

    public PlayerDto(int id, String pseudo, int nbWins, int nbLoses, int nbDraws, int nbUsedJoker) {
        this.id = id;
        this.pseudo = pseudo;
        this.nbWins = nbWins;
        this.nbLoses = nbLoses;
        this.nbDraws = nbDraws;
        this.nbUsedJoker = nbUsedJoker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNbWins() {
        return nbWins;
    }

    public void setNbWins(int nbWins) {
        this.nbWins = nbWins;
    }

    public int getNbLoses() {
        return nbLoses;
    }

    public void setNbLoses(int nbLoses) {
        this.nbLoses = nbLoses;
    }

    public int getNbDraws() {
        return nbDraws;
    }

    public void setNbDraws(int nbDraws) {
        this.nbDraws = nbDraws;
    }

    public int getNbUsedJoker() {
        return nbUsedJoker;
    }

    public void setNbUsedJoker(int nbUsedJoker) {
        this.nbUsedJoker = nbUsedJoker;
    }
    

    @Override
    public String toString() {
        return "PlayerDto{" + "id=" + id + ", pseudo=" + pseudo + ", nbWins=" + nbWins + ", nbLoses=" + nbLoses + ", nbDraws=" + nbDraws + '}';
    }
}
