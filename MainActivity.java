package com.finalproject21.application1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
            TextView textviewTime ;
            TextView textviewCal;
            TextView textviewScore;
            TextView comment;
            Button buttonGo;
            Button button0;
            Button button1;
            Button button2;
            Button button3;
            Button playagian;
            int score = 0;
            int numberOfQuestions = 0;

            int correctAnsLocation;
            ArrayList<Integer> answers = new ArrayList<>();
            ConstraintLayout layoutsecond;

    public void buttonOn (){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }
    public void buttonOff (){
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }
    public void GameRun(){
        score = 0;
        numberOfQuestions = 0;
       textviewTime.setText("30s");
        textviewScore.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        buttonOn();
        new CountDownTimer(3100,1000) {

            @Override
            public void onTick(long l) {
                textviewTime.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                comment.setText("Done!");
                playagian.setVisibility(View.VISIBLE);
                buttonOff();

            }
        }.start();
        Calculation();
    }
    public void Calculation () {
        Random randomnumber = new Random();
        int spinner = randomnumber.nextInt( 4);
         correctAnsLocation = randomnumber.nextInt( 4);
        answers.clear();
        int a = randomnumber.nextInt( 25 );
        int b = randomnumber.nextInt(25);
        int c = randomnumber.nextInt(11);
        int d = randomnumber.nextInt(11);
        while(c == 0){
            c = randomnumber.nextInt(11);
        }
        while(d == 0){
            d = randomnumber.nextInt(11);
        }
        try{
            if(spinner == 0) {// +++++++++++++++++++++
                textviewCal.setText(Integer.toString(a) + " + " + Integer.toString(b));
                for (int i = 0; i < 4; i++) {
                    if (i == correctAnsLocation) {
                        answers.add(a + b);
                    } else {
                        int wrongAn = randomnumber.nextInt(51);
                        while (wrongAn == a + b) {
                            wrongAn = randomnumber.nextInt(51);
                        }
                        answers.add(wrongAn);
                    }
                }
            }
            else if (spinner == 1){// -------------
                if(a < b){
                    int temp = 0;
                    temp = a;
                    a = b;
                    b = temp;
                }
                textviewCal.setText(Integer.toString(a) + " - " + Integer.toString(b));

                for (int i = 0 ; i < 4; i++){

                        if(i == correctAnsLocation){
                        answers.add(a-b);
                        }else {
                        int wrongAn = randomnumber.nextInt(26);
                        while(wrongAn == a-b){
                            wrongAn = randomnumber.nextInt(26);
                        }
                        answers.add(wrongAn);
                    }
                }
            }
            else if (spinner == 2 ){
                textviewCal.setText(Integer.toString(c) + " x " + Integer.toString(d));
                for (int i = 0 ; i < 4; i++){

                    if(i == correctAnsLocation){
                        answers.add(c*d);
                    }else {
                        int wrongAn = randomnumber.nextInt(91);
                        while(wrongAn == c*d){
                            wrongAn = randomnumber.nextInt(91);
                        }
                        answers.add(wrongAn);
                    }
                }
            }
               else if (spinner == 3 ) {
                textviewCal.setText(Integer.toString(c * d) + " / " + Integer.toString(c));
                for (int i = 0; i < 4; i++) {

                    if (i == correctAnsLocation) {
                        answers.add(d);
                    } else {
                        int wrongAn = randomnumber.nextInt(11);
                        while (wrongAn == d) {
                            wrongAn = randomnumber.nextInt(11);
                        }
                        answers.add(wrongAn);
                    }
                }
            }
        }catch (Exception e){
            Log.i("Error", " " + e );
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public  void letStartgame(View view){
        buttonGo.setVisibility(View.INVISIBLE);
        layoutsecond.setVisibility(View.VISIBLE);
        GameRun();
    }
    public void checkanswers(View view){
        if (Integer.toString(correctAnsLocation).equals(view.getTag().toString())) {
            comment.setText("Correct!");
            score++;
        } else {
            comment.setText("Wrong :(");
        }
        numberOfQuestions++;
        textviewScore.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        Calculation();
    }

    public void playag(View view){
        GameRun();
        playagian.setVisibility(View.INVISIBLE);
        comment.setText("Try Again");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewTime = findViewById(R.id.textviewTime);
        textviewCal = findViewById(R.id.textviewCal);
        textviewScore = findViewById(R.id.textviewScore);
        comment = findViewById(R.id.comment);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonGo = findViewById(R.id.buttonGo);
        playagian = findViewById(R.id.playagain);
        layoutsecond = findViewById(R.id.layoutsecond);
        buttonGo.setVisibility(View.VISIBLE);
        layoutsecond.setVisibility(View.INVISIBLE);
        playagian.setVisibility(View.INVISIBLE);
    }
}
