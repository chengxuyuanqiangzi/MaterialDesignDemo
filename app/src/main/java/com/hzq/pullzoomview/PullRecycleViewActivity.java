package com.hzq.pullzoomview;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import com.hzq.recycler.RecyclerAdapter;
import com.lzy.widget.PullZoomView;
import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/20.
 */
public class PullRecycleViewActivity extends BaseActivity {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.pull_zoom)
    PullZoomView pullZoom;

    @Override
    public int initLayoutId() {
        return R.layout.recycle_pullzoomview_layout;
    }

    @Override
    public void initPageView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new RecyclerAdapter(this));
        pullZoom.setIsParallax(true); //设置头部是否有视差效果
        pullZoom.setIsZoomEnable(true); //设置是否允许缩放效果
        pullZoom.setSensitive(1.2f); //放大敏感系数
        pullZoom.setZoomTime(500);
    }
}
