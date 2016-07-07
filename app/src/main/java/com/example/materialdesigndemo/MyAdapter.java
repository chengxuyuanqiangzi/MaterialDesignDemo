package com.example.materialdesigndemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezhiqiang on 16/6/30.
 */
public class MyAdapter extends RecyclerSwipeAdapter<MyAdapter.MyHolder>{


//    private final onDragStartListener startdraglistener;
    private List<String> mDatas = new ArrayList<>();
    private LayoutInflater mInflater;
    public Context mcontext;

    public MyAdapter(Activity context) {
        for (int i = 0 ; i < 50 ; i++){
            mDatas.add("testData"+i);
        }
        mInflater = LayoutInflater.from(context);
        mcontext = context;
//        this.startdraglistener = (onDragStartListener) context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        MyHolder holder = new MyHolder(mInflater.inflate(
                R.layout.swipe_view_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.itemView.findViewById(R.id.left));
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                notifyItemRemoved(position);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });


//        holder.iv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    startdraglistener.onStartDrag(holder);
//                }
//                return false;
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipelayout;
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;
        LinearLayout layout;
        SwipeLayout swipeLayout;

        public MyHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            iv = (ImageView) view.findViewById(R.id.iv);
            layout = (LinearLayout) view.findViewById(R.id.layout);
            swipeLayout = (SwipeLayout) view.findViewById(R.id.swipelayout);
        }
    }

}
