package server;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static double x, y;
    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox vBox = new VBox(5);
        primaryStage.setTitle("Game Server");
        primaryStage.setScene(new Scene(vBox, 300, 275));

        HBox hBox = new HBox(new Label("Enter Port: "));
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(hBox);

        TextField portNum = new TextField();
        portNum.setPromptText("Enter Port Number");
        portNum.setMaxWidth(150);
        vBox.getChildren().add(portNum);

        vBox.setAlignment(Pos.CENTER);

        Button start = new Button("Start Server");

        vBox.getChildren().add(start);

        primaryStage.setOnCloseRequest(event2 -> System.exit(0));

        portNum.setOnKeyPressed(event1 -> {
            switch (event1.getCode()){
                case ENTER:
                    start.fire();
                    break;
            }
        });

        start.setOnAction(event -> {
            if(!portNum.getText().equals("") && portNum.getText() != null) {
                if (Integer.parseInt(portNum.getText().replaceAll("[^0-9]","")) > 1025 && Integer.parseInt(portNum.getText().replaceAll("[^0-9]","")) < 65535) {
                    x = primaryStage.getX();
                    y = primaryStage.getY();
                    GameLobby.showLobby();
                    InitializeConnection init = new InitializeConnection(Integer.parseInt(portNum.getText().replaceAll("[^0-9]","")));
                    Thread thread = new Thread(init);
                    thread.start();
                    primaryStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Port Number!\nMust be between 1026 and 65534");

                    alert.showAndWait();

                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Port Number!\nMust be between 1026 and 65534");

                alert.showAndWait();

            }
        });


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
