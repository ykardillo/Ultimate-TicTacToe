package atlg4.ultimate.g45682.db;

import atlg4.ultimate.g45682.exception.UtttDbException;
import java.sql.*;

/**
 * Offre les outils de connexion et de gestion de transaction.
 *
  * @author g45682
 *
 */
public class DBManager {

    private static Connection connection;
    //private static MesSettingsDeConnexion dbChoisie;

    /**
     * Retourne la connexion établie ou à défaut, l'établit
     *
     * @return la connexion établie.
     */
    public static Connection getConnection() throws UtttDbException {
        if (connection == null) {
            try {
                //           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "moha", "salut10");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "younes", "123456");
            } catch (SQLException ex) {
                throw new UtttDbException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * débute une transaction
     */
    public static void startTransaction() throws UtttDbException {
        try {

            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new UtttDbException("Impossible de démarrer une transaction: " + ex.getMessage());
        }
    }

    /**
     * débute une transaction en spécifiant son niveau d'isolation Attention,
     * ceci n'est pas implémenté sous Access! (cette notion sera abordée
     * ultérieurement dans le cours de bd)
     */
    public static void startTransaction(int isolationLevel) throws UtttDbException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new UtttDbException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new UtttDbException("Impossible de démarrer une transaction: " + ex.getMessage());
        }
    }

    /**
     * valide la transaction courante
     */
    public static void validateTransaction() throws UtttDbException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new UtttDbException("Impossible de valider la transaction: " + ex.getMessage());
        }
    }

    /**
     * annule la transaction courante
     */
    public static void cancelTransaction() throws UtttDbException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new UtttDbException("Impossible d'annuler la transaction: " + ex.getMessage());
        }
    }
}
