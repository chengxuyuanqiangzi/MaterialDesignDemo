package com.hzq.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/26.
 */
public class ViewPagerActivity extends BaseActivity {
    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int initLayoutId() {
        return R.layout.viewpager_layout;
    }

    @Override
    public void initPageView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 5 ; i++) {
            viewPagerAdapter.addFragment(new ViewPagerFragment());
        }
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        List<Fragment> fragments = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
