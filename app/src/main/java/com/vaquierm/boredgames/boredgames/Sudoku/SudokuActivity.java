package com.vaquierm.boredgames.boredgames.Sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.vaquierm.boredgames.boredgames.R;

/**
 * Created by Michael Vaquier on 2017-08-11.
 */

public class SudokuActivity extends AppCompatActivity {

    private final static String TAG = SudokuActivity.class.getName();

    private static SudokuActivity ourInstance;

    private ImageButton playSudokuButton;
    private ImageButton sudokuSolverButton;

    private FrameLayout menuFrame;
    private FragmentSudokuSolver fragmentSudokuSolver;
    private FragmentSudokuPlay fragmentSudokuPlay;

    public static SudokuActivity getInstance() { return ourInstance; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "SudokuActivity Created");
        super.onCreate(savedInstanceState);
        ourInstance = this;
        setContentView(R.layout.sudoku_activity);

        setUpView();
    }

    private void setUpView() {
        playSudokuButton = (ImageButton) findViewById(R.id.play_sudoku_button);
        playSudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "play sudoku CLICK");
                sudokuPlaySelected();
            }
        });

        sudokuSolverButton = (ImageButton) findViewById(R.id.sudoku_solver_button);
        sudokuSolverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "sudoku solver CLICK");
                sudokuSolverSelected();
            }
        });

        menuFrame = (FrameLayout) findViewById(R.id.sudoku_container);
        fragmentSudokuSolver = new FragmentSudokuSolver();
        fragmentSudokuPlay = new FragmentSudokuPlay();
    }

    private void sudokuSolverSelected() {
        getFragmentManager().beginTransaction().replace(R.id.sudoku_container, fragmentSudokuSolver).commit();

        playSudokuButton.setVisibility(View.GONE);
        sudokuSolverButton.setVisibility(View.GONE);
    }

    private void sudokuPlaySelected() {
        getFragmentManager().beginTransaction().replace(R.id.sudoku_container, fragmentSudokuPlay).commit();

        playSudokuButton.setVisibility(View.GONE);
        sudokuSolverButton.setVisibility(View.GONE);
    }
}
