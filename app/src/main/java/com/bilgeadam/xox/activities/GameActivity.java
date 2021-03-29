package com.bilgeadam.xox.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bilgeadam.xox.Actions.Animations;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.data.ScoreboardCommunicator;
import com.bilgeadam.xox.fragments.GameBoard;
import com.bilgeadam.xox.fragments.GameInfo;
import com.bilgeadam.xox.game.Logic;

public class GameActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private Logic gameLogic;
    private ScoreboardCommunicator scoreboardCommunicator;
    private boolean isDrawCompleted;
    private Animations animations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        animations = new Animations();
        isDrawCompleted = false;
        scoreboardCommunicator = ScoreboardCommunicator.getINSTANCE(this);

        // Declare fragment
        Fragment gameInfo = new GameInfo(),
                gameBoard = new GameBoard();

        // Initialize fragments
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.game_info_frame,gameInfo);
        transaction.add(R.id.game_board_frame,gameBoard);
        transaction.commit();


        // Game Logic Communicator
        gameLogic = Logic.getInstance();
        gameLogic.setScoreboardCommunicator(scoreboardCommunicator); //new Logic(r, c, scoreboardCommunicator);

        setContentView(R.layout.activity_game);
    }
    public void dropIn(View view){
        ImageView currentView = (ImageView) view;

        animations.dropImage(currentView, gameLogic.getCurrentPlayerImageId(), 500);
        currentView.setClickable(false);

        int id = Integer.parseInt(currentView.getTag().toString());
        if (gameLogic.processTurn(Math.floorDiv(id, 10) - 1, id % 10 - 1)){
            Toast.makeText(this,
                    gameLogic.isGameDraw() ? getString(R.string.draw_message) : String.format(getString(R.string.win_message), gameLogic.getCurrentPlayerInfo(), gameLogic.getGameScore()),
                    Toast.LENGTH_LONG).show();

            scoreboardCommunicator.getAllScores(null);
            animations.setWidgetVisibilityWithDelay(findViewById(R.id.scoreBoardList), 1000L, View.VISIBLE);

//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.postDelayed(this::recreate, 2500);
//            finish();
        } else {
//            setPlayerInfoText();
        }
    }
//
//    private void setPlayerInfoText(){
//        TextView textView = findViewById(R.id.playerInfo);
//        textView.setText(String.format(getString(R.string.player), game.getCurrentPlayerInfo()));
//    }
}
