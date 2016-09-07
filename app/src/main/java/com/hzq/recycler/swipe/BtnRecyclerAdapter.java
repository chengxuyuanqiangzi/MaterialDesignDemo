package com.hzq.recycler.swipe;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.materialdesigndemo.R;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;
import com.hzq.metrialdesignrefresh.Util;

/**
 * Created by hezhiqiang on 16/7/1.
 */
public class BtnRecyclerAdapter extends RecyclerView.Adapter<BtnRecyclerAdapter.MyViewHolder>
        implements SwipeableItemAdapter<BtnRecyclerAdapter.MyViewHolder> {

    private AbstractDataProvider mProvider;
    private EventListener mEventListener;
    private LayoutInflater mInflater;
    public Context mcontext;

    public BtnRecyclerAdapter(Activity context, AbstractDataProvider dataProvider) {
        mProvider = dataProvider;
        mInflater = LayoutInflater.from(context);
        mcontext = context;

        setHasStableIds(true);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = mInflater.inflate(R.layout.btn_recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AbstractDataProvider.Data item = mProvider.getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventListener != null) {
                    mEventListener.onItemViewClicked(v); // true --- pinned
                }
            }
        });
        // set text
        holder.mTextView.setText(mProvider.getItem(position).getText());
        // set background resource (target view ID: container)
        final int swipeState = holder.getSwipeStateFlags();

        if (((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_UPDATED) != 0))  {
            int bgResId = R.drawable.bg_item_normal_state;
            holder.mContainer.setBackgroundResource(bgResId);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventListener.onItemViewClicked(RecyclerViewAdapterUtils.getParentViewHolderItemView(v));
            }
        });

        // set swiping properties
        float f = -(float)(Util.dip2px(mcontext,80)*3)/(float) Util.width(mcontext);
        holder.setMaxLeftSwipeAmount(f);
        holder.setMaxRightSwipeAmount(0);
        holder.setSwipeItemSlideAmount(item.isPinnedToSwipeLeft() ? f : 0);
    }

    @Override
    public long getItemId(int position) {
        return mProvider.getItem(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        return mProvider.getItem(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mProvider.getCount();
    }

    @Override
    public int onGetSwipeReactionType(MyViewHolder holder, int position, int x, int y) {
        if(ViewUtils.hitTest(holder.getSwipeableContainerView(),x,y)){
            return mProvider.getItem(position).getSwipeReactionType();
        }
        return RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
    }

    @Override
    public void onSetSwipeBackground(MyViewHolder holder, int position, int type) {
        int bgRes = 0;
        if(type == RecyclerViewSwipeManager.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND){
            bgRes = R.drawable.bg_swipe_item_neutral;
        }
        holder.itemView.setBackgroundResource(bgRes);
    }

    /*@Override
    public int onSwipeItem(MyViewHolder holder, int position, int result) {
        switch (result) {
            // swipe right
            case RecyclerViewSwipeManager.RESULT_SWIPED_RIGHT:
                    // pinned --- back to default position
                    return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_DEFAULT;
            case RecyclerViewSwipeManager.RESULT_SWIPED_LEFT:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION;
            // other --- do nothing
            case RecyclerViewSwipeManager.RESULT_CANCELED:
            default:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_DEFAULT;
        }
    }

    @Override
    public void onPerformAfterSwipeReaction(MyViewHolder holder, int position, int result, int reaction) {
        final AbstractDataProvider.Data item = mProvider.getItem(position);

        if (reaction == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM) {
//            mProvider.removeItem(position);
//            notifyItemRemoved(position);
//
//            if (mEventListener != null) {
//                mEventListener.onItemRemoved(position);
//            }
        }
        else if (reaction == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION) {
            item.setPinnedToSwipeLeft(true);
            notifyItemChanged(position);

//            if (mEventListener != null) {
//                mEventListener.onItemPinned(position);
//            }
        }
        else {
                item.setPinnedToSwipeLeft(false);
        }
    }*/

    public EventListener getEventListener() {
        return mEventListener;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @Override
    public SwipeResultAction onSwipeItem(MyViewHolder holder, int position, int result) {
        return null;
    }

    public static class MyViewHolder extends AbstractSwipeableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;
        public View mDragHandle;
        private Button btn;

        public MyViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
            mDragHandle = v.findViewById(R.id.drag_handle);
            btn = (Button) v.findViewById(R.id.btn);
        }

        @Override
        public View getSwipeableContainerView() {
            return mContainer;
        }
    }

    public interface EventListener {
        void onItemRemoved(int position);

        void onItemPinned(int position);

        void onItemViewClicked(View v);
    }
}
