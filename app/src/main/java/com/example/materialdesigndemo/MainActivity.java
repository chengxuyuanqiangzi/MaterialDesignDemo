package com.example.materialdesigndemo;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.deeplinkdispatch.DeepLink;
import com.hzq.espresso.EspressoDemo;
import com.hzq.espresso.HierarchyActivity;
import com.hzq.espresso.ListViewActivity;
import com.hzq.pullzoomview.PullRecycleViewActivity;
import com.hzq.pullzoomview.PullZoomViewDemoActivity;
import com.hzq.recycler.RecyclerItemAnimatorActivity;
import com.hzq.recycler.RecyclerViewActivity;
import com.hzq.recycler.RefreshLayoutDemoActivity;
import com.hzq.recycler.itemtouchhelper.ItemTouchHelperActivity;
import com.hzq.recycler.swipe.RecyclerViewSwipeBtnActivity;
import com.hzq.recycler.swipe.RecyclerViewSwipeActivity;
import com.hzq.widgets.CircleProgressBarActivity;
import com.hzq.widgets.FresciDemoActivity;
import com.hzq.widgets.ViewPagerActivity;
import com.hzq.widgets.WidgetsDemoActivity;

import java.util.HashMap;

@DeepLink("hzq://example.com/deepLink")
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private TextView title,desc;
    DrawerLayout drawer;
    private int currentNavigationId;
    private static HashMap<Integer,Class[]> sNavigationMap;

    static {
        Class[] recyclerView = {
                RecyclerViewActivity.class,
                RecyclerItemAnimatorActivity.class,
                RecyclerViewSwipeActivity.class,
                RecyclerViewSwipeBtnActivity.class,
                RefreshLayoutDemoActivity.class,
                ItemTouchHelperActivity.class
        };

        Class[] pullzoomview = {
                PullZoomViewDemoActivity.class,
                PullRecycleViewActivity.class
        };

        Class[] widgets = {
                WidgetsDemoActivity.class,
                ViewPagerActivity.class,
                CircleProgressBarActivity.class
        };

        Class[] liberary = {
                FresciDemoActivity.class
        };

        Class[] animations = {

        };

        Class[] espresso = {
                EspressoDemo.class,
                HierarchyActivity.class,
                ListViewActivity.class
        };
        sNavigationMap = new HashMap<>();
        sNavigationMap.put(R.id.recyclerview,recyclerView);
        sNavigationMap.put(R.id.animations,animations);
        sNavigationMap.put(R.id.widgets,widgets);
        sNavigationMap.put(R.id.liberary,liberary);
        sNavigationMap.put(R.id.pull_zoom,pullzoomview);
        sNavigationMap.put(R.id.espresso,espresso);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Demo");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        initHeaderView();
        initFragment();
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    private void initHeaderView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            View rootView = navigationView.getHeaderView(0);
            imageView = (ImageView) rootView.findViewById(R.id.imageView);
            title = (TextView) rootView.findViewById(R.id.title);
            desc = (TextView) rootView.findViewById(R.id.desc);

            title.setText("ANDROID STUDIO");
            desc.setText("hezhiiang@haizhi.com");
        }
    }

    private  void initFragment(){
        currentNavigationId = R.id.recyclerview;
        HomeFragment fragment = new HomeFragment();
        fragment.updateContentList(sNavigationMap.get(currentNavigationId));
        getSupportFragmentManager().beginTransaction().replace(R.id.content_view,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if(sNavigationMap.containsKey(item.getItemId())){
            setTitle(item.getTitle());
            currentNavigationId = item.getItemId();
            HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.content_view);
            if(fragment != null){
                fragment.updateContentList(sNavigationMap.get(currentNavigationId));
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
