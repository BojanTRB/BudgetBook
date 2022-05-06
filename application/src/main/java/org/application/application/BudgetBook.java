package org.application.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.application.util.FileHandler;

import java.net.URL;

public class BudgetBook extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        
        scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Budget Book");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        FileHandler fh = new FileHandler();
        fh.writeTransactions("Withdraw", "Amazon", 70);
    }

    public static void main(String[] args) {
        launch();
    }
}