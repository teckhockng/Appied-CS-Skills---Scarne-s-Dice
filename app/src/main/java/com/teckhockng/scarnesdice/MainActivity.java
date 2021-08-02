package com.teckhockng.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public int overall_score = 0;
    public int user_turn_score = 0;
    public int computer_score = 0;
    public int computer_turn_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Your score: "+ overall_score + "\nComputer score: " + computer_score + "\nYour turn score: 0");
    }

    /**
     * Called when the user taps the send button
     */
    public void clickRoll(View view) {
          Button rollButton = (Button) findViewById(R.id.roll);
          rollButton.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                TextView textView = (TextView) findViewById(R.id.textView);
                System.out.println("Roll button was clicked");

                if(rollDice()){

                }else{
                    computerTurn();
                }

              }
          });
//
    }

    public boolean rollDice(){
        Random random = new Random();
        int rollDice = random.nextInt(5)+1;
        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView);


        switch (rollDice) {
            case 1:
                
                image.setImageResource(R.drawable.dice1);
                user_turn_score = 0;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: 0");
                return false;

            case 2:
                image.setImageResource(R.drawable.dice2);
                user_turn_score += 2;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                break;

            case 3:
                image.setImageResource(R.drawable.dice3);
                user_turn_score += 3;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                break;

            case 4:
                image.setImageResource(R.drawable.dice4);
                user_turn_score += 4;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                break;

            case 5:
                image.setImageResource(R.drawable.dice5);
                user_turn_score += 5;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                break;

            case 6:
                image.setImageResource(R.drawable.dice6);
                user_turn_score += 6;
                textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                break;

        }
        return true;
    }

    public void clickHold(View view){
        Button rollButton = (Button) findViewById(R.id.roll);
        Button holdButton = (Button) findViewById(R.id.hold);

        System.out.println("Hold button was clicked");
        holdButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textView);
                overall_score += user_turn_score;
                user_turn_score = 0;
                textView.setText("Your score: "+ overall_score + "\nComputer score: " + computer_score + "\nYour turn score: " + user_turn_score);
                if (overall_score >= 50){
                    textView.setText("Your score: " + overall_score + "\nYou won!");
                    holdButton.setEnabled(false);
                    rollButton.setEnabled(false);
                }else {
                    computerTurn();
                }

            }
        });
    }

    public void clickReset(View view){
        Button resetButton = (Button) findViewById(R.id.reset);
        Button rollButton = (Button) findViewById(R.id.roll);
        Button holdButton = (Button) findViewById(R.id.hold);
        System.out.println("Reset button was clicked");
        resetButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                overall_score = 0;
                user_turn_score = 0;
                computer_score = 0;
                computer_turn_score = 0;

                ImageView image = (ImageView) findViewById(R.id.imageView);
                TextView textView = (TextView) findViewById(R.id.textView);

                image.setImageResource(R.drawable.dice5);
                textView.setText("Your score: "+ overall_score + "\nComputer score: " + computer_score + "\nYour turn score: X");
                holdButton.setEnabled(true);
                rollButton.setEnabled(true);
            }
        });
    }

    public void computerTurn(){
        Button rollButton = (Button) findViewById(R.id.roll);
        Button holdButton = (Button) findViewById(R.id.hold);

        TextView textView = (TextView) findViewById(R.id.textView);

        rollButton.setEnabled(false);
        holdButton.setEnabled(false);



        boolean ableToRoll = true;
        boolean losing = ((computer_score+user_turn_score) <= overall_score);

        rollDice();
//        while (ableToRoll){
//            ableToRoll = rollDice();
//            if(!losing){
//                break;
//            }
//            System.out.println(ableToRoll);
//        }
        computer_score += user_turn_score;
        user_turn_score = 0;
        if (computer_score >= 50){
            textView.setText("Computer score: " + computer_score + "\nYou lost!");
            holdButton.setEnabled(false);
            rollButton.setEnabled(false);
        }else {
            textView.setText("Your score: " + overall_score + "\nComputer score: " + computer_score + "\nYour turn score: 0");
        }

        holdButton.setEnabled(true);
        rollButton.setEnabled(true);

    }

}