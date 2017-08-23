package com.vaquierm.boredgames.boredgames;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                Log.d(TAG, "info CLICK");
                Fragment fragmentInfo =  new FragmentInfo();
                getFragmentManager().beginTransaction().add(android.R.id.content, fragmentInfo,"fragmentInfo").commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
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
