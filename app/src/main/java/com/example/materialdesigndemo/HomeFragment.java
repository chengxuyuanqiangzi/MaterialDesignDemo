package com.example.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/8.
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.recycler)
    RecyclerView mRecycerView;

    private Class[] items;
    private RecyclerView.Adapter mAdapter;

    public void updateContentList(Class[] _items){
        this.items = _items;
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter = new HomeListAdapter();
    }

    @Override
    public int getPageLayoutId() {
        return R.layout.fragment_main_context;
    }

    @Override
    public void initPageView() {
        if(mAdapter != null){
            mRecycerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void process() {

    }

    class HomeListAdapter extends RecyclerView.Adapter<ItemViewHolder>{

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_main_content_item,parent,false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            final Class cls = getItem(position);
            final String title = cls.getSimpleName().replace("Activity","");
            holder.titleView.setText(title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,cls);
                    intent.putExtra(BaseActivity.EXTRA_TITLE,title);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.length;
        }

        public Class getItem(int position){
            return items[position];
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView titleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleView = ButterKnife.findById(itemView, R.id.title);
        }
    }
}
