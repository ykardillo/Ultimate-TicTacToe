package atlg4.ultimate.g45682.db;

import atlg4.ultimate.g45682.persistence.dto.PlayerDto;
import atlg4.ultimate.g45682.exception.UtttDbException;
import atlg4.ultimate.g45682.model.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès au gestionnaire de persistance pour les Players
 * @author g45682
 */
public class PlayerDB {

    public static List<PlayerDto> getAllPlayers() throws UtttDbException {
       
        List<PlayerDto> al = new ArrayList<PlayerDto>();
        try {
            String query = "Select id, pseudo, nbWins, nbLoses, nbDraws, nbUsedJoker  FROM Player ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt = null;
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PlayerDto(rs.getInt("id"), rs.getString("pseudo"), rs.getInt("nbWins"), rs.getInt("nbLoses"), rs.getInt("nbDraws"), rs.getInt("nbUsedJoker")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new UtttDbException("Instanciation des players  impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static List<PlayerDto> getPlayer(Player player) throws UtttDbException {
        List<PlayerDto> al = new ArrayList<PlayerDto>();
        try {
            String query = "Select id, pseudo, nbWins, nbLoses, nbDraws, nbUsedJoker  FROM Player ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt = null;
            String where = "";
            query = query +  " where pseudo= '"+player.getName()+"';";
            System.out.println(query);
            stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PlayerDto(rs.getInt("id"), rs.getString("pseudo"), rs.getInt("nbWins"), rs.getInt("nbLoses"), rs.getInt("nbDraws"), rs.getInt("nbUsedJoker")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new UtttDbException("Instanciation du player  impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void updateDb(PlayerDto player) throws UtttDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Player set " + "nbWins=?," + "nbLoses=?, " + "nbDraws=?, " + "nbUsedJoker=? " + "where pseudo=?;";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);

            update.setInt(1, player.getNbWins());
            update.setInt(2, player.getNbLoses());
            update.setInt(3, player.getNbDraws());
            update.setInt(4, player.getNbUsedJoker());
            update.setString(5, player.getPseudo());
            update.executeUpdate();
        } catch (Exception ex) {
            throw new UtttDbException("UpDate Player, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(PlayerDto player) throws UtttDbException {
        try {
            int num = SequenceDB.getNextNum();
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Player(id, pseudo, nbWins, nbLoses, nbDraws, nbUsedJoker) "
                    + "values(?, ?, ?,?,?,? )");
            insert.setInt(1, num);
            insert.setString(2, player.getPseudo());
            insert.setInt(3, player.getNbWins());
            insert.setInt(4, player.getNbLoses());
            insert.setInt(5, player.getNbDraws());
            insert.setInt(6, player.getNbUsedJoker());
            insert.executeUpdate();
           return num;
        } catch (Exception ex) {
            throw new UtttDbException("Player: ajout impossible\n" + ex.getMessage());
        }
    }
    
      public static void deleteDb(String pseudo) throws UtttDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Player where pseudo=" + pseudo);
        } catch (Exception ex) {
            throw new UtttDbException("Player: suppression impossible\n" + ex.getMessage());
        }
    }

}
