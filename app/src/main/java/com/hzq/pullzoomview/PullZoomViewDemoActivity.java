package com.hzq.pullzoomview;

import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import com.lzy.widget.PullZoomView;

import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/16.
 */
public class PullZoomViewDemoActivity extends BaseActivity {
    @InjectView(R.id.pull_zoom)
    PullZoomView pullZoom;

    @InjectView(R.id.swipelayout)
    SwitchCompat swipelayout;

    @Override
    public int initLayoutId() {
        return R.layout.pullzoomview_layout;
    }

    @Override
    public void initPageView() {
        pullZoom.setIsParallax(true);
        pullZoom.setIsZoomEnable(false);
        swipelayout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pullZoom.setIsZoomEnable(isChecked);
            }
        });
    }
}
