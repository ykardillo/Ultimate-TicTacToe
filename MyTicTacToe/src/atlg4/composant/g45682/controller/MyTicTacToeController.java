package atlg4.composant.g45682.controller;

import atlg4.composant.g45682.model.Letters;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author g45682
 */
public class MyTicTacToeController extends AnchorPane {

    private final StringProperty choice;

    private GridPane ticTacToe;

    @FXML
    private Label lblFinish;

    public MyTicTacToeController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MyTicTacToe.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.choice = new SimpleStringProperty("");
        this.ticTacToe = (GridPane) getChildren().get(0);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        /*
        Button button = (Button) event.getTarget();
        try {
            int row = (GridPane.getRowIndex(button) == null)? 0 : GridPane.getRowIndex(button);
            int col = (GridPane.getColumnIndex(button)== null)? 0 : GridPane.getColumnIndex(button);
            
            System.out.println("Row : "+row);
            System.out.println("Col : "+col);
            System.out.println("Choice : "+choice.getValue());
            
            Letters letter = Letters.getLettersFromString(choice.getValue());
            button.setText(letter.getLetter());
            button.setStyle(letter.getStyle());
            setOneButtonDisable(button);
            
            if(hasWon(choice.getValue(), row, col)){
                finish(choice.getValue());
            }

        } catch (Exception e) {
            /*
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
             
            System.out.println("ici " + e.getMessage());
        }*/
    }

    public StringProperty choiceProperty() {
        return choice;
    }

    public void finish(String letter) {
        this.lblFinish.setVisible(true);
        this.lblFinish.setDisable(true);
        
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), this.lblFinish);

        switch (letter) {

            case "O":
                this.lblFinish.setText("O");
                this.lblFinish.setStyle("-fx-text-fill: red; -fx-font-size: 120px;-fx-font-family: \"Comic Sans MS\";");

                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.setCycleCount(3);
                fadeTransition.play();
                break;
            case "X":
                this.lblFinish.setText("X");
                this.lblFinish.setStyle("-fx-text-fill: #0090ff; -fx-font-size: 120px;-fx-font-family: \"Comic Sans MS\";");

                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.setCycleCount(3);
                fadeTransition.play();
                break;
            default:
        }
    }

    private Button getButtonFromGridPane(int row, int col) {
        for (Node node : ((GridPane) getChildren().get(0)).getChildren()) {
            int row2 = (GridPane.getRowIndex(node) == null) ? 0 : GridPane.getRowIndex(node);
            int col2 = (GridPane.getColumnIndex(node) == null) ? 0 : GridPane.getColumnIndex(node);
            if (col2 == col && row2 == row) {
                return (Button) node;
            }
        }
        return null;
    }

    public Label getLblFinish() {
        return lblFinish;
    }

    public void setOneButtonDisable(Button btn) {
        btn.setDisable(true);
        btn.setStyle(btn.getStyle() + "-fx-opacity:1;");
    }

    public void setAllButtonDisable() {
        for (Node n : ticTacToe.getChildren()) {
            setOneButtonDisable(((Button) n));
        }
    }

    public void restart() {
        for (Node n : ticTacToe.getChildren()) {
            ((Button) n).setText("");
            ((Button) n).setDisable(false);
        }
        lblFinish.setText("");
        lblFinish.setVisible(false);
        displayGoodPosition();
        
    }

    public void setButtonsHeight(double d) {
        for (Node n : ticTacToe.getChildren()) {
            ((Button) n).setPrefHeight(d);
        }
    }

    public void setButtonsWidth(double d) {
        for (Node n : ticTacToe.getChildren()) {
            ((Button) n).setPrefWidth(d);
        }
    }

    public void putLetter(String letter, Button button) {
        button.setText(letter);
        button.setStyle(Letters.getLettersFromString(letter).getStyle());
        //setOneButtonDisable(button);
    }

    public int getRowButton(Button button) {
        return (GridPane.getRowIndex(button) == null) ? 0 : GridPane.getRowIndex(button);
    }

    public int getColButton(Button button) {
        return (GridPane.getColumnIndex(button) == null) ? 0 : GridPane.getColumnIndex(button);
    }

    public void displayGoodPosition() {
        if (lblFinish.getText().equals("")) {
            for (Node node : ((GridPane) getChildren().get(0)).getChildren()) {
                if (((Button) node).getText().equals("")) {
                    ((Button) node).setStyle("-fx-background-color: yellow;");
                }
            }
        }
    }

    public void removeGoodPosition() {
        for (Node node : ((GridPane) getChildren().get(0)).getChildren()) {

            if (!((Button) node).getText().equals("")) {
                ((Button) node).setStyle(Letters.getLettersFromString(((Button) node).getText()).getStyle());
                //setOneButtonDisable(((Button) node));
            } else {
                ((Button) node).setStyle("-fx-background-color: white;");
            }
        }
    }

    public void putDisableButton() {

        for (Node n : ticTacToe.getChildren()) {
            n.setDisable(true);
        }
    }
    
}
