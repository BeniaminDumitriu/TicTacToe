package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GameModeActivity extends AppCompatActivity {
    Button singlePlayer,withFriend;
    ImageView appLogo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        singlePlayer=findViewById(R.id.play_single);
        withFriend=findViewById(R.id.play_with_friend);
        appLogo=findViewById(R.id.logo);

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameModeActivity.this,ComputerGameActivity.class);
                startActivity(intent);
            }
        });
        withFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameModeActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
}
}
