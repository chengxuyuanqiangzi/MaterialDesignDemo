package com.hzq.widgets;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.deeplinkdispatch.DeepLink;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.GlobalLog;
import com.example.materialdesigndemo.R;

import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/8/30.
 */
@DeepLink("hzq://circleprogress.com/deepLink/{id}")
public class CircleProgressBarActivity extends BaseActivity {
    @InjectView(R.id.circleProgressBar)
    CircleProgressBar circleProgressBar;

    @Override
    public int initLayoutId() {
        return R.layout.circle_progress_layout;
    }

    @Override
    public void initPageView() {
        //兼容Deeplink跳转
        Intent intent = getIntent();
        if(intent.getBooleanExtra(DeepLink.IS_DEEP_LINK,false)){
            Bundle params = intent.getExtras();
            if(params != null && params.getString("id") != null){
                String id = params.getString("id");
                GlobalLog.d("------------------->id=",id);
            }
        }
        circleProgressBar.setProgress(56);
        circleProgressBar.initProgress();
        return;
    }

}
