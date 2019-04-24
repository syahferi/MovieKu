package com.studio.karya.submission4.menu.fragment.fav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.utils.PagerAdapter;


public class FavContainerFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tab;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        tab = view.findViewById(R.id.tab_fav);
        viewPager = view.findViewById(R.id.viewpager_fav);
        setTab();
        return view;
    }

    private void setTab() {
        if (getFragmentManager() != null) {
            PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(pagerAdapter);
            tab.setupWithViewPager(viewPager);
        }
    }
}
