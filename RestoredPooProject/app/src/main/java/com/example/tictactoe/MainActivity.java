package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneName,playerTwoName;
    private TextView playerOneScore,playerTwoScore,playerStatus;
    private Button [] buttons= new Button[9];
    private Button resetGame;
    private int playerOneScoreCount,playerTwoScoreCount,roundCount;
    boolean activePlayer;

    private Chronometer chronometer;   //timer
    private long pauseOffset;
    private boolean running;

    //player1=0
    //player2=1
    //null=2
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningCases={
            {0,1,2}, {3,4,5}, {6,7,8},//linii
            {0,3,6}, {1,4,7}, {2,5,8},//coloane
            {0,4,8}, {2,4,6}          //diagonale
    };
    int reachTimeLimit=300000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.timer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
         @Override
         public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= reachTimeLimit) {
                          chronometer.setBase(SystemClock.elapsedRealtime());
                          Toast.makeText(MainActivity.this,"You've had enough!",Toast.LENGTH_SHORT).show();
                          Intent i=new Intent(MainActivity.this,GameModeActivity.class);
                          startActivity(i);
                }
                }
         });
        resetChronometer();
        startChronometer();
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tictactoe_music);
        mediaPlayer.start();


        playerOneName=findViewById(R.id.player1_name);
        playerTwoName=findViewById(R.id.player2_name);
        playerOneScore=(TextView) findViewById(R.id.player1);
        playerTwoScore=(TextView) findViewById(R.id.player2);
        playerStatus=(TextView) findViewById(R.id.winner_view);
        resetGame=(Button) findViewById(R.id.reset_button);
        String name1=getIntent().getStringExtra("keyplayer1");
        String name2=getIntent().getStringExtra("keyplayer2");
        if(name1==null)
            playerOneName.setText(R.string.player1);
        else
            playerOneName.setText(name1);
        if(name2==null)
            playerTwoName.setText(R.string.player2);
        else
            playerTwoName.setText(name2);


        for(int i=0;i<buttons.length;i++)
        {
            String buttonID="btn"+i;
            int resourceID=getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i]=(Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        roundCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID= view.getResources().getResourceEntryName(view.getId());//btn0
        int gameStatePointer= Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));//

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#FF000000"));
            gameState[gameStatePointer]=0;
        }
        else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#FF0000"));
            gameState[gameStatePointer]=1;
        }
        roundCount++;

        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerResult();
                Toast.makeText(this,"Player One Won",Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                playerTwoScoreCount++;
                updatePlayerResult();
                Toast.makeText(this,"Player Two Won",Toast.LENGTH_SHORT).show();
                playAgain();
            }
        }else{
            if(roundCount==9){
                playAgain();
                Toast.makeText(this,"No Winner ",Toast.LENGTH_SHORT).show();
            }
            else{
                activePlayer=!activePlayer;
            }
        }
        if(playerOneScoreCount>playerTwoScoreCount){
            playerStatus.setText("Player One is winning");
        }
        else{
            if(playerTwoScoreCount>playerOneScoreCount)
                playerStatus.setText("Player Two is winning");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain(0,true);
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerResult();
            }
        });
    }

    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
    public boolean checkWinner(){
        boolean winnerResult=false;

        for(int [] winningPosition:winningCases){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    &&gameState[winningPosition[0]]!=2)
            {
                winnerResult=true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerResult(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        roundCount=0;
        activePlayer=true;

        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
    public void playAgain(int _roundCount,boolean _activePlayer){
        roundCount=_roundCount;
        activePlayer=_activePlayer;
        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }

}
