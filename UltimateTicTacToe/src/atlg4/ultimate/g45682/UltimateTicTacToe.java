package atlg4.ultimate.g45682;

import atlg4.ultimate.g45682.controller.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author g45682
 */
public class UltimateTicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            LoginController root = new LoginController(primaryStage);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("TicTacToe");
            primaryStage.getIcons().add(new Image("images/tictactoe.jpg"));
            primaryStage.show();
            System.out.println(primaryStage);
            

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
