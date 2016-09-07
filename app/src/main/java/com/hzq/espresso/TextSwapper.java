package com.hzq.espresso;

/**
 * Created by hezhiqiang on 16/8/8.
 */
public class TextSwapper {
    private String mBeforeText,mAfterText,mCurrentText;

    public TextSwapper(String beforeText,String afterText){
        mBeforeText = beforeText;
        mAfterText = afterText;
        mCurrentText = mBeforeText;
    }

    public String swap(){
        mCurrentText = getmCurrentText().equals(mBeforeText) ? mAfterText : mBeforeText;
        return mCurrentText;
    }

    public String getmCurrentText(){
        return mCurrentText;
    }
}
