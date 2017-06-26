package com.bignerdranch.android.geoquiz;

/**
 * Created by justinelsemah on 2017-06-22.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mEnabled;

    public Question(int textResId, boolean answerTrue){
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
        this.mEnabled = true;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }
}
