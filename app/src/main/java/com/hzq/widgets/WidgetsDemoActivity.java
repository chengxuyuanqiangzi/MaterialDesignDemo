package com.hzq.widgets;

import android.support.design.widget.Snackbar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/11.
 */
public class WidgetsDemoActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
    @InjectView(R.id.seekBar1)
    SeekBar seekBar1;
    @InjectView(R.id.txt)
    TextView txt;

    @Override
    public int initLayoutId() {
        return R.layout.widgets_layout;
    }

    @Override
    public void initPageView() {
        setTitle("Widget-SeekBar");
        seekBar1.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //用户拖动
        if(fromUser) {
            txt.setText(String.valueOf(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Snackbar.make(seekBar.getRootView(), "start", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Snackbar.make(seekBar.getRootView(), "stop", Snackbar.LENGTH_SHORT).show();
    }

}
