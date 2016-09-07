package com.hzq.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.materialdesigndemo.R;

/**
 * Created by hezhiqiang on 16/7/26.
 */
public class ViewPagerFragment extends Fragment {

    public static final int[] ResIds = {R.mipmap.cheese_1,R.mipmap.cheese_2,R.mipmap.cheese_3,R.mipmap.cheese_4,R.mipmap.cheese_5};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_layout,container,false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        int index = (int)(Math.random()*4+1);
        img.setImageResource(ResIds[index]);
        return view;
    }
}
