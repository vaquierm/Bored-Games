package com.vaquierm.boredgames.boredgames.Sudoku;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vaquierm.boredgames.boredgames.R;


/**
 * Created by Michael Vaquier on 2017-08-12.
 */

public class FragmentSudokuSolver extends Fragment {

    private final static String TAG = FragmentSudokuSolver.class.getName();

    private ImageButton solveButton;
    private ImageButton clearButton;

    private TextView message;

    private EditText[][] board;

    public FragmentSudokuSolver() {

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

        View view = inflater.inflate(R.layout.fragment_sudoku_solver, container, false);

        setUpView(view);

        return view;
    }

    private void setUpView(View view) {
        board = SudokuUtil.getBoardEntries(view);

        solveButton = (ImageButton) view.findViewById(R.id.solve_button);
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "solve CLICK");
                attemptSolve();
            }
        });

        clearButton = (ImageButton) view.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clear CLICK");
                clearBoard();
            }
        });
        message = (TextView) view.findViewById(R.id.sudoku_message);
    }

    private void attemptSolve() {
        Log.d(TAG, "entering .attemptSolve()");
        displayMessage("");
        int[][] intBoard = SudokuUtil.editTextToInt(board);
        if(intBoard == null) {
            Log.d(TAG, "invalid board");
        }
        else {
            Sudoku attemptSudoku = new Sudoku(intBoard);
            if(attemptSudoku.isValidState()) {
                boolean isEmpty = attemptSudoku.isEmpty();
                Log.d(TAG, "solving sudoku");
                attemptSudoku.solve(1, 0);
                Log.d(TAG, "sudoku is in a solved state:  " + attemptSudoku.isSolved());
                if(isEmpty) {
                    SudokuGenerator.shuffleBoard(attemptSudoku);
                }
                if (SudokuUtil.solutionToEditText(attemptSudoku, board)) {
                    Log.d(TAG, "sudoku solved and displayed");
                } else {
                    Log.d(TAG, "sudoku solved but display problem");
                }
            }
            else {
                Log.d(TAG, "sudoku invalid starting state");
                displayMessage("The Sudoku you are trying to solve is invalid...");
            }
        }

    }

    public void displayMessage(String m) {
        Log.d(TAG, "entering .displayMessage(" + m + ")");
        if (message != null)
            message.setText(m);
    }

    private void clearBoard() {
        Log.d(TAG, "entering .clearBoard()");
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                board[i][j].setText("");
            }
        }
    }

}
