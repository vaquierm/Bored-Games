package com.vaquierm.boredgames.boredgames;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Michael Vaquier on 2017-08-11.
 */

public class FragmentMainMenu extends Fragment {

    private final static String TAG = FragmentMainMenu.class.getName();

    private GameSelectionInteractionListener gameSelectionInteractionListener;

    ImageButton sudokuButton;
    ImageButton tictactoeButton;

    public FragmentMainMenu() {
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "FragmentMainMenu created");

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        setUpView(view);

        return view;
    }

    private void setUpView(View view) {

        sudokuButton = (ImageButton) view.findViewById(R.id.sudoku_button);

        sudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Sudoku CLICK");
                startSudoku();
            }
        });

        tictactoeButton = (ImageButton) view.findViewById(R.id.tictactoe_button);

        tictactoeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe CLICK");
                startTictactoe();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameSelectionInteractionListener) {
            gameSelectionInteractionListener = (GameSelectionInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameSelectionInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        gameSelectionInteractionListener = null;
    }


    public void startSudoku() {
        gameSelectionInteractionListener.onSudokuSelected();
    }

    public void startTictactoe() {
        gameSelectionInteractionListener.onTictactoeSelected();
    }

    public interface GameSelectionInteractionListener {
        void onSudokuSelected();
        void onTictactoeSelected();
    }
}
