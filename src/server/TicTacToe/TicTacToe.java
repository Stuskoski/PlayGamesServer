package server.TicTacToe;

import server.ListOfSockets;

/**
 * Created by augustus on 4/13/16.
 */
public class TicTacToe {
    public String playerX;
    public String playerO;
    public char[] row1, row2, row3;
    public char[][] board;

    public TicTacToe(String playerX, String playerO){
        this.playerO = playerO;
        this.playerX = playerX;
    }

    public void startNewGame(){
        board  = new char[][]{
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        row1 = new char[]{'-', '-', '-'};
        row2 = new char[]{'-', '-', '-'};
        row3 = new char[]{'-', '-', '-'};
    }

    /**
     * Return true if spot is available
     * false if taken
     * @return
     */
    public boolean checkSpot(int row, int col){
        return !(board[row][col] == 'X' || board[row][col] == 'O');
    }

    public void addToSpot(int row, int col, char xORo){
        if(checkSpot(row, col)){
            if(xORo == 'O') {
                board[row][col] = xORo;
                switch (playerO) {
                    case "Client1": {
                        ListOfSockets.writeToClient1("Tic Tac Toe - Valid Spot");
                        break;
                    }
                    case "Client2": {
                        ListOfSockets.writeToClient2("Tic Tac Toe - Valid Spot");
                        break;

                    }
                }
            }
            if(xORo == 'X'){
                board[row][col] = xORo;
                switch (playerX){
                    case "Client1":{
                        ListOfSockets.writeToClient1("Tic Tac Toe - Valid Spot");
                        break;
                    }
                    case "Client2":{
                        ListOfSockets.writeToClient2("Tic Tac Toe - Valid Spot");
                        break;
                    }
                }
            }
        }else{

        }
        checkForWin(xORo);
    }

    private boolean checkForWin(char xORo) {
        switch (xORo){
            case 'X': {
                break;
            }
            case 'O':{
                break;
            }
        }
        return false;
    }
}
