package com.hzq.recycler.itemtouchhelper;

import android.content.Context;
import android.view.ViewGroup;

import com.hzq.recycler.RecyclerAdapter;

import java.util.Collections;

/**
 * Created by hezhiqiang on 16/7/9.
 */
public class SimpleItemTouchAdapter extends RecyclerAdapter implements ItemTouchHelperAdapter{

    public SimpleItemTouchAdapter(Context context){
        super(context);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDatas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onSwipe(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}
