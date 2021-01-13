package atlg4.ultimate.g45682.business;

import atlg4.ultimate.g45682.db.PlayerDB;
import atlg4.ultimate.g45682.persistence.dto.PlayerDto;
import atlg4.ultimate.g45682.exception.UtttDbException;
import atlg4.ultimate.g45682.model.Player;
import java.util.Collection;

/**
 *
 * @author g45682
 */
public class PlayerBI {

    static void add(PlayerDto player) throws UtttDbException {
         PlayerDB.insertDb(player);
    }

    static void delete(String pseudo) throws UtttDbException {
        PlayerDB.deleteDb(pseudo);
    }

    static void update(PlayerDto player) throws UtttDbException {
        PlayerDB.updateDb(player);
    }

    static PlayerDto findByPseudo(String pseudo) throws UtttDbException {
        Player player = new Player(pseudo, null);
        Collection<PlayerDto> col = PlayerDB.getPlayer(player);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }

    static Collection<PlayerDto> findAll() throws UtttDbException {
        Player player = new Player("",null);
        Collection<PlayerDto> col = PlayerDB.getAllPlayers();
        return col;
    }
}
