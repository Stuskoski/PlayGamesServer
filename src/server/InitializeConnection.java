package server;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by augustus on 4/13/16.
 */
public class InitializeConnection implements Runnable{
    public int portNum;
    public int currentPlayers;

    public InitializeConnection(int portNum){
        this.portNum = portNum;
    }

    @Override
    public void run() {
        int maxPlayers = 2;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNum);
        } catch (IOException e) {
            System.out.println("Error with socket");
        }

        for(currentPlayers = 0; currentPlayers < maxPlayers; currentPlayers++){
            try {
                Socket socket = serverSocket.accept();
                ListOfSockets.socketList.add(socket);
                System.out.println("Connection accepted");
                Platform.runLater(() -> GameLobby.addPlayerToConnectionList(socket.getInetAddress().toString()));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Finished.  Max number of players connected.");
        ListOfSockets.createReadersAndWriters();
    }
}
