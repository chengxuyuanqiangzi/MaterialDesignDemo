package com.hzq.recycler.itemtouchhelper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;

import butterknife.InjectView;

/**
 * 功能:基于RecyclerView 与 ItemTouchHelper 实现的侧滑删除与拖动排序功能
 * Created by hezhiqiang on 16/7/9.
 */
public class ItemTouchHelperActivity extends BaseActivity {
    @InjectView(R.id.recyleView)
    RecyclerView recyleView;

    SimpleItemTouchAdapter adapter;

    @Override
    public int initLayoutId() {
        return R.layout.swipeview_mian;
    }

    @Override
    public void initPageView() {
        adapter = new SimpleItemTouchAdapter(this);
        recyleView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        recyleView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyleView);
    }
}
