package com.vaquierm.boredgames.boredgames.TicTacToe;

import android.util.Log;

import java.util.List;

/**
 * Created by Michael Vaquier on 2017-08-15.
 */

public class TictactoeBot {

    private final static String TAG = TictactoeBot.class.getName();

    private static final char robot = 'O';

    private static final char player = 'X';



    public static void play(TictactoeGame game) {
        Log.d(TAG, "entering bot play()");
        int[] move = findBestMove(game.getBoard());
        game.playMove(move[0], move[1]);
    }

    public static void firstMovePlay(TictactoeGame game) {
        Log.d(TAG, "entering bot firstMovePlay()");
        double random = Math.random();
        int[] move = new int[2];
        if (random < 0.6) {
            move[0] = 1;
            move[1] = 1;
        }
        else {
            if (random < 0.7) {
                move[0] = 0;
                move[1] = 0;
            }
            else if (random < 0.8) {
                move[0] = 0;
                move[1] = 2;
            }
            else if (random < 0.9) {
                move[0] = 2;
                move[1] = 0;
            }
            else {
                move[0] = 2;
                move[1] = 2;
            }
        }
        game.playMove(move[0], move[1]);
    }

    private static int evaluate(char[][] b) {

        for (int i = 0 ; i < b.length ; i++)
        {
            if (b[i][0] == b[i][1] && b[i][1] == b[i][2]) {
                if (b[i][0] == robot)
                    return +10;
                else if (b[i][0] == player)
                    return -10;
            }
        }

        for (int j = 0 ; j < b.length ; j++) {
            if (b[0][j] == b[1][j] && b[1][j]==b[2][j]) {
                if (b[0][j] == robot)
                    return +10;

                else if (b[0][j] == player)
                    return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == robot)
                return +10;
            else if (b[0][0] == player)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == robot)
                return +10;
            else if (b[0][2] == player)
                return -10;
        }


        return 0;
    }


    private static int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (!isMovesLeft(board))
            return 0;

        // If this robot's move
        if (isMax) {
            int best = -1000;

            for (int i = 0 ; i < board.length ; i++)
            {
                for (int j = 0 ; j < board[i].length ; j++)
                {
                    if (board[i][j]==' ')
                    {
                        board[i][j] = robot;

                        best = Math.max( best, minimax(board, depth+1, !isMax) );

                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // If this player's move
        else {
            int best = 1000;

            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    if (board[i][j]==' ')
                    {
                        board[i][j] = player;

                        best = Math.min(best, minimax(board, depth+1, !isMax));

                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    private static int[] findBestMove(char[][] board) {
        Log.d(TAG, "entering .findBestMove()");
        int bestVal = -1000;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;

        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[i].length ; j++) {
                // Check if celll is empty
                if (board[i][j]==' ') {
                    // Make the move
                    board[i][j] = robot;

                    // compute evaluation function for this move
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = ' ';

                    // If the value of the current move is more than the best value, then update best
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        Log.d(TAG, "The best move is [" + bestMove[0] + "][" + bestMove[1] + " with value : " + bestVal);

        return bestMove;
    }

    private static boolean isMovesLeft(char[][] board) {
        for(int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[i].length ; j++) {
                if (board[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }

}
