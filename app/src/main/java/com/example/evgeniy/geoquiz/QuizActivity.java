package com.example.evgeniy.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;
    private int currentIndex = 0;

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    public void updateQuestion(){
      int question = questionBank[currentIndex].getQuestion();
        questionTextView.setText(question);
    };

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else messageResId = R.string.incorrect_toast;

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView)findViewById(R.id.question_textview);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questionBank.length;
                updateQuestion();
            }
        });

        trueButton = (Button)findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        nextButton = (ImageButton)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionBank.length;
                updateQuestion();
            }
        });
        prevButton = (ImageButton)findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick (View view){
                currentIndex = (currentIndex-1)%questionBank.length;
                updateQuestion();
            }
        });

        falseButton = (Button)findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                checkAnswer(false);
            }
        });
        updateQuestion();
    }
}
