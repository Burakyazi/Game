package com.bilgeadam.xox.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Score {

    private final float score;
    private final String username;
    private final Integer id;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public float getScore() {
        return score;
    }

    protected Score(float score,String username){
        this.score = score;
        this.username= username;
        id = null;
    }

    public Score(float score, String username, Integer id) {
        this.score = score;
        this.username = username;
        this.id = id;
    }
    protected JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("playerName",getUsername());
            jsonObject.put("score",getScore());
            if (id != null)
                jsonObject.put("id",getId());
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;

    }

}
