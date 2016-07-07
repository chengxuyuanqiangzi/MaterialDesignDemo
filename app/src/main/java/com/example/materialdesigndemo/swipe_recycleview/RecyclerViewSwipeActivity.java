package com.example.materialdesigndemo.swipe_recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

/**
 * Created by hezhiqiang on 16/7/1.
 */
public class RecyclerViewSwipeActivity  extends BaseActivity {


    private RecyclerView recycleView;
//    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewSwipeManager mRecyclerViewSwipeManager;
    private RecyclerViewTouchActionGuardManager mRecyclerViewTouchActionGuardManager;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    private DataProvader provader;
    @Override
    public int initLayoutId() {
        return R.layout.recycler_view_swipe;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycleView = (RecyclerView) findViewById(R.id.recyleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));//定义样式
        init();
    }

    private void init(){
        mRecyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        mRecyclerViewTouchActionGuardManager.setInterceptVerticalScrollingWhileAnimationRunning(true);
        mRecyclerViewTouchActionGuardManager.setEnabled(true);
        mRecyclerViewSwipeManager = new RecyclerViewSwipeManager();
        mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
//        mRecyclerViewDragDropManager.setDraggingItemShadowDrawable();
        //adapter
        provader = new DataProvader();
        final RecyclerAdapter myItemAdapter = new RecyclerAdapter(this,provader);
        myItemAdapter.setEventListener(new RecyclerAdapter.EventListener() {
            @Override
            public void onItemRemoved(int position) {
                Snackbar snackbar = Snackbar.make(
                        findViewById(R.id.container),
                        "test",
                        Snackbar.LENGTH_LONG);

                snackbar.setAction("撤消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = provader.undoLastRemoval();
                        if(position>0) {
                            mWrappedAdapter.notifyItemInserted(position);
                            recycleView.scrollToPosition(position);
                        }
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.snackbar_action_color_done));
                snackbar.show();
            }

            @Override
            public void onItemPinned(final int position) {
                Snackbar.make(recycleView,"position="+position,Snackbar.LENGTH_SHORT).show();
//                AlertDialog dialog = new AlertDialog(RecyclerViewSwipeActivity.this);
                provader.getItem(position).setPinnedToSwipeLeft(false);
                mWrappedAdapter.notifyItemChanged(position);
            }

            @Override
            public void onItemViewClicked(View v, boolean pinned) {
                Snackbar.make(recycleView,"pinned="+pinned,Snackbar.LENGTH_SHORT).show();
            }
        });

//        mAdapter = myItemAdapter;

        mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(myItemAdapter);
        mWrappedAdapter = mRecyclerViewSwipeManager.createWrappedAdapter(mWrappedAdapter);      // wrap for swiping

//        final GeneralItemAnimator animator = new SwipeDismissItemAnimator();

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Disable the change animation in order to make turning back animation of swiped item works properly.
//        animator.setSupportsChangeAnimations(false);

        RecyclerView.ItemAnimator animator1 = recycleView.getItemAnimator();
        if (animator1 instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator1).setSupportsChangeAnimations(false);
        }

        recycleView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
//        recycleView.setItemAnimator(animator);
        mRecyclerViewTouchActionGuardManager.attachRecyclerView(recycleView);
        mRecyclerViewSwipeManager.attachRecyclerView(recycleView);
        mRecyclerViewDragDropManager.attachRecyclerView(recycleView);
    }

    @Override
    protected void onPause() {
        mRecyclerViewDragDropManager.cancelDrag();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mRecyclerViewDragDropManager != null) {
            mRecyclerViewDragDropManager.release();
            mRecyclerViewDragDropManager = null;
        }
        if (mRecyclerViewSwipeManager != null) {
            mRecyclerViewSwipeManager.release();
            mRecyclerViewSwipeManager = null;
        }

        if (mRecyclerViewTouchActionGuardManager != null) {
            mRecyclerViewTouchActionGuardManager.release();
            mRecyclerViewTouchActionGuardManager = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
//        mAdapter = null;
        super.onDestroy();
    }
}
