package atlg4.composant.g45682;

import atlg4.composant.g45682.controller.MyTicTacToeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author g45682
 */
public class MyTicTacToe extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
            BorderPane root = new BorderPane();
            
            MyTicTacToeController myTicTacToe = new MyTicTacToeController();
            root.setCenter(myTicTacToe);
            Scene scene = new Scene(root);
            

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("TicTacToe");
            stage.getIcons().add(new Image("images/tictactoe.jpg"));
            stage.show();
        }catch(Exception e){
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
