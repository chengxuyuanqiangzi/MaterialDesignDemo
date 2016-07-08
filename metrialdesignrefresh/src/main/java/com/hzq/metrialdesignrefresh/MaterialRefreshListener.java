package com.hzq.metrialdesignrefresh;

/**
 * Created by hezhiqiang on 16/7/2.
 */
public abstract class MaterialRefreshListener {
    public void onfinish(){};
    public abstract void onRefresh(MaterialRefreshLayout materialRefreshLayout);
    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){};
}
