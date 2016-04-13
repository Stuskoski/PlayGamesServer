package server;

/**
 * Created by augustus on 4/13/16.
 */
public class InitializeConnection implements Runnable{
    public int portNum;

    public InitializeConnection(int portNum){
        this.portNum = portNum;
    }

    @Override
    public void run() {
        int maxPlayers = 2;
        int currentPlayers = 0;

        while(currentPlayers != 2){

        }
    }
}
