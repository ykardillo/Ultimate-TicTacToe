package atlg4.ultimate.g45682.exception;

/**
 * Where that manage the errors of the game.
 *
 * @author ykard
 */
public class GameException extends RuntimeException {

    /**
     * Create a new instance of <code>GameException</code> without detail
     * message.
     */
    public GameException() {
    }

    /**
     * Constructs an instance of <code>GameException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameException(String msg) {
        super(msg);
    }
}
