package com.bilgeadam.xox.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bilgeadam.xox.Actions.Animations;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.config.dynamicConfiguration;
import com.bilgeadam.xox.data.ScoreboardCommunicator;
import com.bilgeadam.xox.game.Logic;
import com.bilgeadam.xox.presentation.contacts.contacts.ContactsAdapter;
import com.bilgeadam.xox.presentation.contacts.contacts.ItemClickListener;
import com.bilgeadam.xox.presentation.contacts.scoreBoard.ScoreBoardAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;


public class WelcomeActivity extends AppCompatActivity implements ItemClickListener {

     private Animations animations;
     private ContactsAdapter contactsAdapter;
     private String firstPlayer,secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        animations= new Animations();


        //Request permission if not granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        //Request permission if not granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.INTERNET},2);
        }

        RecyclerView contactList = findViewById(R.id.contactsList);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ContactsAdapter(readContact());
        contactsAdapter.setItemClickListener((ItemClickListener) this);
        contactList.setAdapter(contactsAdapter);


    }

    @Override
    public void OnClickListener(View view, int position) {
        //Log.d(this.getClass().getSimpleName(),String.format("Position %s, value %s",position,contactsAdapter.getItem(position)));

        String name = contactsAdapter.getItem(position);
        contactsAdapter.removeItem(position);
        contactsAdapter.notifyItemRemoved(position);

        if (firstPlayer == null) {
            firstPlayer = name;
            TextView playerText = findViewById(R.id.currentPlayerConfig);
            playerText.setText(String.format(getString(R.string.player_config_message),"0"));
        }else{
            secondPlayer = name;

            Intent intent = new Intent(this,GameActivity.class);
        // intent.putExtra()
            startActivity(intent);

        }

    }


    public void clickStart(View view) {
        final long animationLength = 1000L;
        final Button button = findViewById(R.id.welcomeButton);

        String newText = getString(R.string.welcome_clicked);
        button.setText(newText);

        animations.sendButtonRandomly(button, Optional.empty(), getWindowManager());

        TextView text = findViewById(R.id.welcomeMessage);
        animations.removeText(text, animationLength);

        if (contactsAdapter.getItemCount() == 0) {
            startGame(1000L);
        } else {
            TextView playerText = findViewById(R.id.currentPlayerConfig);
            playerText.setText(String.format(getString(R.string.player_config_message),"X"));
            animations.setWidgetVisibilityWithDelay(playerText,animationLength,View.VISIBLE);
            animations.setWidgetVisibilityWithDelay(findViewById(R.id.contactsList), animationLength, View.VISIBLE);
        }
    }

    private void startGame(long animationLength) {
        contactsAdapter.setItemClickListener(null);

        animations.setWidgetVisibilityWithDelay(findViewById(R.id.currentPlayerConfig),animationLength,View.GONE);
        animations.setWidgetVisibilityWithDelay(findViewById(R.id.contactsList), animationLength, View.GONE);

        TableLayout layout = findViewById(R.id.gameboard_layout);
        animations.setWidgetVisibilityWithDelay(layout, animationLength, View.VISIBLE);

      //  setPlayerInfoText();
        animations.setWidgetVisibilityWithDelay(findViewById(R.id.playerInfo), animationLength, View.VISIBLE);

    }

    @NotNull
    private List<String> readContact(){
        List<String> contacts = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) == PERMISSION_GRANTED) {

            Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                    null,
                    null,
                    String.format("%s  ASC",ContactsContract.Contacts.DISPLAY_NAME ));

            while (cursor.moveToNext())
                contacts.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

            return contacts;
        }else
            return null;
    }
}