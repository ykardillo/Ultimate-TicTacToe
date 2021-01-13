package atlg4.ultimate.g45682.controller;

import atlg4.composant.g45682.controller.MyTicTacToeController;
import atlg4.ultimate.g45682.business.PlayerFacade;
import atlg4.ultimate.g45682.exception.GameException;
import atlg4.ultimate.g45682.model.Game;
import atlg4.ultimate.g45682.model.Letters;
import atlg4.ultimate.g45682.model.Player;
import atlg4.ultimate.g45682.model.Point;
import atlg4.ultimate.g45682.persistence.dto.PlayerDto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g45682
 */
public class UltimateTicTacToeController extends AnchorPane {

    @FXML
    private GridPane ultimateTicTacToe;

    @FXML
    private Label firstPlayer;

    @FXML
    private Label secondPlayer;

    @FXML
    private Button btnJoker;

    private Game game;

    public UltimateTicTacToeController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UltimateTicTacToe.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        this.game = new Game();

    }

    @FXML
    private void newGame() throws IOException {
        Stage stage = (Stage) firstPlayer.getScene().getWindow();
        LoginController root = new LoginController(stage);
        stage.setScene(new Scene(root));
    }

    @FXML
    private void restart() {
        ultimateTicTacToe.getChildren().stream().filter((ticTacToe) -> (ticTacToe instanceof MyTicTacToeController)).forEachOrdered((ticTacToe) -> {
            ((MyTicTacToeController) ticTacToe).restart();
        });
        if (!game.gameOver()) {
            game.addToHistoryExaeco();
        }
        List<Player> players = game.getPlayers();
        game = new Game(players);
        removeDisplayCurrentPlayer();
        displayCurrentPlayer(game.getCurrentPlayer().getName());

    }

    @FXML
    private void endGame() {
        removeGoodPosition();

        ultimateTicTacToe.getChildren().forEach((node) -> {
            ((MyTicTacToeController) node).putDisableButton();
        });
    }

    public void start(String firstName, String secondName) {
        Player player1 = new Player(firstName, Letters.O);
        Player player2 = new Player(secondName, Letters.X);
        game.addPlayer(player1);
        game.addPlayer(player2);

        displayPlayersNames(firstName, secondName);

        game.initialize();
        removeDisplayCurrentPlayer();
        displayCurrentPlayer(game.getCurrentPlayer().getName());
        displayGoodPosition(game.getLastMove());
        ultimateTicTacToe.getChildren().stream().filter((ticTacToeG) -> (ticTacToeG instanceof MyTicTacToeController)).forEachOrdered((Node ticTacToeG) -> {
            GridPane grid = (GridPane) ((AnchorPane) ticTacToeG).getChildren().get(0);
            grid.getChildren().forEach((caseLetter) -> {
                caseLetter.setOnMouseClicked((event) -> {

                    int rowCase = ((MyTicTacToeController) ticTacToeG).getRowButton((Button) caseLetter);
                    int colCase = ((MyTicTacToeController) ticTacToeG).getColButton((Button) caseLetter);
                    Point posCase = new Point(rowCase, colCase);

                    int rowTicTacToe = getRowAnchor((AnchorPane) ticTacToeG);
                    int colTicTacToe = getColAnchor((AnchorPane) ticTacToeG);

                    Point posTicTacToe = new Point(rowTicTacToe, colTicTacToe);
                    try {

                        game.play(posTicTacToe, posCase);
                        ((MyTicTacToeController) ticTacToeG).putLetter(game.getCurrentPlayer().getRole().getLetter(), (Button) caseLetter);

                        if (!(game.getUltimateTicTacToe().getWinnerAt(posTicTacToe).getLetter().equals(Letters.NONE.getLetter()))) {
                            ((MyTicTacToeController) ticTacToeG).finish(game.getUltimateTicTacToe().getWinnerAt(posTicTacToe).getLetter());
                        }
                        if (game.gameOver()) {
                            endGame();
                            game.addToHistory();
                            displayEndGame(game.getWinner());
                        } else {
                            removeGoodPosition();
                            removeDisplayCurrentPlayer();
                            game.nextPlayer();
                            if (game.isUsedJoker()) {
                                btnJoker.setDisable(true);
                            } else {
                                btnJoker.setDisable(false);
                            }
                            displayCurrentPlayer(game.getCurrentPlayer().getName());
                            displayGoodPosition(game.getLastMove());
                        }

                    } catch (GameException e) {
                        Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText(e.getMessage());
                        errorAlert.showAndWait();

                        System.out.println(e.getMessage());
                    } catch (IOException ex) {
                        Logger.getLogger(UltimateTicTacToeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            });
        });
    }

    public void displayGoodPosition(Point position) {
        if (position.getX() == -1 && position.getY() == -1) {
            ultimateTicTacToe.getChildren().forEach((node) -> {
                ((MyTicTacToeController) node).displayGoodPosition();
            });
        } else {
            ((MyTicTacToeController) ultimateTicTacToe.getChildren().get((position.getX() * 3) + position.getY())).displayGoodPosition();
        }
    }

    public void removeGoodPosition() {
        ultimateTicTacToe.getChildren().forEach((node) -> {
            ((MyTicTacToeController) node).removeGoodPosition();
        });
    }

    public int getRowAnchor(AnchorPane anchorPane) {
        return (GridPane.getRowIndex(anchorPane) == null) ? 0 : GridPane.getRowIndex(anchorPane);
    }

    public int getColAnchor(AnchorPane anchorPane) {
        return (GridPane.getColumnIndex(anchorPane) == null) ? 0 : GridPane.getColumnIndex(anchorPane);
    }

    private void displayEndGame(Player player) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("The winer is " + player.getName());
        alert.setContentText("Choose your option.");

        ButtonType buttonRestart = new ButtonType("Restart");
        ButtonType buttonNewGame = new ButtonType("New game");
        ButtonType buttonExit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(buttonRestart, buttonNewGame, buttonExit);
        PlayerDto winner = PlayerFacade.findPlayerByPseudo(game.getWinner().getName());
        System.out.println("winner" + winner.getPseudo());
        PlayerDto looser = PlayerFacade.findPlayerByPseudo(game.getLooser().getName());
        System.out.println("looser" + looser.getPseudo());
        PlayerFacade.updatePlayer(new PlayerDto(winner.getId(), winner.getPseudo(), winner.getNbWins() + 1, winner.getNbLoses(), winner.getNbDraws(),(game.getWinner().hasBeenUsedJoker())?winner.getNbUsedJoker()+1 : winner.getNbUsedJoker()));
        PlayerFacade.updatePlayer(new PlayerDto(looser.getId(), looser.getPseudo(), looser.getNbWins(), looser.getNbLoses() + 1, looser.getNbDraws(),(game.getLooser().hasBeenUsedJoker())?looser.getNbUsedJoker()+1 : looser.getNbUsedJoker()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonRestart) {
            restart();
        } else if (result.get() == buttonNewGame) {
            newGame();
        } else if (result.get() == buttonExit) {
            System.exit(0);
        }
    }

    private void displayPlayersNames(String firstName, String secondName) {
        this.firstPlayer.setText(firstName);
        this.secondPlayer.setText(secondName);

    }

    public void displayCurrentPlayer(String name) {
        if (firstPlayer.getText().equals(name)) {
            firstPlayer.setStyle("-fx-background-color :  red; -fx-background-radius :  15px; -fx-text-fill :  white;");
        } else {
            secondPlayer.setStyle("-fx-background-color :  #0090ff; -fx-background-radius :  15px; -fx-text-fill :  white;");
        }
    }

    public void removeDisplayCurrentPlayer() {
        firstPlayer.setStyle("-fx-text-fill :  red;");
        secondPlayer.setStyle("-fx-text-fill :  #0090ff;");
    }

    @FXML
    private void useJoker() {
        if (!game.isUsedJoker()) {
            game.useJoker();
            btnJoker.setDisable(true);
        }

    }

}
