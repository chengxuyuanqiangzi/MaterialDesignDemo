package com.hzq.espresso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/8/8.
 */
public class TestActivity extends BaseActivity {
    @InjectView(R.id.txt)
    TextView txt;

    @Override
    public int initLayoutId() {
        return R.layout.test_layout;
    }

    @Override
    public void initPageView() {
        Bundle bundle = getIntent().getBundleExtra("b");
        if(bundle!=null) {
//            Long[] l = (Long[]) getIntent().getSerializableExtra("l");
            long[] l = bundle.getLongArray("l");
            txt.setText(l.length+"");
        }
    }

    public static void invoke(Context context,Long[] l){
        Intent intent = new Intent(context,TestActivity.class);
        intent.putExtra("l",l);
        context.startActivity(intent);
    }

    public static void invoke1(Context context,long[] l){
        Intent intent = new Intent(context,TestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLongArray("l",l);
//        bundle.putSerializable("l",l);
        intent.putExtra("b",bundle);
        context.startActivity(intent);
    }

}
