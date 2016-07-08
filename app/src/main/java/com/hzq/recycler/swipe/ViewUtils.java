package com.hzq.recycler.swipe;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by hezhiqiang on 16/7/5.
 */
public class ViewUtils {
    public static boolean hitTest(View v, int x, int y) {
        final int tx = (int) (ViewCompat.getTranslationX(v) + 0.5f);
        final int ty = (int) (ViewCompat.getTranslationY(v) + 0.5f);
        final int left = v.getLeft() + tx;
        final int right = v.getRight() + tx;
        final int top = v.getTop() + ty;
        final int bottom = v.getBottom() + ty;

        return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
    }

    private static final int[] EMPTY_STATE = new int[]{};

    public static void clearState(Drawable drawable){
        if(drawable != null){
            drawable.setState(EMPTY_STATE);
        }
    }
}
