package com.bilgeadam.xox.data;

import android.util.Log;
import com.bilgeadam.xox.data.ScoreBoardConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ScoreBoard_DAO  {
/*
    private final ScoreBoardConnection dbConnection;

    private final ExecutorService executorService;

    public ScoreBoard_DAO() {

        /*
        executorService.execute(()->{
            dbConnection = new ScoreBoardConnection();
            try {
                dbConnection.getConnection();

            }catch (Exception e) {
                Log.e("DB","Failure",e);
            }

        });

        dbConnection = new ScoreBoardConnection();
        executorService = Executors.newSingleThreadExecutor();
        try{
            dbConnection.getConnection();
        }catch (SQLException d){
            Log.e("DB","Failed to insert",d);
        }
    }

    public boolean addScore(String player,float score){
        String query = "Insert into scores(score,name,id) values(? ? ?)";
        AtomicInteger nbOfAffectedRows = new AtomicInteger();

        executorService.execute(()->{
            try (Connection connection = dbConnection.getConnection()){
               PreparedStatement statement= connection.prepareStatement(query);
               statement.setDouble(1,score);
               statement.setNString(2,player);

               nbOfAffectedRows.set(statement.executeUpdate());
            } catch (SQLException d) {
               Log.e("DB","Failed to insert",d);
            }
        });

        return nbOfAffectedRows.get() == 1;
    }

    public List<Score> getScores(){
        AtomicInteger nbOfAffectedRows = new AtomicInteger();
        String query = "Select * from scores";
        AtomicReference<ResultSet> resultSet = new AtomicReference<>();

        executorService.execute(()-> {
            try (Connection connection = dbConnection.getConnection()){
                PreparedStatement statement = connection.prepareStatement(query);
                resultSet.set(statement.executeQuery());
            } catch (Exception e) {
                Log.e("DB","Failed to insert",e);
            }
        });
        try {
            return convertResultToScores(resultSet.get());

        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }

    }

    private List<Score> convertResultToScores(ResultSet set)throws SQLException{
        ArrayList<Score> result = new ArrayList<>();

        while (set.next()) {
            result.add(new Score(
                    set.getFloat("score"),
                    set.getString("name"),
                    set.getInt("id")
            ));
        }
        return result;
    }

 */
}
