package org.application.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.application.util.FileHandler;

import java.util.Optional;

public class BudgetBook extends Application {
    @Override
    public void init() throws Exception {
        FileHandler fh = new FileHandler();
        fh.writeTransactions("Withdraw", "Amazon", 70);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileHandler fh = new FileHandler();

        BorderPane root = new BorderPane();
        //Scene scene = new Scene(root);

        FXMLLoader fxmlLoader = new FXMLLoader(BudgetBook.class.getResource("/overview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        /* Stylesheet */
        scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);

        /* Icon */
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/Icon.png").toExternalForm()));
        primaryStage.setTitle("Budget Book");

        /* Set the configuration values */

        if(!fh.readConfig().containsKey("defaultFullScreen")) {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Do you want to have Default Full Screen?",
                    yes,
                    no);

            alert.setTitle("Configuration");
            alert.setHeaderText("Full Screen");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get().getButtonData().isCancelButton()) {
                // If set to no
                fh.writeConfig("defaultFullScreen", "false");
            } else {
                // If set to true or closed
                fh.writeConfig("defaultFullScreen" ,"true");
            }
        }


        /* Set full screen */
        if(fh.readConfig().containsKey("defaultFullScreen")) {
          String value = fh.readConfig().get("defaultFullScreen");
          if(value.equals("true")) {
              // If value is true
              primaryStage.setMaximized(true);
          } else {
              // If value is set to false
              primaryStage.setMaximized(false);
          }
        } else {
            // If no value exists
            primaryStage.setMaximized(true);
        }


        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}