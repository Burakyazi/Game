package com.bilgeadam.xox.game;

import androidx.annotation.NonNull;
import com.bilgeadam.xox.R;
import org.jetbrains.annotations.NotNull;

/**
 * Player type declarations
 */
enum Player {
    X(R.drawable.x, 1),
    O(R.drawable.o, 2);

    private final int imageId;
    private final int value;
    private String playerName;

    Player(int imageId, int value) {
        this.imageId = imageId;
        this.value = value;
    }

    public int getImageId() {
        return imageId;
    }

    public Player nextPlayer(){
        return this.ordinal() ==
                X.ordinal() ? O : X;
    }

    public int getValue() {
        return value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String setPlayerName(@NotNull String playerName) {
        this.playerName = playerName;
        return playerName;
    }

    @NotNull
    @NonNull
    @Override
    public String toString() {
        return playerName == null ? name() : String.format("%s %s",this.name(),this.playerName);
    }
}
