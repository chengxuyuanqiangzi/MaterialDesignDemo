package com.hzq.metrialdesignrefresh;

/**
 * Created by hezhiqiang on 16/7/2.
 */
public interface MaterialHeadListener {
    void onComlete(MaterialRefreshLayout materialRefreshLayout);
    void onBegin(MaterialRefreshLayout materialRefreshLayout);
    void onPull(MaterialRefreshLayout materialRefreshLayout, float fraction);
    void onRelease(MaterialRefreshLayout materialRefreshLayout, float fraction);
    void onRefreshing(MaterialRefreshLayout materialRefreshLayout);
}
