package com.vaquierm.boredgames.boredgames.TicTacToe;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vaquierm.boredgames.boredgames.R;

/**
 * Created by Michael Vaquier on 2017-08-13.
 */

public class FragmentTicTacToe extends Fragment {

    private final static String TAG = FragmentTicTacToe.class.getName();

    private TictactoeActionListener tictactoeActionListener;

    private ImageButton newGameButton;

    private ImageButton[][] board;

    private TextView message;

    public FragmentTicTacToe() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "FragmentTictactoe Created");
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_tictactoe, container, false);

        setUpView(view);

        return view;
    }

    private void setUpView(View view) {
        newGameButton = (ImageButton) view.findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "new game CLICK");
                startNewGame();
            }
        });

        board = TictactoeUtil.getImageButtonsBoard(view, this);
        message = (TextView) view.findViewById(R.id.tictactoe_message);
    }

    private void startNewGame() {
        Log.d(TAG, "entering .startNewGame()");
        tictactoeActionListener.onStartNewGame();
    }

    public void tictactoeEnryClicked(int i, int j) {
        Log.d(TAG, "entering .sudokuEntryClicked(" + i + ", " + j + ")");
        tictactoeActionListener.onTictactoeMove(i, j);
    }

    public void displayMessage(String m) {
        Log.d(TAG, "entering .displayMessage(" + m + ")");
        if (message != null)
            message.setText(m);
    }

    public ImageButton[][] getBoard() { return board; }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TictactoeActionListener) {
            tictactoeActionListener = (TictactoeActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameSelectionInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        tictactoeActionListener = null;
    }

    public interface TictactoeActionListener {
        void onStartNewGame();
        void onTictactoeMove(int i, int j);
    }


}
