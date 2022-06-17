package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class ComputerGameActivity extends AppCompatActivity {
    Button x, o;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7 ,8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;
    ArrayList<String> emptySquares = new ArrayList<>();

    public void computer(){

        if (gameActive) {

            int select = emptySquares.size();
            int selected = new Random().nextInt(select);
            String selectedSquare = emptySquares.get(selected);
            switch (selectedSquare) {
                case "0":
                    ImageView imageView1 = findViewById(R.id.imageView1);
                    imageView1.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "1":
                    ImageView imageView2 = findViewById(R.id.imageView2);
                    imageView2.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "2":
                    ImageView imageView3 = findViewById(R.id.imageView3);
                    imageView3.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "3":
                    ImageView imageView4 = findViewById(R.id.imageView4);
                    imageView4.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "4":
                    ImageView imageView5 = findViewById(R.id.imageView5);
                    imageView5.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "5":
                    ImageView imageView6 = findViewById(R.id.imageView6);
                    imageView6.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "6":
                    ImageView imageView7 = findViewById(R.id.imageView7);
                    imageView7.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "7":
                    ImageView imageView8 = findViewById(R.id.imageView8);
                    imageView8.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;

                case "8":
                    ImageView imageView9 = findViewById(R.id.imageView9);
                    imageView9.setImageResource(R.drawable.tictactoe_x);
                    activePlayer = 0;
                    emptySquares.remove(selectedSquare);
                    gameState[Integer.parseInt(selectedSquare)] = activePlayer;
                    break;
            }

            check();
        }
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive && emptySquares.contains(Integer.toString(tappedCounter))){

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0)
            {

                counter.setImageResource(R.drawable.tictactoe_0);

                activePlayer = 1;

                emptySquares.remove(Integer.toString(tappedCounter));

                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                check();

                computer();
            }
          else {

                counter.setImageResource(R.drawable.tictactoe_x);

                activePlayer = 0;
           }
        }

    }

    public void check(){
        for (int[] winningPosition : winningPositions){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){

                gameActive = false;
                if (activePlayer == 1){
                    Toast.makeText(this,"You won!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"You lost!",Toast.LENGTH_SHORT).show();
                }
                new Handler().postDelayed(() -> {
                    Intent intent=new Intent(ComputerGameActivity.this,GameModeActivity.class);
                    startActivity(intent);
                },2000);
            }
          /*  else {
                Button playAgain = findViewById(R.id.button);
                playAgain.setVisibility(View.VISIBLE);
            }*/
        }
    }

    public void playAgain(View view){

        Button playAgain = findViewById(R.id.button);
        TextView winnerTextView = findViewById(R.id.textView);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        for (int i = 0; i<gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);

        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_game);

        emptySquares.add("0");
        emptySquares.add("1");
        emptySquares.add("2");
        emptySquares.add("3");
        emptySquares.add("4");
        emptySquares.add("5");
        emptySquares.add("6");
        emptySquares.add("7");
        emptySquares.add("8");
        x = findViewById(R.id.x);
        o = findViewById(R.id.o);

       x.setOnClickListener(view -> {
           if (gameState[0] == 2 && gameState[1] == 2 && gameState[2] == 2 && gameState[3] == 2 && gameState[4] == 2 && gameState[5] == 2 && gameState[6] == 2 && gameState[7] == 2 && gameState[8] == 2){
               activePlayer = 1;
           }
       });

       o.setOnClickListener(view -> {
          if (gameState[0] == 2 && gameState[1] == 2 && gameState[2] == 2 && gameState[3] == 2 && gameState[4] == 2 && gameState[5] == 2 && gameState[6] == 2 && gameState[7] == 2 && gameState[8] == 2){
              activePlayer = 0;
           }
       });
    }
}
