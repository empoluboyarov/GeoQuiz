package com.example.evgeniy.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";

    public static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String CHEAT_INDEX = "CHEAT";

    private boolean answerIsTrue;
    private TextView answerTextView;
    private Button showAnswer;
    private boolean isCheater;

    private void setAnswerShowResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK, data);
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if(savedInstanceState != null)
            isCheater = savedInstanceState.getBoolean(CHEAT_INDEX, false);
        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        setAnswerShowResult(isCheater);

        answerTextView = (TextView)findViewById(R.id.answerTextView);

        showAnswer = (Button)findViewById(R.id.showAnswerButton);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue == true) answerTextView.setText(R.string.true_button);
                else answerTextView.setText(R.string.false_button);
                isCheater = true;
                setAnswerShowResult(isCheater);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(CHEAT_INDEX, isCheater );

    }
}
