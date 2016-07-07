package com.example.materialdesigndemo;

/**
 * Created by hezhiqiang on 16/6/29.
 */
public interface ItemThouchHelperAdapterCallback {
    /**
     * 拖拽会掉
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * 侧滑删除
     *
     * @param adapterPosition
     */
    void onItemSwiped(int adapterPosition);
}
