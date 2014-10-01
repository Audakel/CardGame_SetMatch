package com.example.root.SetCardGamev2;

//import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.app.ActionBar;

public class MyActivity extends FragmentActivity implements android.app.ActionBar.TabListener {

    android.app.ActionBar actionBar;
    ViewPager viewPager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_my);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);

        android.app.ActionBar.Tab tabMatchismo = actionBar.newTab();
        tabMatchismo.setText("Match").setTabListener(this);

        android.app.ActionBar.Tab tabSet = actionBar.newTab();
        tabSet.setText("Set").setTabListener(this);

        android.app.ActionBar.Tab tabScore = actionBar.newTab();
        tabScore.setText("Score").setTabListener(this);

        android.app.ActionBar.Tab tabSettings = actionBar.newTab();
        tabSettings.setText("Options").setTabListener(this);

        actionBar.addTab(tabMatchismo);
        actionBar.addTab(tabSet);
        actionBar.addTab(tabScore);
        actionBar.addTab(tabSettings);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {

    }
}




