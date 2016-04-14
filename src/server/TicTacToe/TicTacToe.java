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
    private int moves = 0;
    public boolean gameOver = false;

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
        System.out.println(board[0][0]);
    }

    /**
     * Return true if spot is available
     * false if taken
     * @return
     */
    public boolean checkSpot(int row, int col) {
        System.out.println("Row : " + row);
        System.out.println("Col : " + col);
        return board[row][col] != 'X' && board[row][col] != 'O';


    }

    public void addToSpot(int row, int col, char xORo){
        if(!gameOver) {
            if (checkSpot(row, col)) {

                moves++;
                if (moves >= 10) {
                    ListOfSockets.writeToBothClients("Tic Tac Toe - Draw");
                }
                if (xORo == 'O') {
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
                if (xORo == 'X') {
                    board[row][col] = xORo;
                    switch (playerX) {
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
            } else {
                ListOfSockets.writeToBothClients("Tic Tac Toe - Invalid Spot");
            }

            //If there is a winner let them know
            if (checkForWin(row, col, xORo)) {
                gameOver = true;
                if (xORo == 'O') {
                    ListOfSockets.writeToBothClients("Tic Tac Toe - O Wins");
                    ListOfSockets.writeToBothClients("Tic Tac Toe - Game Over");
                }
                if (xORo == 'X') {
                    ListOfSockets.writeToBothClients("Tic Tac Toe - X Wins");
                    ListOfSockets.writeToBothClients("Tic Tac Toe - Game Over");
                }
            }


            //Turn control
            if(xORo == 'X'){
                switch (playerX){
                    case "Client1":{
                        ListOfSockets.writeToClient1("Tic Tac Toe - Your turn");
                        break;
                    }
                    case "Client2":{
                        ListOfSockets.writeToClient2("Tic Tac Toe - Your turn");
                        break;
                    }
                }
            }
            if(xORo == 'O'){
                switch (playerO){
                    case "Client1":{
                        ListOfSockets.writeToClient1("Tic Tac Toe - Your turn");
                        break;
                    }
                    case "Client2":{
                        ListOfSockets.writeToClient2("Tic Tac Toe - Your turn");
                        break;
                    }
                }
            }

        }else {
            ListOfSockets.writeToBothClients("Tic Tac Toe - Game Over");
        }
    }

    private boolean checkForWin(int row, int col, char xORo) {
        System.out.println(board[0][0] + "," + board[0][1] + "," + board[0][2]);
        System.out.println(board[1][0] + "," + board[1][1] + "," + board[1][2]);
        System.out.println(board[2][0] + "," + board[2][1] + "," + board[2][2]);

        switch (xORo){
            case 'X': {

                //Check for col wins
                for(int colI = 0; colI <= 2; colI++){
                    int counter = 0;
                    for(int rowI = 0; rowI <= 2; rowI++){
                        if(board[colI][rowI] == 'X'){
                            counter++;
                        }
                        if(counter == 3)
                            return true;
                    }
                }

                //Check for row wins
                for(int rowI = 0; rowI<=2; rowI++){
                    int counter = 0;
                    for(int colI = 0; colI <= 2; colI++){
                        if(board[colI][rowI] == 'X'){
                            counter++;
                        }
                        if(counter == 3)
                            return true;
                    }
                }

                //Check for diag wins
                if(board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X') //col row
                    return true;

                //Check for reverse diag wins
                if(board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X')
                    return true;


                // 0,0 0,1 0,2
                // 1,0 1,1 1,2
                // 2,0 2,1 2,2
                break;
            }
            case 'O':{

                //Check for col wins
                for(int colI = 0; colI <= 2; colI++){
                    int counter = 0;
                    for(int rowI = 0; rowI <= 2; rowI++){
                        if(board[colI][rowI] == 'O'){
                            counter++;
                        }
                        if(counter == 3)
                            return true;
                    }
                }

                //Check for row wins
                for(int rowI = 0; rowI<=2; rowI++){
                    int counter = 0;
                    for(int colI = 0; colI <= 2; colI++){
                        if(board[colI][rowI] == 'O'){
                            counter++;
                        }
                        if(counter == 3)
                            return true;
                    }
                }

                //Check for diag wins
                if(board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O')
                    return true;

                //Check for reverse diag wins
                if(board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O')
                    return true;

                break;
            }
        }
        return false;
    }
}
