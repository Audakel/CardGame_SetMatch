package com.example.root.SetCardGamev2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by audakel on 9/30/14.
 */
class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        if (i == 0){
            fragment = new TabMatchismo();
        }

        if (i == 1){
            fragment = new TabSetCard();
        }

        if (i == 2){
            fragment = new TabScore();
        }

        if (i == 3){
            fragment = new TabSettings();
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

//    @Override
//    public CharSequence getPageTitle(int position){
//        CharSequence title = null;
//
//        if (position == 0){
//            title =  "Matchismo";
//        }
//
//        if (position == 1){
//            title = "Set";
//        }
//
//        if (position == 2){
//            title = "Scores";
//        }
//
//        if (position == 3){
//            title = "Settings";
//        }
//
//        return  title;
//
//    }
}