package server;

import server.TicTacToe.TicTacToe;
import server.TicTacToe.TicTacToeGameReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by augustus on 4/13/16.
 */
public class Reader1FromSocket implements Runnable {
    BufferedReader client1In;

    public Reader1FromSocket(BufferedReader client){
        this.client1In = client;
    }

    @Override
    public void run() {
        String temp;
        try {
            while((temp = client1In.readLine()) != null){
                if(!temp.contains("Tic Tac Toe")){
                    ListOfSockets.writeToClient2(temp);
                }

                switch (GameLobby.game){
                    case "Tic Tac Toe": {
                        String[] nums = temp.split("\\s+");
                        TicTacToeGameReference.ticTacToe.addToSpot(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), nums[2].charAt(0)); // col row char
                        switch (nums[2].charAt(0)){
                            case 'O':{
                                ListOfSockets.writeToBothClients(nums[0] + " " + nums[1] + " blue");
                                break;
                            }
                            case 'X':{
                                ListOfSockets.writeToBothClients(nums[0] + " " + nums[1] + " red");
                                break;
                            }
                        }
                        break;
                    }
                    case "Connect 4":{
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to start reader for client 1");
            System.exit(-1);
        }
    }
}
