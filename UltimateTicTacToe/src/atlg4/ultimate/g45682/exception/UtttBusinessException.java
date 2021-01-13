package atlg4.ultimate.g45682.exception;

/**
 * Exception lanc&eacute;e par la manipulation des dto
 */
public class UtttBusinessException extends GameException {

    /**
     * Creates a new instance of <code>BibliothequeDTOException</code> without
     * detail message.
     */
    public UtttBusinessException() {
    }

    /**
     * Constructs an instance of <code>BibliothequeDTOException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UtttBusinessException(String msg) {
        super(msg);
    }
}
