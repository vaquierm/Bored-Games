package com.vaquierm.boredgames.boredgames;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentMainMenu.GameSelectionInteractionListener {

    private final static String TAG = MainActivity.class.getName();

    Fragment fragmentMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MainActivity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

        initializeView();
    }

    public void setUpViews() {
        Log.d(TAG, "entering .setUpViews()");
        fragmentMainMenu = new FragmentMainMenu();
    }

    public void initializeView() {
        Log.d(TAG, "entering .initializingView()");
        getFragmentManager().beginTransaction().replace(R.id.contentFrame, fragmentMainMenu).commit();
    }

    @Override
    public void onSudokuSelected() {
        Log.d(TAG, "entering .onSudokuSelected()");
        startActivity(new Intent(getApplicationContext(), com.vaquierm.boredgames.boredgames.Sudoku.SudokuActivity.class));
    }

    @Override
    public void onTictactoeSelected() {
        Log.d(TAG, "entering .onTictaktoeSelected()");
        startActivity(new Intent(getApplicationContext(), com.vaquierm.boredgames.boredgames.TicTacToe.TictactoeActivity.class));
    }
}
