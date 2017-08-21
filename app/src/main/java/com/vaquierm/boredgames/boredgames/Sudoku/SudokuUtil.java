package com.vaquierm.boredgames.boredgames.Sudoku;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vaquierm.boredgames.boredgames.R;

/**
 * Created by Michael Vaquier on 2017-08-12.
 */

public class SudokuUtil {

    public static EditText[][] getBoardEntries(View view) {
        EditText[][] out = new EditText[9][9];

        out[0][0] = (EditText) view.findViewById(R.id.sudoku_entry_0_0);
        out[0][1] = (EditText) view.findViewById(R.id.sudoku_entry_0_1);
        out[0][2] = (EditText) view.findViewById(R.id.sudoku_entry_0_2);
        out[0][3] = (EditText) view.findViewById(R.id.sudoku_entry_0_3);
        out[0][4] = (EditText) view.findViewById(R.id.sudoku_entry_0_4);
        out[0][5] = (EditText) view.findViewById(R.id.sudoku_entry_0_5);
        out[0][6] = (EditText) view.findViewById(R.id.sudoku_entry_0_6);
        out[0][7] = (EditText) view.findViewById(R.id.sudoku_entry_0_7);
        out[0][8] = (EditText) view.findViewById(R.id.sudoku_entry_0_8);
        out[1][0] = (EditText) view.findViewById(R.id.sudoku_entry_1_0);
        out[1][1] = (EditText) view.findViewById(R.id.sudoku_entry_1_1);
        out[1][2] = (EditText) view.findViewById(R.id.sudoku_entry_1_2);
        out[1][3] = (EditText) view.findViewById(R.id.sudoku_entry_1_3);
        out[1][4] = (EditText) view.findViewById(R.id.sudoku_entry_1_4);
        out[1][5] = (EditText) view.findViewById(R.id.sudoku_entry_1_5);
        out[1][6] = (EditText) view.findViewById(R.id.sudoku_entry_1_6);
        out[1][7] = (EditText) view.findViewById(R.id.sudoku_entry_1_7);
        out[1][8] = (EditText) view.findViewById(R.id.sudoku_entry_1_8);
        out[2][0] = (EditText) view.findViewById(R.id.sudoku_entry_2_0);
        out[2][1] = (EditText) view.findViewById(R.id.sudoku_entry_2_1);
        out[2][2] = (EditText) view.findViewById(R.id.sudoku_entry_2_2);
        out[2][3] = (EditText) view.findViewById(R.id.sudoku_entry_2_3);
        out[2][4] = (EditText) view.findViewById(R.id.sudoku_entry_2_4);
        out[2][5] = (EditText) view.findViewById(R.id.sudoku_entry_2_5);
        out[2][6] = (EditText) view.findViewById(R.id.sudoku_entry_2_6);
        out[2][7] = (EditText) view.findViewById(R.id.sudoku_entry_2_7);
        out[2][8] = (EditText) view.findViewById(R.id.sudoku_entry_2_8);
        out[3][0] = (EditText) view.findViewById(R.id.sudoku_entry_3_0);
        out[3][1] = (EditText) view.findViewById(R.id.sudoku_entry_3_1);
        out[3][2] = (EditText) view.findViewById(R.id.sudoku_entry_3_2);
        out[3][3] = (EditText) view.findViewById(R.id.sudoku_entry_3_3);
        out[3][4] = (EditText) view.findViewById(R.id.sudoku_entry_3_4);
        out[3][5] = (EditText) view.findViewById(R.id.sudoku_entry_3_5);
        out[3][6] = (EditText) view.findViewById(R.id.sudoku_entry_3_6);
        out[3][7] = (EditText) view.findViewById(R.id.sudoku_entry_3_7);
        out[3][8] = (EditText) view.findViewById(R.id.sudoku_entry_3_8);
        out[4][0] = (EditText) view.findViewById(R.id.sudoku_entry_4_0);
        out[4][1] = (EditText) view.findViewById(R.id.sudoku_entry_4_1);
        out[4][2] = (EditText) view.findViewById(R.id.sudoku_entry_4_2);
        out[4][3] = (EditText) view.findViewById(R.id.sudoku_entry_4_3);
        out[4][4] = (EditText) view.findViewById(R.id.sudoku_entry_4_4);
        out[4][5] = (EditText) view.findViewById(R.id.sudoku_entry_4_5);
        out[4][6] = (EditText) view.findViewById(R.id.sudoku_entry_4_6);
        out[4][7] = (EditText) view.findViewById(R.id.sudoku_entry_4_7);
        out[4][8] = (EditText) view.findViewById(R.id.sudoku_entry_4_8);
        out[5][0] = (EditText) view.findViewById(R.id.sudoku_entry_5_0);
        out[5][1] = (EditText) view.findViewById(R.id.sudoku_entry_5_1);
        out[5][2] = (EditText) view.findViewById(R.id.sudoku_entry_5_2);
        out[5][3] = (EditText) view.findViewById(R.id.sudoku_entry_5_3);
        out[5][4] = (EditText) view.findViewById(R.id.sudoku_entry_5_4);
        out[5][5] = (EditText) view.findViewById(R.id.sudoku_entry_5_5);
        out[5][6] = (EditText) view.findViewById(R.id.sudoku_entry_5_6);
        out[5][7] = (EditText) view.findViewById(R.id.sudoku_entry_5_7);
        out[5][8] = (EditText) view.findViewById(R.id.sudoku_entry_5_8);
        out[6][0] = (EditText) view.findViewById(R.id.sudoku_entry_6_0);
        out[6][1] = (EditText) view.findViewById(R.id.sudoku_entry_6_1);
        out[6][2] = (EditText) view.findViewById(R.id.sudoku_entry_6_2);
        out[6][3] = (EditText) view.findViewById(R.id.sudoku_entry_6_3);
        out[6][4] = (EditText) view.findViewById(R.id.sudoku_entry_6_4);
        out[6][5] = (EditText) view.findViewById(R.id.sudoku_entry_6_5);
        out[6][6] = (EditText) view.findViewById(R.id.sudoku_entry_6_6);
        out[6][7] = (EditText) view.findViewById(R.id.sudoku_entry_6_7);
        out[6][8] = (EditText) view.findViewById(R.id.sudoku_entry_6_8);
        out[7][0] = (EditText) view.findViewById(R.id.sudoku_entry_7_0);
        out[7][1] = (EditText) view.findViewById(R.id.sudoku_entry_7_1);
        out[7][2] = (EditText) view.findViewById(R.id.sudoku_entry_7_2);
        out[7][3] = (EditText) view.findViewById(R.id.sudoku_entry_7_3);
        out[7][4] = (EditText) view.findViewById(R.id.sudoku_entry_7_4);
        out[7][5] = (EditText) view.findViewById(R.id.sudoku_entry_7_5);
        out[7][6] = (EditText) view.findViewById(R.id.sudoku_entry_7_6);
        out[7][7] = (EditText) view.findViewById(R.id.sudoku_entry_7_7);
        out[7][8] = (EditText) view.findViewById(R.id.sudoku_entry_7_8);
        out[8][0] = (EditText) view.findViewById(R.id.sudoku_entry_8_0);
        out[8][1] = (EditText) view.findViewById(R.id.sudoku_entry_8_1);
        out[8][2] = (EditText) view.findViewById(R.id.sudoku_entry_8_2);
        out[8][3] = (EditText) view.findViewById(R.id.sudoku_entry_8_3);
        out[8][4] = (EditText) view.findViewById(R.id.sudoku_entry_8_4);
        out[8][5] = (EditText) view.findViewById(R.id.sudoku_entry_8_5);
        out[8][6] = (EditText) view.findViewById(R.id.sudoku_entry_8_6);
        out[8][7] = (EditText) view.findViewById(R.id.sudoku_entry_8_7);
        out[8][8] = (EditText) view.findViewById(R.id.sudoku_entry_8_8);

        return out;
    }

