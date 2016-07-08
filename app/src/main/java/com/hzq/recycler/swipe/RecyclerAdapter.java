package com.hzq.recycler.swipe;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.materialdesigndemo.R;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;

/**
 * Created by hezhiqiang on 16/7/1.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
        implements SwipeableItemAdapter<RecyclerAdapter.MyViewHolder> ,
        DraggableItemAdapter<RecyclerAdapter.MyViewHolder>{

    private AbstractDataProvider mProvider;
    private EventListener mEventListener;
    private LayoutInflater mInflater;
    public Context mcontext;

    public RecyclerAdapter(Activity context,AbstractDataProvider dataProvider) {
        mProvider = dataProvider;
        mInflater = LayoutInflater.from(context);
        mcontext = context;

        setHasStableIds(true);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AbstractDataProvider.Data item = mProvider.getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventListener != null) {
                    mEventListener.onItemViewClicked(v, true); // true --- pinned
                }
            }
        });
        // (if the item is *pinned*, click event comes to the mContainer)
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventListener != null) {
                    mEventListener.onItemViewClicked(RecyclerViewAdapterUtils.getParentViewHolderItemView(v), false);  // false --- not pinned
                }
            }
        });

        // set text
        holder.mTextView.setText(mProvider.getItem(position).getText());

        // set background resource (target view ID: container)
        final int swipeState = holder.getSwipeStateFlags();
        final int dragState = holder.getDragStateFlags();

        if (((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_UPDATED) != 0) ||
                (dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_UPDATED) != 0)  {
            int bgResId;
            if((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_ACTIVE) != 0){
                bgResId = R.drawable.bg_item_dragging_active_state;
                ViewUtils.clearState(holder.mContainer.getForeground());
            } else if((dragState & RecyclerViewDragDropManager.STATE_FLAG_DRAGGING) != 0){
                bgResId = R.drawable.bg_item_dragging_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_swipe_active_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_SWIPING) != 0) {
                bgResId = R.drawable.bg_item_swiping_state;
            } else {
                bgResId = R.drawable.bg_item_normal_state;
            }

            holder.mContainer.setBackgroundResource(bgResId);
        }

        // set swiping properties
        holder.setSwipeItemSlideAmount(item.isPinnedToSwipeLeft() ? RecyclerViewSwipeManager.OUTSIDE_OF_THE_WINDOW_LEFT : 0);
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
        return mProvider.getItem(position).getSwipeReactionType();
    }

    @Override
    public void onSetSwipeBackground(MyViewHolder holder, int position, int type) {
        int bgRes = 0;
        switch (type) {
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
                bgRes = R.color.bg_swipe_item_neutral;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_LEFT_BACKGROUND:
                bgRes = R.drawable.bg_swipe_item_left;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
                bgRes = R.drawable.bg_swipe_item_right;
                break;
        }

        holder.itemView.setBackgroundResource(bgRes);
    }

    @Override
    public int onSwipeItem(MyViewHolder holder, int position, int result) {
        switch (result) {
            // swipe right
            case RecyclerViewSwipeManager.RESULT_SWIPED_RIGHT:
                if (mProvider.getItem(position).isPinnedToSwipeLeft()) {
                    // pinned --- back to default position
                    return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_DEFAULT;
                } else {
                    // not pinned --- remove
                    return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM;
                }
                    // not pinned --- remove
                // swipe left -- pin
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
            mProvider.removeItem(position);
            notifyItemRemoved(position);

            if (mEventListener != null) {
                mEventListener.onItemRemoved(position);
            }
        }
        else if (reaction == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION) {
            item.setPinnedToSwipeLeft(true);
            notifyItemChanged(position);

            if (mEventListener != null) {
                mEventListener.onItemPinned(position);
            }
        }
        else {
                item.setPinnedToSwipeLeft(false);
        }
    }

    public EventListener getEventListener() {
        return mEventListener;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        // x, y --- relative from the itemView's top-left
        final View containerView = holder.mContainer;
        final View dragHandleView = holder.mDragHandle;

        final int offsetX = containerView.getLeft() + (int) (ViewCompat.getTranslationX(containerView) + 0.5f);
        final int offsetY = containerView.getTop() + (int) (ViewCompat.getTranslationY(containerView) + 0.5f);

        return ViewUtils.hitTest(dragHandleView, x - offsetX, y - offsetY);
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        if(fromPosition == toPosition) return;
        mProvider.moveItem(fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    public static class MyViewHolder extends AbstractDraggableSwipeableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;
        public View mDragHandle;

        public MyViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
            mDragHandle = v.findViewById(R.id.drag_handle);
        }

        @Override
        public View getSwipeableContainerView() {
            return mContainer;
        }
    }

    public interface EventListener {
        void onItemRemoved(int position);

        void onItemPinned(int position);

        void onItemViewClicked(View v, boolean pinned);
    }
}
