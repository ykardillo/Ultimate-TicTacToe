package atlg4.ultimate.g45682.exception;

/**
 * Exception lanc&eacute;e par les acc&eacute;s db
 */
public class UtttDbException extends GameException {

    /**
     * Creates a new instance of <code>BibliothequeDBException</code> without detail message.
     */
    public UtttDbException() {
    }


    /**
     * Constructs an instance of <code>BibliothequeDBException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UtttDbException(String msg) {
        super(msg);
    }
}
