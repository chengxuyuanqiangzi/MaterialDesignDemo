package com.hzq.recycler;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import com.hzq.metrialdesignrefresh.MaterialRefreshLayout;
import com.hzq.metrialdesignrefresh.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/8.
 */
public class RefreshLayoutDemoActivity extends BaseActivity {


    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    boolean isLoadMore = true;
    RecyclerAdapter adapter ;

    @Override
    public int initLayoutId() {
        return R.layout.refresh_layout;
    }

    @Override
    public void initPageView() {
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this);
        recycleview.setAdapter(adapter);
        /**
         * 设置是否上拉加载更多，默认是false，要手动改为true，要不然不会出现上拉加载
         */
        refresh.setLoadMore(isLoadMore);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(recycleview,"已经没有更多数据了",Snackbar.LENGTH_SHORT).show();
                        refresh.finishRefresh();
                    }
                },3000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;
                        List<String> data = new ArrayList<String>();
                        if(data != null){
                            for (int i = 0; i <= 3; i++) {
                                data.add("new City " + i);
                            }
                            adapter.setDatas(data);
                        }
                        refresh.finishRefreshLoadMore();
                    }
                },3000);


            }
        });
    }

}
