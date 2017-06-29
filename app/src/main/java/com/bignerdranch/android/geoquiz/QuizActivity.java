package com.bignerdranch.android.geoquiz;

/**
 * Created by justinelsemah on 2017-06-22.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private static final int CHEAT_ACTIVITY_CODE = 0;
    Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;
    private int mCurrentQuestionId;
    private double mCorrectAnswers = 0;
    private double mNumOfQuestions = mQuestionBank.length;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt("MyIndex");
            mIsCheater = savedInstanceState.getBoolean("IsCheater");
        }
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mQuestionBank[mCurrentIndex].setEnabled(false);
                updateQuestionButtons();
                checkAnswer(true);

            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mQuestionBank[mCurrentIndex].setEnabled(false);
                updateQuestionButtons();
                checkAnswer(false);
            }
        });
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex != 0) ? (mCurrentIndex - 1) : mQuestionBank.length - 1;
                mIsCheater = false;
                updateTextView();
                updateQuestionButtons();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateTextView();
                updateQuestionButtons();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateTextView();
                updateQuestionButtons();
            }

        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, CHEAT_ACTIVITY_CODE);
            }

        });

        updateTextView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == CHEAT_ACTIVITY_CODE) {
            if (data == null) {
                return;
            }
            mQuestionBank[mCurrentIndex].setCheatedON(CheatActivity.wasAnswerShown(data));
        }
    }

    public void checkAnswer(boolean guess) {
        int ResId;
        if (mIsCheater || mQuestionBank[mCurrentIndex].isCheatedON()) {
            Toast.makeText(QuizActivity.this, R.string.judgement_toast, Toast.LENGTH_SHORT).show();
        } else {
            if (mQuestionBank[mCurrentIndex].isAnswerTrue() == guess) {
                mCorrectAnswers++;
                ResId = R.string.correct_toast;
            } else {
                ResId = R.string.incorrect_toast;
            }
            if (!checkCompleted()) {
                Toast.makeText(QuizActivity.this, ResId, Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(QuizActivity.this, "You got a score of " + calculateScore() + "%", Toast.LENGTH_LONG).show();
        }
    }

    public void updateTextView() {
        mCurrentQuestionId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(mCurrentQuestionId);
    }

    public boolean checkCompleted() {
        for (Question question : mQuestionBank) {
            if (question.isEnabled() == true) {
                return false;
            }
        }
        return true;
    }

    public void updateQuestionButtons() {
        if (mQuestionBank[mCurrentIndex].isEnabled() == false) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }


    public String calculateScore() {
        if (mCorrectAnswers == 0) {
            return "0";
        } else {
            return Double.toString(mCorrectAnswers / mNumOfQuestions * 100);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("MyIndex", mCurrentIndex);
        savedInstanceState.putBoolean("IsCheater", mIsCheater);
    }

}
