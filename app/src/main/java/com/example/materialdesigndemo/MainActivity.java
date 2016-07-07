package com.example.materialdesigndemo;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.materialdesigndemo.swipe_recycleview.BtnRecyclerViewSwipeActivity;
import com.example.materialdesigndemo.swipe_recycleview.RecyclerViewSwipeActivity;
import com.hzq.recycler.ItemAnimatorRecyclerActivity;
import com.hzq.recycler.RecycleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private TextView title,desc;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Demo");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        initHeaderView();
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
        navigationView.setNavigationItemSelectedListener(this);
        View rootView = navigationView.getHeaderView(0);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        title = (TextView) rootView.findViewById(R.id.title);
        desc = (TextView) rootView.findViewById(R.id.desc);

        title.setText("ANDROID STUDIO");
        desc.setText("hezhiiang@haizhi.com");
    }

    public void startSwipeView(View view){
        launcherActivity(RecyclerViewSwipeActivity.class);
    }

    public void startAdapterAnimations(View view){
        launcherActivity(RecycleActivity.class);
    }

    public void startItemAnimators(View view){
        launcherActivity(ItemAnimatorRecyclerActivity.class);
    }

    public void startBtnSwipeLayout(View view){
        launcherActivity(BtnRecyclerViewSwipeActivity.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.animations) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
