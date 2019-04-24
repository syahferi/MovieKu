package com.studio.karya.submission4.utils;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.studio.karya.submission4.menu.fragment.fav.FavFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    Bundle bundle;
    Fragment fragment;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            bundle = new Bundle();
            bundle.putString("type", "movie");
            fragment = new FavFragment();
            fragment.setArguments(bundle);
            return fragment;
        } else {
            bundle = new Bundle();
            bundle.putString("type", "tv");
            fragment = new FavFragment();
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "movie";
            case 1:
                return "tv";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
