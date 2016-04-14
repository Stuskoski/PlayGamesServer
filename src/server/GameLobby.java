package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.TicTacToe.TicTacToe;
import server.TicTacToe.TicTacToeGameReference;

import java.util.Random;

/**
 * Created by augustus on 4/13/16.
 * Lobby Screen
 */
public class GameLobby {
    public static String game = "";
    public static Stage stage = new Stage();
    public static VBox connectedPlayers = new VBox();

    public static void showLobby(){
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 200, 250);

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //VBox connectedPlayers = new VBox();
        connectedPlayers.setPrefHeight(300);

        Label label = new Label("Connected Players");
        label.setStyle("-fx-font-weight: bolder; -fx-font-size: 15;");
        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.CENTER);

        gridPane.add(hBox,0,0);
        gridPane.add(connectedPlayers, 0, 1);

        ComboBox<Label> comboBox = new ComboBox<>();

        Label ticTac = new Label("Tic Tac Toe");
        Label connect4 = new Label("Connect 4");
        ticTac.setStyle("-fx-text-fill: black;");
        connect4.setStyle("-fx-text-fill: black;");

        comboBox.getItems().addAll(ticTac, connect4);
        comboBox.setPromptText("Select Game");
        comboBox.setPrefWidth(150);

        gridPane.add(comboBox, 0, 3);
        Button startGame = new Button("Start Game");

        HBox hBox1 = new HBox(startGame);
        hBox1.setAlignment(Pos.CENTER);

        gridPane.add(hBox1, 0, 4);

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.setScene(scene);

        stage.setTitle("Game Lobby");

        stage.show();

        startGame.setOnAction(event -> {
            if(comboBox.getValue() != null && ListOfSockets.socketList.size() == 2){
                switch (comboBox.getValue().getText()){
                    case "Tic Tac Toe": {
                        game = "Tic Tac Toe";
                        ListOfSockets.writeToBothClients("Start game - Tic Tac Toe");
                        Random random = new Random();
                        int xORy = random.nextInt(2);
                        if (xORy == 1) {
                            ListOfSockets.writeToClient1("Tic Tac Toe - Player X");
                            ListOfSockets.writeToClient2("Tic Tac Toe - Player O");
                            TicTacToeGameReference.ticTacToe = new TicTacToe("Client2", "Client1");
                        } else {
                            ListOfSockets.writeToClient1("Tic Tac Toe - Player O");
                            ListOfSockets.writeToClient2("Tic Tac Toe - Player X");
                            TicTacToeGameReference.ticTacToe = new TicTacToe("Client1", "Client2");
                        }
                        break;
                    }
                    case "Connect 4": {
                        game = "Connect 4";
                        ListOfSockets.writeToBothClients("Start game - Connect 4");
                        Random random = new Random();
                        int xORy = random.nextInt(2);
                        if (xORy == 1) {
                            ListOfSockets.writeToClient1("Connect 4 - Player Black");
                            ListOfSockets.writeToClient2("Connect 4 - Player Red");
                        } else {
                            ListOfSockets.writeToClient1("Connect 4 - Player Red");
                            ListOfSockets.writeToClient2("Connect 4 - Player Black");
                        }
                        break;
                    }
                }
            }
        });
    }

    public static void addPlayerToConnectionList(String str){

        if(connectedPlayers.getChildren().size() == 0) {
            Label label = new Label("Client 1: " + str);
            label.setStyle("-fx-font-weight: bold;");
            connectedPlayers.getChildren().add(label);
        }else {
            Label label = new Label("Client 2: " + str);
            label.setStyle("-fx-font-weight: bold;");
            connectedPlayers.getChildren().add(label);
        }
    }
}
