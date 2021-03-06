package com.example.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by hezhiqiang on 16/6/27.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    public static final String TAG = "BaseActivity";
    public static final String EXTRA_TITLE = "title_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        ButterKnife.inject(this);
        initToolbar();
        initPageView();
    }

    public abstract int initLayoutId();

    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar == null)
            throw new NullPointerException("未找到对应的标题栏资源");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initPageView(){};

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GlobalLog.d(TAG,"test------------");
    }

    public void launcherActivity(Class cs){
        startActivity(new Intent(this,cs));
    }
}
