package server;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by augustus on 4/13/16.
 */
public class Reader2FromSocket implements Runnable{
    BufferedReader client2In;

    public Reader2FromSocket(BufferedReader client){
        this.client2In = client;
    }

    @Override
    public void run() {
        String temp;
        try {
            while((temp = client2In.readLine()) != null){
                switch (GameLobby.game){
                    case "Tic Tac Toe": {

                        break;
                    }
                    case "Connect 4":{
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to start reader for client 2");
            System.exit(-1);
        }
    }
}
