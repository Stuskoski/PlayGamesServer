package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by augustus on 4/13/16.
 * Creating communcation channels
 */
public class ListOfSockets {
    public static ArrayList<Socket> socketList = new ArrayList<>();
    public static BufferedReader client1In;
    public static BufferedReader client2In;
    public static PrintWriter client1Out;
    public static PrintWriter client2Out;

    //Create the streams from the two sockets
    public static void createReadersAndWriters(){
        if(socketList.size() == 2){//max players
            try {
                client1In = new BufferedReader(new InputStreamReader(socketList.get(0).getInputStream()));
                client2In = new BufferedReader(new InputStreamReader(socketList.get(1).getInputStream()));
                client1Out = new PrintWriter(socketList.get(0).getOutputStream(), true);
                client2Out = new PrintWriter(socketList.get(1).getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Unable to create output/input streams for clients");
            }


            try {
                startTheReaderFor1();
                startTheReaderFor2();
            } catch (IOException e) {
                System.out.println("Unable to start the readers for 1 and 2");
                System.exit(-1);
            }

        }else{
            System.out.println("Not enough clients");
        }
    }


    private static void startTheReaderFor1() throws IOException {
        Thread thread = new Thread(new Reader1FromSocket(client1In));
        thread.start();
    }

    private static void startTheReaderFor2() throws IOException {
        Thread thread = new Thread(new Reader2FromSocket(client2In));
        thread.start();
    }

    public static void writeToClient1(String str){
        client1Out.println(str);
    }

    public static void writeToClient2(String str){
        client2Out.println(str);
    }

    public static void writeToBothClients(String str){
        client1Out.println(str);
        client2Out.println(str);
    }

}
