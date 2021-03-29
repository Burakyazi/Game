package com.bilgeadam.xox.game;

import java.util.Date;

public class ScoreGenerator {

    private final int Max_Point = 10_000,Min_Point = 1000;
    private static final float Min_Game_Length = 2_000F; // 2 SECONDS

    private final int minTurn,perTurnPoint;
    private Date startDate;

    public ScoreGenerator(int minTurn, int maxTurn) {
        this.minTurn = minTurn;
        perTurnPoint = Math.floorDiv((Max_Point - Min_Point),(maxTurn - minTurn));
    }
    protected void startGame(){
        startDate= new Date();
    }
    protected double generateScore(int numberOfTurns){
        double turnPoint = Max_Point - (numberOfTurns - minTurn) * perTurnPoint;

        float timePoint = (float) (Min_Game_Length * 1.0 / (new Date().getTime() - startDate.getTime()));

        return turnPoint * timePoint;

    }
}
