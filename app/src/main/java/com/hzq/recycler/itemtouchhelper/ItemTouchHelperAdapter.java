package com.hzq.recycler.itemtouchhelper;

/**
 * Created by hezhiqiang on 16/7/9.
 */
public interface ItemTouchHelperAdapter {
    /**
     * 移动
     * @param fromPosition
     * @param toPosition
     */
    void onItemMove(int fromPosition,int toPosition);

    /**
     * 侧滑
     * @param position
     */
    void onSwipe(int position);
}
