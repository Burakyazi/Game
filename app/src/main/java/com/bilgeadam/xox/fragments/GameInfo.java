package com.bilgeadam.xox.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.game.Logic;
import org.jetbrains.annotations.NotNull;

public class GameInfo extends Fragment {
    Logic game;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        View gameInfo = inflater.inflate(R.layout.fragment_game_info,container,false);

        game = Logic.getInstance();

        TextView playerText = gameInfo.findViewById(R.id.playerInfo);
        playerText.setText(String.format(getString(R.string.player), game.getCurrentPlayerInfo()));

        return gameInfo;


    }
}
