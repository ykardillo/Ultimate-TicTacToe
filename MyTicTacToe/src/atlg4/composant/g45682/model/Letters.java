package atlg4.composant.g45682.model;

/**
 *
 * @author ykard
 */
public enum Letters {
    X("X", "-fx-text-fill: #0090ff;-fx-background-color: white;"),
    O("O", "-fx-text-fill: red;-fx-background-color: white;");
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
        } else {
            throw new NullPointerException("No letter chosen.");
        }

    }

}
