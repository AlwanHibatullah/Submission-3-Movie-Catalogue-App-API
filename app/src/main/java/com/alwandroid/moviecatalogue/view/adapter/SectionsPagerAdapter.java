package com.alwandroid.moviecatalogue.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alwandroid.moviecatalogue.R;
import com.alwandroid.moviecatalogue.view.fragment.MovieFragment;
import com.alwandroid.moviecatalogue.view.fragment.TvShowFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context aContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        aContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.movies,
            R.string.tv_show
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MovieFragment();
                break;
            case 1:
                fragment = new TvShowFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return aContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
