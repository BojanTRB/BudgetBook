package org.application.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.application.util.FileHandler;

public class BudgetBook extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        scene.setFill(Color.web("#262626"));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Budget Book");
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        FileHandler fh = new FileHandler();
        fh.writeTransactions("Withdraw", "Netflix", 10.5);
    }

    public static void main(String[] args) {
        launch();
    }
}