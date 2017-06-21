package com.example.mingxiu.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mingxiu on 6/12/2017.
 */

public class TabtwoFragment extends Fragment {

    private SectionsPageAdapter mSectionPageAdapter;
    private Tab1Fragment tab1Fragment;
    private Tab2Fragment tab2Fragment;
    private Tab3Fragment tab3Fragment;
    private ViewPager viewPager;
    public TabtwoFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        tab1Fragment = new Tab1Fragment();
        tab2Fragment = new Tab2Fragment();
        tab3Fragment = new Tab3Fragment();
        viewPager = (ViewPager) view.findViewById(R.id.container);
        SetupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;


    }
    public void SetupViewPager(ViewPager viewPager){
        mSectionPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mSectionPageAdapter.addFragment(tab1Fragment,"SUB TAB ONE");
        mSectionPageAdapter.addFragment(tab2Fragment,"SUB TAB TWO");
        mSectionPageAdapter.addFragment(tab3Fragment,"SUB TAB THREE");
        viewPager.setAdapter(mSectionPageAdapter);
    }
}
