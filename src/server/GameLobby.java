package server;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by augustus on 4/13/16.
 * Lobby Screen
 */
public class GameLobby {
    public static Stage stage = new Stage();
    public static void showLobby(){
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 250, 450);

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        VBox connectedPlayers = new VBox();
        connectedPlayers.setPrefHeight(300);

        HBox hBox = new HBox(new Label("Connected Players"));
        hBox.setAlignment(Pos.CENTER);

        gridPane.add(hBox,0,0);
        gridPane.add(connectedPlayers, 0, 1);

        ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPlaceholder(new Label("Select A Game"));

        Label ticTac = new Label("Tic Tac Toe");
        Label connect4 = new Label("Connect 4");
        ticTac.setStyle("-fx-text-fill: black;");
        connect4.setStyle("-fx-text-fill: black;");

        comboBox.getItems().addAll(ticTac, connect4);

        gridPane.add(comboBox, 0, 3);
        Button startGame = new Button("Start Game");

        HBox hBox1 = new HBox(startGame);
        hBox1.setAlignment(Pos.CENTER);

        gridPane.add(hBox1, 0, 4);


        stage.setScene(scene);

        stage.setTitle("Game Lobby");

        stage.show();
    }
}
