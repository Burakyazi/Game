package com.bilgeadam.xox.game;

import com.bilgeadam.xox.Utils.EnumSupport;
import com.bilgeadam.xox.data.ScoreboardCommunicator;

import java.io.Serializable;

public class Logic implements Serializable {

    private static Logic INSTANCE;
    private int[][] gameBoard;
    Player currentPlayer;
    private Boolean isGameDraw;
    private int dimension;
    private int currentTurn, minTurn,maxTurn;
    private ScoreGenerator scoreGenerator;
    private Double gameScore;
    private ScoreboardCommunicator scoreboardCommunicator;

    public synchronized static Logic getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new Logic();
        }
        return INSTANCE;
    }
    private Logic(){
        currentPlayer = (Player) EnumSupport.createRandomValue(Player.values());

    }

    public void Initialize(int rows,int columns) {
        if (rows != columns)
            throw  new IllegalArgumentException(String.format("The game should be initialized with same number of" +
                    " rows and columns.Current row count is %s",rows,columns));

        this.dimension = rows;
        gameBoard = new int[dimension][dimension];
        minTurn = 2 * dimension - 1;
        maxTurn = dimension * dimension;
        isGameDraw = false;

        this.scoreGenerator = new ScoreGenerator(minTurn,maxTurn);
        Player.X.setPlayerName(null);
        Player.O.setPlayerName(null);

        gameScore = null;

    }


    /**
     * Process the current turn
     * @param currentX row index of image
     * @param currentY column index of image
     * @return {@code true} is someone wins or draw,{@code false} game continues
     */
    public boolean processTurn(int currentX, int currentY){

        if (currentTurn == 0)
            scoreGenerator.startGame();

        gameBoard[currentX][currentY] = currentPlayer.getValue();

        //Check if someone won!
        if(++currentTurn >= minTurn){

            if (checkElements(currentX,0,true) || checkElements(0,currentY,false) ||
                    checkCross(currentX,currentY)) {
            gameScore = scoreGenerator.generateScore(currentTurn);

            if (currentPlayer.getPlayerName() != null) {
                scoreboardCommunicator.addScore(currentPlayer.getPlayerName(), gameScore.floatValue());
            }
                return true;
            }

            // Check draw case
            if (currentTurn == maxTurn) {
                isGameDraw = true;
                return true;
            }
        }

        // go to next turn
        currentPlayer = currentPlayer.nextPlayer();
        return false; // Someone win(true), game continues(false),draw
    }

    private boolean checkElements(int currentX,int currentY,boolean isRowCheck){
        if (currentY == dimension || currentX == dimension)
            return true;
        else if (gameBoard[currentX][currentY] != currentPlayer.getValue())
            return false;
        else
            return isRowCheck ? checkElements(currentX,currentY + 1 ,true) :
                    checkElements(currentX+1,currentY,false);
    }

    private boolean checkCrossElements(int currentX,int currentY,boolean isSameElementCheck){
        if (currentY == dimension || currentX == dimension)
            return true;
        else if (gameBoard[currentX][currentY] != currentPlayer.getValue())
            return false;
        else
            return isSameElementCheck ? checkCrossElements(currentX+1,currentY+ 1 ,true)
                    : checkCrossElements(currentX-1,currentY+1,false);
    }


    private boolean checkCross(int currentX,int currentY) {

        if (currentX == currentY || Math.abs(currentX - currentY) == dimension-1) {
            return checkCrossElements(0,0,true)
                    || checkCrossElements(dimension - 1,0,false);
        }else
            return false;
    }

    public boolean isGameDraw(){
        return isGameDraw;
    }

    public String getCurrentPlayerInfo() {
        return currentPlayer.toString();
    }

    public int getCurrentPlayerImageId(){
        return currentPlayer.getImageId();
    }


    public String getPlayerConfiguration(){
        if (Player.X.getPlayerName() != null && Player.O.getPlayerName() != null)
            return null;
        else if (Player.X.getPlayerName() == null)
            return Player.X.name();
        else
            return Player.O.name();
    }
    public void setPlayerName(String name){
        if (Player.X.getPlayerName() == null) {
            Player.X.setPlayerName(name);
        }else {
            Player.O.setPlayerName(name);
        }
    }

    public double getGameScore() {
        return gameScore;
    }

    public void setScoreboardCommunicator(ScoreboardCommunicator scoreboardCommunicator) {
        this.scoreboardCommunicator = scoreboardCommunicator;
    }
}
