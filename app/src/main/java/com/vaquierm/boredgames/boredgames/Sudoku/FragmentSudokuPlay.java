package com.vaquierm.boredgames.boredgames.Sudoku;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vaquierm.boredgames.boredgames.R;
import com.vaquierm.boredgames.boredgames.TicTacToe.TictactoeActivity;


/**
 * Created by Michael Vaquier on 2017-08-12.
 */

public class FragmentSudokuPlay extends Fragment {

    private final static String TAG = FragmentSudokuPlay.class.getName();

    private ImageButton easyButton;
    private ImageButton mediumButton;
    private ImageButton hardButton;

    private TextView message;

    private EditText[][] board;

    public FragmentSudokuPlay() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "FragmentSudokuSolver Created");
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_sudoku_play, container, false);

        setUpView(view);

        return view;
    }

    private void setUpView(View view) {
        board = SudokuUtil.getBoardEntries(view);

        easyButton = (ImageButton) view.findViewById(R.id.easy_button);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "easy CLICK");
                confirmNewGame(SudokuGenerator.Difficulty.EASY);
            }
        });

        mediumButton = (ImageButton) view.findViewById(R.id.medium_button);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"medium CLICK");
                confirmNewGame(SudokuGenerator.Difficulty.MEDIUM);
            }
        });

        hardButton = (ImageButton) view.findViewById(R.id.hard_button);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"hard CLICK");
                confirmNewGame(SudokuGenerator.Difficulty.HARD);
            }
        });
        message = (TextView) view.findViewById(R.id.sudoku_message);
    }


    public void displayMessage(String m) {
        Log.d(TAG, "entering .displayMessage(" + m + ")");
        if (message != null)
            message.setText(m);
    }

    private void confirmNewGame(final SudokuGenerator.Difficulty difficulty) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.d(TAG, "confirm new game of difficulty: " + difficulty);
                        generateNewGame(difficulty);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.d(TAG, "cancel new game of difficulty: " + difficulty);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(SudokuActivity.getInstance());
        builder.setMessage("Would you like to start a new game with difficulty: " + difficulty + " ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener).show();
    }


    private void generateNewGame(SudokuGenerator.Difficulty difficulty) {
        Log.d(TAG, "generating new game of difficulty: " + difficulty);
        Sudoku generatedGame = SudokuGenerator.generateSudokuPuzzle(difficulty);
        if(board != null) {
            clearBoard();
            SudokuUtil.generatedGameToEditText(generatedGame, board, message);
            displayMessage("");
        }
        else {
            Log.d(TAG, "the board was not set up properly...");
        }
    }

    private void clearBoard() {
        Log.d(TAG, "entering .clearBoard()");
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                board[i][j].setText("");
                board[i][j].setOnTouchListener(null);
            }
        }
    }

}
