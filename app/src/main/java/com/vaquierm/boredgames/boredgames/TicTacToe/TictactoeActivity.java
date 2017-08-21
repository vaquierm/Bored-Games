package com.vaquierm.boredgames.boredgames.TicTacToe;

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

public class TictactoeActivity extends AppCompatActivity implements FragmentTicTacToe.TictactoeActionListener {

    private final static String TAG = TictactoeActivity.class.getName();

    private static TictactoeActivity ourInstance;

    private FragmentTicTacToe fragmentTicTacToe;

    private FrameLayout menuFrame;

    private ImageButton onePlayerButton;
    private ImageButton twoPlayerButton;

    private GameMode gameMode;

    private TictactoeGame game;

    public enum GameMode { ONE_PLAYER, TWO_PLAYER }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TictactoeActivity Created");
        super.onCreate(savedInstanceState);
        ourInstance = this;
        setContentView(R.layout.tictactoe_activity);

        game = new TictactoeGame(TictactoeGame.Player.X);

        setUpView();
    }

    private void setUpView() {

        onePlayerButton = (ImageButton) findViewById(R.id.one_player_button);

        onePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "one player CLICK");
                onePlayerSelected();
            }
        });

        twoPlayerButton = (ImageButton) findViewById(R.id.two_player_button);

        twoPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "two player CLICK");
                twoPlayerSelected();
            }
        });

        menuFrame = (FrameLayout) findViewById(R.id.tictactoe_container);
        fragmentTicTacToe = new FragmentTicTacToe();
    }

    private void onePlayerSelected() {
        Log.d(TAG, "entering .onePlayerSelected()");
        gameMode = GameMode.ONE_PLAYER;
        setUpGameFragment();
    }

    private void twoPlayerSelected() {
        Log.d(TAG, "entering .twoPlayerSelected()");
        gameMode = GameMode.TWO_PLAYER;
        setUpGameFragment();
    }

    private void setUpGameFragment() {
        Log.d(TAG, "entering .setUpGameFragment()");
        onePlayerButton.setVisibility(View.GONE);
        twoPlayerButton.setVisibility(View.GONE);
        getFragmentManager().beginTransaction().replace(R.id.tictactoe_container, fragmentTicTacToe).commit();
    }

    @Override
    public void onStartNewGame() {
        Log.d(TAG, "entering .onStartNewGame()");

        switch (gameMode) {
            case ONE_PLAYER:
                Log.d(TAG, "starting new game for one player");
                gameMode = GameMode.ONE_PLAYER;
                setUpNewGame();
                break;
            case TWO_PLAYER:
                Log.d(TAG, "starting new game for two player");
                gameMode = GameMode.TWO_PLAYER;
                setUpNewGame();
                break;
            default:
                Log.d(TAG, "starting new game failed because no game mode is selected");
                break;
        }
    }

    private void setUpNewGame() {
        double random = Math.random();
        TictactoeGame.Player startingPlayer;
        if (random < 0.5)
            startingPlayer = TictactoeGame.Player.O;
        else
            startingPlayer = TictactoeGame.Player.X;
        game = new TictactoeGame(startingPlayer);
        if (gameMode == GameMode.ONE_PLAYER && startingPlayer == TictactoeGame.Player.O) {
            TictactoeBot.firstMovePlay(game);
            fragmentTicTacToe.displayMessage("You start!");
        }
        else if (gameMode == GameMode.TWO_PLAYER) {
            switch (startingPlayer) {
                case X:
                    fragmentTicTacToe.displayMessage("X starts!");
                    break;
                case O:
                    fragmentTicTacToe.displayMessage("O starts!");
                    break;
                default:
                    break;
            }
        }
        afterMove();
    }

    @Override
    public void onTictactoeMove(int i, int j) {
        Log.d(TAG, ".onTictactoeMove(" + i + ", " + j + ")");
        if (game != null) {
            if (game.getCurrentPlayer() == TictactoeGame.Player.O && gameMode == GameMode.ONE_PLAYER) {
                Log.d(TAG, "it's the computer's turn");
            }
            else if (game.playMove(i,j)) {
                Log.d(TAG, "move was valid, updating view");
                afterMove();
                if(game.getGameState() == TictactoeGame.GameState.PLAYING && gameMode == GameMode.ONE_PLAYER) {
                    Log.d(TAG, "one player mode, calling bot");
                    TictactoeBot.play(game);
                    afterMove();
                }
            }
            else {
                Log.d(TAG, "move was invalid");
            }
        }
        else {
            Log.d(TAG, "There seems to be no existing game, create a new one");
        }
    }

    private void afterMove() {
        Log.d(TAG, "entering .afterMove()");
        TictactoeUtil.setImmageViewsFromTictactoeBoard(fragmentTicTacToe.getBoard(), game.getBoard());
        renderMessageFromGameStates();
    }

    private void renderMessageFromGameStates() {
        Log.d(TAG, "entering .renderMessagesFromStates");
        switch (game.getGameState()) {
            case PLAYING:
                switch (game.getCurrentPlayer()) {
                    case X:
                        switch (gameMode) {
                            case ONE_PLAYER:
                                fragmentTicTacToe.displayMessage("");
                                break;
                            case TWO_PLAYER:
                                fragmentTicTacToe.displayMessage("X's turn!");
                                break;
                            default:
                                break;
                        }
                        break;
                    case O:
                        switch (gameMode) {
                            case ONE_PLAYER:
                                fragmentTicTacToe.displayMessage("Robot's turn!");
                                break;
                            case TWO_PLAYER:
                                fragmentTicTacToe.displayMessage("O's turn!");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case WON:
                switch (game.getWinner()) {
                    case X:
                        switch (gameMode) {
                            case ONE_PLAYER:
                                fragmentTicTacToe.displayMessage("You won! Great!");
                                //should never get here...
                                break;
                            case TWO_PLAYER:
                                fragmentTicTacToe.displayMessage("X won the game!");
                                break;
                            default:
                                break;
                        }
                        break;
                    case O:
                        switch (gameMode) {
                            case ONE_PLAYER:
                                fragmentTicTacToe.displayMessage("You lost... Try again!");
                                break;
                            case TWO_PLAYER:
                                fragmentTicTacToe.displayMessage("O won the game!");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case TIED:
                switch (gameMode) {
                    case ONE_PLAYER:
                        fragmentTicTacToe.displayMessage("It's a tie... Try again!");
                        break;
                    case TWO_PLAYER:
                        fragmentTicTacToe.displayMessage("It's a tie!");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

}
