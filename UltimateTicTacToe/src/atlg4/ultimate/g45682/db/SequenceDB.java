package atlg4.ultimate.g45682.db;

import atlg4.ultimate.g45682.exception.UtttDbException;
import javax.management.Query;

/**
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 * @author g45682
 */
public class SequenceDB {

    static final String PLAYER = "Player";
    
    static synchronized int getNextNum() throws UtttDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
           
            java.sql.Statement stmt = connexion.createStatement();
            String query = "Select count(*)+1 as nextNum FROM Player";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("nextNum");
                return nvId;
            } else {
                throw new UtttDbException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new UtttDbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }
//    static synchronized int getNextNum(String sequence) throws UtttDbException {
//        try {
//            java.sql.Connection connexion = DBManager.getConnection();
//            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
//            java.sql.PreparedStatement update = connexion.prepareStatement(query);
//            update.execute();
//            java.sql.Statement stmt = connexion.createStatement();
//            query = "Select sValue FROM Sequences where id='" + sequence + "'";
//            java.sql.ResultSet rs = stmt.executeQuery(query);
//            int nvId;
//            if (rs.next()) {
//                nvId = rs.getInt("sValue");
//                return nvId;
//            } else {
//                throw new UtttDbException("Nouveau n° de séquence inaccessible!");
//            }
//        } catch (java.sql.SQLException eSQL) {
//            throw new UtttDbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
//        }
//    }

}
