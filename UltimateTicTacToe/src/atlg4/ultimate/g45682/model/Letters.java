package atlg4.ultimate.g45682.model;

import atlg4.ultimate.g45682.exception.GameException;

/**
 *
 * @author ykard
 */
public enum Letters {
    X("X", "-fx-text-fill: #0090ff;-fx-background-color: white;"),
    O("O", "-fx-text-fill: red;-fx-background-color: white;"),
    NONE("None","");
    private final String letter;
    private final String style;

    private Letters(String letter, String style) {
        this.letter = letter;
        this.style = style;
    }

    public String getLetter() {
        return letter;
    }

    public String getStyle() {
        return style;
    }

    public static Letters getLettersFromString(String letter) {
        if (letter.equals(X.letter)) {
            return X;
        } else if (letter.equals(O.letter)) {
            return O;
        } else if (letter.equals(NONE.letter)) {
            return NONE;
        } else {
            throw new GameException("Letters error : No letter chosen.");
        }

    }

}
