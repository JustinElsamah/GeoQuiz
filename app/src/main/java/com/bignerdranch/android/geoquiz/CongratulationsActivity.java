package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CongratulationsActivity extends AppCompatActivity {

    private TextView mScoreCongratulationsText;
    private Button mCongratulationsButton;
    private static final String getScoreExtra = "com.bignerdranch.android.geoquiz.get_score";
    private static final String buttonPressedExtra = "com.bignerdranch.android.geoquiz.button_pressed";
    private String mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);
        mScore = this.getIntent().getStringExtra(getScoreExtra);
        mScoreCongratulationsText = (TextView)findViewById(R.id.score_congratulations_text);
        mScoreCongratulationsText.setText("You got a score of " + mScore);
        mCongratulationsButton = (Button)findViewById(R.id.congratulations_button);
        mCongratulationsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(CongratulationsActivity.this, "Thanks!", Toast.LENGTH_SHORT).show();
                setIfButtonPressedResult(true);
            }

        });

    }


    public void setIfButtonPressedResult(boolean buttonPressedResult){
        Intent intent = new Intent();
        intent.putExtra(buttonPressedExtra, buttonPressedResult);
        setResult(Activity.RESULT_OK, intent);
    }

    public static boolean wasButtonPressed(Intent data){
        return data.getBooleanExtra(buttonPressedExtra, false);
    }



    public static Intent newIntent(Context packageContext, String score){
        return (new Intent(packageContext, CongratulationsActivity.class)).putExtra(getScoreExtra, score);
    }



}
