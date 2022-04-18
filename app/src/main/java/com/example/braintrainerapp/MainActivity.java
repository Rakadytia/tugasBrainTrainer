package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView questionTextView;

    TextView timerTextView;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> order= new ArrayList<Integer> ();
    int questionIndex = 0;

    //    Dictionary of the questions and answers. Index of the correct answer given in final Arraylist element
        HashMap<Integer, ArrayList<String>> questions = new HashMap<Integer, ArrayList<String>> () {{
        put(1, new ArrayList<String>(){{ add("Buah warna kuning"); add("pisang"); add("salak"); add("semangka"); add("duku"); add("1");}});
        put(2, new ArrayList<String>(){{ add("Buah berduri"); add("jeruk"); add("durian"); add("melon"); add("anggur"); add("2");}});
        put(3, new ArrayList<String>(){{ add("buah yang rasanya asem"); add("pisang"); add("strawbery"); add("sawo"); add("nangka"); add("2");}});
        put(4, new ArrayList<String>(){{ add("buah yang depanya s"); add("semangka"); add("gk tau"); add("jeruk"); add("pisang"); add("1");}});
        put(5, new ArrayList<String>(){{ add("buah yang kayak hewan"); add("buah naga"); add("durian"); add("semangka"); add("duku"); add("1");}});
        put(6, new ArrayList<String>(){{ add("buah yang depannya k"); add("timun suri"); add("kelengkeng"); add("salak"); add("nanas"); add("2");}});

        // put(, new ArrayList<String>(){{ add(""); add(""); add(""); add(""); add(""); add("2");}});

    }};

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startbuttom);
        button0 = (Button)findViewById((R.id.button0));
        button1 = (Button)findViewById((R.id.button1));
        button2 = (Button)findViewById((R.id.button2));
        button3 = (Button)findViewById((R.id.button3));
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = findViewById (R.id.gameRelativeLayout);
        questionTextView = (TextView)findViewById(R.id.questionTextView);

        for(int i = 1; i <= questions.size(); i++) {
            order.add(i);
        }
        Collections.shuffle(order);
    }

    public void playAgain (View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion(questionIndex);

        new CountDownTimer (30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }
            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                resultTextView.setText("Your score: ");
            }
        }.start();
    }

    public void generateQuestion(int num) {
        int i = order.get(num);

        locationOfCorrectAnswer = Integer.valueOf(questions.get(i).get(5));
        String question = questions.get(i).get(0);
        questionTextView.setText(question);

        button0.setText(questions.get(i).get(1));
        button1.setText(questions.get(i).get(2));
        button2.setText(questions.get(i).get(3));
        button3.setText(questions.get(i).get(4));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(String.valueOf(locationOfCorrectAnswer))) {

            System.out.println(view.getTag().getClass().getName());
            System.out.println(locationOfCorrectAnswer);

            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        questionIndex++;
        if (questions.get(questionIndex+1) == null) {
            questionIndex = 0;
            Collections.shuffle(order);
        }
        generateQuestion(questionIndex);

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

}