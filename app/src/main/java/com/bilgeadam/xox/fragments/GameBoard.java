package com.bilgeadam.xox.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.config.dynamicConfiguration;
import com.bilgeadam.xox.game.Logic;
import org.jetbrains.annotations.NotNull;

public class GameBoard extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        View gameBoard = inflater.inflate(R.layout.fragment_game_board,container,false);

        TableLayout board =gameBoard.findViewById(R.id.gameboard_layout);
        int r = board.getChildCount();
        int c = ((TableRow)board.getChildAt(0)).getChildCount();
        Logic.getInstance().Initialize(r,c);

        gameBoard.post(()-> {
            Log.i("POST", String.format("onCreateView: x:%d, y:%d", gameBoard.getMeasuredHeight(), gameBoard.getMeasuredWidth()));
            dynamicConfiguration.setImageSize(gameBoard.findViewById(R.id.gameboard_layout));
        });

        return gameBoard;
    }

}
