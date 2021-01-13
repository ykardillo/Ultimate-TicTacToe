/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.controller;

import atlg4.ultimate.g45682.business.PlayerFacade;
import atlg4.ultimate.g45682.persistence.dto.PlayerDto;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g45682
 */
public class LoginController extends AnchorPane {

    @FXML
    private TextField tfdFirstName;
    @FXML
    private TextField tfdSecondName;
    @FXML
    private HBox hboxLoading;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;

    private Stage stage;

    public LoginController(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.stage = stage;

    }

    @FXML
    private void startGame(ActionEvent event) throws IOException, InterruptedException {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        if (!tfdFirstName.getText().equals(tfdSecondName.getText()) && !tfdFirstName.getText().isEmpty() && !tfdSecondName.getText().isEmpty()) {
            if (PlayerFacade.findPlayerByPseudo(tfdFirstName.getText()) != null && PlayerFacade.findPlayerByPseudo(tfdSecondName.getText()) != null) {

                UltimateTicTacToeController root = new UltimateTicTacToeController();
                stage.setScene(new Scene(root));
                root.start(tfdFirstName.getText(), tfdSecondName.getText());
//                errorAlert.setContentText("le premier pseudo est déja utilisé");
//                errorAlert.showAndWait();
            } else if (PlayerFacade.findPlayerByPseudo(tfdFirstName.getText()) != null && PlayerFacade.findPlayerByPseudo(tfdSecondName.getText()) == null) {

                PlayerDto playerDto2 = new PlayerDto(tfdSecondName.getText());
                PlayerFacade.addPlayer(playerDto2);

                UltimateTicTacToeController root = new UltimateTicTacToeController();
                stage.setScene(new Scene(root));
                root.start(tfdFirstName.getText(), tfdSecondName.getText());
//                errorAlert.setContentText("le deuxieme pseudo est déja utilisé");
//                errorAlert.showAndWait();
            } else if (PlayerFacade.findPlayerByPseudo(tfdFirstName.getText()) == null && PlayerFacade.findPlayerByPseudo(tfdSecondName.getText()) != null) {

                PlayerDto playerDto1 = new PlayerDto(tfdFirstName.getText());
                PlayerFacade.addPlayer(playerDto1);

                UltimateTicTacToeController root = new UltimateTicTacToeController();
                stage.setScene(new Scene(root));
                root.start(tfdFirstName.getText(), tfdSecondName.getText());
//                errorAlert.setContentText("le deuxieme pseudo est déja utilisé");
//                errorAlert.showAndWait();
            } else {

                PlayerDto playerDto1 = new PlayerDto(tfdFirstName.getText());
                PlayerDto playerDto2 = new PlayerDto(tfdSecondName.getText());
                PlayerFacade.addPlayer(playerDto1);
                PlayerFacade.addPlayer(playerDto2);

                UltimateTicTacToeController root = new UltimateTicTacToeController();
                stage.setScene(new Scene(root));
                root.start(tfdFirstName.getText(), tfdSecondName.getText());
                /*
                hboxLoading.setVisible(true);
                new Bounce(circle1).setCycleCount(4).setDelay(Duration.valueOf("500ms")).play();
                new Bounce(circle2).setCycleCount(4).setDelay(Duration.valueOf("1000ms")).play();
                new Bounce(circle3).setCycleCount(4).setDelay(Duration.valueOf("1100ms")).play();
                Task task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("1) icicicicicii");

                    UltimateTicTacToeController root = new UltimateTicTacToeController();
                    //System.out.println("2) icicicicicii");

                    //System.out.println(stage);
                    stage.setScene(new Scene(root));
                    //System.out.println("3) icicicicicii");

                    //System.out.println("4) icicicicicii");
                    root.start(tfdFirstName.getText(), tfdSecondName.getText());
                    //System.out.println("5) icicicicicii");
        
                        return null;
                    }
                };
                Thread thread = new Thread(task);
                thread.start();
                 */

            }
        } else if (tfdFirstName.getText().isEmpty() || tfdSecondName.getText().isEmpty()) {
            errorAlert.setContentText("Veuillez remplir les champs vides");
            errorAlert.showAndWait();
        } else if (tfdFirstName.getText().equals(tfdSecondName.getText())) {
            errorAlert.setContentText("Erreur Meme pseudo");
            errorAlert.showAndWait();
        }

    }

}
