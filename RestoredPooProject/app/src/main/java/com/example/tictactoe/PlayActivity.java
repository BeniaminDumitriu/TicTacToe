package com.example.tictactoe;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;


public class PlayActivity extends AppCompatActivity {
    private Button button;
    private TextInputEditText playerOneName,playerTwoName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        button=findViewById(R.id.play_button);
        playerOneName=findViewById(R.id.player1_type);
        playerTwoName=findViewById(R.id.player2_type);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1Name=playerOneName.getText().toString();
                String player2Name=playerTwoName.getText().toString();
                Intent intent=new Intent(PlayActivity.this,MainActivity.class);
                intent.putExtra("keyplayer1",player1Name);
                intent.putExtra("keyplayer2",player2Name);
                startActivity(intent);
            }
        });
    }
    public void goMain()
    {

    }

}
