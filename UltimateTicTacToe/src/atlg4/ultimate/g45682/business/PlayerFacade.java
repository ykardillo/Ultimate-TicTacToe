package atlg4.ultimate.g45682.business;

import atlg4.ultimate.g45682.exception.UtttDbException;
import atlg4.ultimate.g45682.exception.UtttBusinessException;
import atlg4.ultimate.g45682.persistence.dto.*;
import atlg4.ultimate.g45682.db.DBManager;
import atlg4.ultimate.g45682.exception.GameException;
import java.util.Collection;

/**
 * Classe reprenant les méthodes destinées aux players
 * @author g45682
 */
public class PlayerFacade {

    /**
     * retourne la collection des Players repris dans le gestionnaire de
     * persistance
     *
     * @return collection des Players repris dans le gestionnaire de persistance
     * @throws atlg4.ultimate.g45682.exception.UtttBusinessException
     */
    public static Collection<PlayerDto> getAllPlayers() throws UtttBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PlayerDto> col = PlayerBI.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (UtttDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UtttDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UtttBusinessException("Liste des Players inaccessible! \n" + msg);
            }
        }
    }

    /**
     * retourne le player de pseudo sous forme de <code>PlayerDto</code>
     *
     * @param pseudo pseudo du plater recherché
     * @return PlayerDto persistant de clé id ou null si non trouvé
     * @throws atlg4.ultimate.g45682.exception.UtttBusinessException
     */
    public static PlayerDto findPlayerByPseudo(String pseudo) throws UtttBusinessException {
        try {
            DBManager.startTransaction();
            PlayerDto player = PlayerBI.findByPseudo(pseudo);
            DBManager.validateTransaction();
            return player;
        } catch (UtttDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UtttDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UtttBusinessException("Player inaccessible! \n" + msg);
            }
        }
    }

    /**
     * ajoute au gestionnaire de persistance le player repris en paramètre
     *
     * @param player Player à ajouter 
     * @throws atlg4.ultimate.g45682.exception.UtttBusinessException
     */
    public static void addPlayer(PlayerDto player) throws UtttBusinessException {
        try {
            DBManager.startTransaction();
            PlayerBI.add(player);
            DBManager.validateTransaction();
        } catch (UtttDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UtttDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UtttBusinessException("Ajout du player impossible! \n" + msg);
            }
        }
    }

    /**
     * met à jour au niveau du gestionnaire de persistance le player passée en
     * paramètre
     *
     * @param player player à mettre à jour
     * @throws atlg4.ultimate.g45682.exception.UtttBusinessException
     */
    public static void updatePlayer(PlayerDto player) throws UtttBusinessException {
        try {
            DBManager.startTransaction();
            PlayerBI.update(player);
            DBManager.validateTransaction();
        } catch (UtttDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UtttDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UtttBusinessException("Mise à jour du player impossible! \n" + msg);
            }
        }
    }

    /**
     * supprime du gestionnaire de persistance le Player dont player reprend
     * l'id
     *
     * @param player
     * @throws atlg4.ultimate.g45682.exception.UtttBusinessException
     */
    public static void deletePlayer(PlayerDto player) throws UtttBusinessException {
        try {
            DBManager.startTransaction();
            PlayerBI.delete(player.getPseudo());
            DBManager.validateTransaction();

        } catch (GameException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UtttDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UtttBusinessException("Suppression du player impossible! \n" + msg);
            }
        }
    }
}
