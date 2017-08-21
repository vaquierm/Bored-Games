package com.vaquierm.boredgames.boredgames.TicTacToe;

import android.util.Log;

/**
 * Created by Michael Vaquier on 2017-08-14.
 */

public class TictactoeGame {

    private final static String TAG = TictactoeGame.class.getName();

    private Player currentPlayer;

    private GameState gameState;

    private Player winner;

    private char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };

    public enum Player { X, O }

    public enum GameState { PLAYING, WON, TIED }

    public TictactoeGame(Player startingPlayer) {
        Log.d(TAG, "new Tictactoe game created with starting player " + startingPlayer);
        gameState = GameState.PLAYING;
        currentPlayer = startingPlayer;
    }

    //returns true if move was successful and false otherwise
    public boolean playMove(int i, int j) {
        Log.d(TAG, "entering .playMove(" + i + ", " + j +")");
        switch (gameState) {
            case PLAYING:
                if (board[i][j] == ' ') {
                    switch (currentPlayer) {
                        case O:
                            Log.d(TAG, "placing 'O' on entry [" + i + "][" + j + "]");
                            board[i][j] = 'O';
                            currentPlayer = Player.X;
                            break;
                        case X:
                            Log.d(TAG, "placing 'X' on entry [" + i + "][" + j + "]");
                            board[i][j] = 'X';
                            currentPlayer = Player.O;
                            break;
                        default:
                            break;
                    }
                    checkGameState();
                    return true;
                }
                else {
                    Log.d(TAG, "a piece has already been placed on entry [" + i + "][" + j + "]");
                }
                break;
            case WON:
                Log.d(TAG, "the game has already been won");
                break;
            case TIED:
                Log.d(TAG, "the game has ended in a tie");
                break;
            default:
                break;
        }
        return false;
    }

    private void checkGameState() {
        Log.d(TAG, "entering .checkGameWon()");

        char vertical = checkVertical();

        char horizontal = checkHorizontal();

        char diagonal = checkDiagonal();

        if (vertical == 'X' || horizontal == 'X' || diagonal == 'X') {
            gameState = GameState.WON;
            winner = Player.X;
        }
        else if (vertical == 'O' || horizontal == 'O' || diagonal == 'O') {
            gameState = GameState.WON;
            winner = Player.O;
        }

        if (gameState != GameState.WON) {
            checkTie();
        }
    }

    private char checkHorizontal() {
        char out = ' ';
        for (int i = 0 ; i < board.length ; i++) {
            char temp = board[i][0];
            if(temp == ' ')
                continue;
            for (int j = 1 ; j < board[i].length ; j++) {
                if (temp != board[i][j]) {
                    break;
                }
                else if (temp == board[i][j] && j == board[i].length - 1) {
                    out = temp;
                    return out;
                }
            }
        }
        return out;
    }

    private char checkVertical() {
        char out = ' ';
        for (int j = 0 ; j < board.length ; j++) {
            char temp = board[0][j];
            if (temp == ' ')
                continue;
            for (int i = 1 ; i < board[i].length ; i++) {
                if (temp != board[i][j]) {
                    break;
                }
                else if (temp == board[i][j] && i == board[j].length - 1) {
                    out = temp;
                    return out;
                }
            }
        }
        return out;
    }

    private char checkDiagonal() {
        char out = ' ';
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            out = board[1][1];
        }
        else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            out = board[1][1];
        }
        return out;
    }

    private boolean checkTie() {
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        gameState = GameState.TIED;
        return true;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public char[][] getBoard() { return board; }

}