    public static int[][] editTextToInt(EditText[][] board) {
        int[][] out = new int[board.length][board.length];
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(board[i][j].getText().toString().equals("")) {
                    out[i][j] = 0;
                }
                else if(android.text.TextUtils.isDigitsOnly(board[i][j].getText())) {
                    out[i][j] = Integer.parseInt(board[i][j].getText().toString());
                }
                else {
                    return null;
                }
            }
        }
        return out;
    }

    public static boolean solutionToEditText(Sudoku sudoku, EditText[][] board) {
        int[][] Grid = sudoku.getGrid();
        for(int i = 0 ; i < Grid.length ; i++) {
            for(int j = 0 ; j < Grid[i].length ; j++) {
                if(Grid[i][j] == 0)
                return false;
            }
        }

        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(Grid[i][j] == 0)
                    continue;
                board[i][j].setText(Integer.toString(Grid[i][j]));
            }
        }
        return true;
    }

    public static boolean generatedGameToEditText(final Sudoku sudoku, final EditText[][] board, final TextView message) {
        final int[][] Grid = sudoku.getGrid();

        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(Grid[i][j]==0) {
                    final int thisI = i;
                    final int thisJ = j;
                    board[i][j].addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().equals("")) {
                                if (!sudoku.isValidRow(thisI, Integer.parseInt(s.toString())) || !sudoku.isValidColumn(thisJ, Integer.parseInt(s.toString())) || !sudoku.isValidSubMatrix(thisI, thisJ, Integer.parseInt(s.toString()))) {
                                    board[thisI][thisJ].setTextColor(Color.RED);
                                }
                                else {
                                    board[thisI][thisJ].setTextColor(Color.BLACK);
                                }
                                Grid[thisI][thisJ] = Integer.parseInt(s.toString());
                            }
                            else
                                Grid[thisI][thisJ] = 0;
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (sudoku.isSolved()) {
                                message.setText("Sudoku is solved! Congratulations!");
                                gameDone(board);
                            }
                        }
                    });
                    continue;
                }
                board[i][j].setTypeface(null, Typeface.BOLD);
                board[i][j].setText(Integer.toString(Grid[i][j]));
                board[i][j].setTextColor(Color.BLACK);
                board[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
            }
        }
        message.setText("");
        return true;
    }

    private static void gameDone(EditText[][] board) {
        for(int i = 0 ; i < board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setTextColor(Color.GREEN);
                board[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
            }
        }
    }

}
