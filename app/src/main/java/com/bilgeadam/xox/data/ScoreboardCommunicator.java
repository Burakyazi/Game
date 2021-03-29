package com.bilgeadam.xox.data;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bilgeadam.xox.presentation.contacts.scoreBoard.ScoreBoardAdapter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ScoreboardCommunicator {
    private static ScoreboardCommunicator INSTANCE;

    private static final String URI = String.format("http://%s:8080/scoreboard","192.168.1.104");
    private final RequestQueue requestQueue; // Request are send via a queue in a separate thread(via volley)
    private final Context context;

    public ScoreboardCommunicator(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    public synchronized static ScoreboardCommunicator getINSTANCE(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new ScoreboardCommunicator(context);
        }
        return INSTANCE;
    }

    public void addScore(String playerName,Float score){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PERMISSION_GRANTED) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URI,
                    new Score(score, playerName).toJSON(),
                    listener -> Log.i("Score Post Request", "Score added."),
                    error -> Log.e("Score Post Request", "Score cannot be added.", error)
            );
            requestQueue.add(request);
        }
    }

    public void getAllScores(ScoreBoardAdapter adapter){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PERMISSION_GRANTED) {

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URI, null,
                    response -> {

                        final List<Score> scoreList = new ArrayList<>();
                        IntStream.range(0, response.length()).forEach(index -> {
                            try {
                                JSONObject obj = response.getJSONObject(index);
                                scoreList.add(new Score((float) obj.optDouble("score"),
                                        obj.optString("playerName"),
                                        obj.optInt("id")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                        adapter.setScoreList(scoreList);
                    },
                    error -> Log.e(("Score Get Request"), "Score cannot be acquire.", error)
            );
            requestQueue.add(request);
        }
    }
}
