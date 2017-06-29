package com.bignerdranch.android.geoquiz;

/**
 * Created by justinelsemah on 2017-06-22.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mEnabled;
    private boolean mCheatedON;

    public Question(int textResId, boolean answerTrue){
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
        this.mEnabled = true;
        this.mCheatedON = false;
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

    public boolean isCheatedON() {
        return mCheatedON;
    }

    public void setCheatedON(boolean cheatedON) {
        mCheatedON = cheatedON;
    }
}
