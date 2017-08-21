package com.vaquierm.boredgames.boredgames.TicTacToe;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.vaquierm.boredgames.boredgames.R;

/**
 * Created by Michael Vaquier on 2017-08-13.
 */

public class TictactoeUtil {

    private final static String TAG = TictactoeUtil.class.getName();

    public static ImageButton[][] getImageButtonsBoard(View view, final FragmentTicTacToe fragment) {
        Log.d(TAG, "entering getImageButtonsBoard()");

        ImageButton[][] out = new ImageButton[3][3];

        out[0][0] = (ImageButton) view.findViewById(R.id.tictactoe_entry_0_0);
        out[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [0][0] CLICK");
                fragment.tictactoeEnryClicked(0,0);
            }
        });
        out[0][1] = (ImageButton) view.findViewById(R.id.tictactoe_entry_0_1);
        out[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [0][1] CLICK");
                fragment.tictactoeEnryClicked(0,1);
            }
        });
        out[0][2] = (ImageButton) view.findViewById(R.id.tictactoe_entry_0_2);
        out[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [0][2] CLICK");
                fragment.tictactoeEnryClicked(0,2);
            }
        });
        out[1][0] = (ImageButton) view.findViewById(R.id.tictactoe_entry_1_0);
        out[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [1][0] CLICK");
                fragment.tictactoeEnryClicked(1,0);
            }
        });
        out[1][1] = (ImageButton) view.findViewById(R.id.tictactoe_entry_1_1);
        out[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [1][1] CLICK");
                fragment.tictactoeEnryClicked(1,1);
            }
        });
        out[1][2] = (ImageButton) view.findViewById(R.id.tictactoe_entry_1_2);
        out[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [1][2] CLICK");
                fragment.tictactoeEnryClicked(1,2);
            }
        });
        out[2][0] = (ImageButton) view.findViewById(R.id.tictactoe_entry_2_0);
        out[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [2][0] CLICK");
                fragment.tictactoeEnryClicked(2,0);
            }
        });
        out[2][1] = (ImageButton) view.findViewById(R.id.tictactoe_entry_2_1);
        out[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [2][1] CLICK");
                fragment.tictactoeEnryClicked(2,1);
            }
        });
        out[2][2] = (ImageButton) view.findViewById(R.id.tictactoe_entry_2_2);
        out[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Tictactoe entry [2][2] CLICK");
                fragment.tictactoeEnryClicked(2,2);
            }
        });

        return out;
    }

    public static void setImmageViewsFromTictactoeBoard(ImageButton[][] boardView, char[][] board) {
        Log.d(TAG, "entering .setImmageViewsFromTictactoeBoard()");
        for(int i = 0 ; i < boardView.length ; i++) {
            for(int j = 0 ; j < boardView[i].length ; j++) {
                switch (board[i][j]) {
                    case 'X':
                        boardView[i][j].setImageResource(R.drawable.x_icon);
                        break;
                    case 'O':
                        boardView[i][j].setImageResource(R.drawable.o_icon);
                        break;
                    case ' ':
                        boardView[i][j].setImageResource(R.drawable.black_icon);
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
