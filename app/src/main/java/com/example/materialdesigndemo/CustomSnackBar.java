package com.example.materialdesigndemo;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hezhiqiang on 16/7/11.
 */
public class CustomSnackBar {

    private Snackbar snackbar;

    public CustomSnackBar(View view){
        if(snackbar == null){
            snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
        }
    }

    public void setContentDrawable(Drawable drawable,int padding){
        if(snackbar != null) {
            TextView tx = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            tx.setCompoundDrawables(drawable,null,null,null);
            tx.setCompoundDrawablePadding(padding);
        }
    }

    public void setBtnDrawable(Drawable drawable){
        if(snackbar != null) {
            Button btn = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btn.setCompoundDrawables(drawable,null,null,null);
        }
    }

    public void setTextColor(int colorId){
        if(snackbar != null) {
            Button btn = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
            btn.setTextColor(colorId);
        }
    }

    public Snackbar getSnackbar(){
        return snackbar;
    }

    public void setCallback(){
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                //判断 侧滑关闭,点击action关闭,超时关闭,
                if(event == DISMISS_EVENT_SWIPE || event == DISMISS_EVENT_ACTION || event == DISMISS_EVENT_TIMEOUT){

                }
            }
        });
    }

}
