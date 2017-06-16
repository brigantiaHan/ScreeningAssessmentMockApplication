package com.example.mingxiu.bottomnavigation;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * Created by mingxiu on 6/12/2017.
 */

public class TabthreeFragment extends Fragment {
    private ExpandableListView expandList;
    private ArrayList<group> expandListItems;
    private ExpandAdapter expandAdapter;
    private static ArrayList<group> group_list = new ArrayList<group>();
    private static ArrayList<child> child_list;
    private DatabaseHelper databaseHelper;
    private int previousGroup = -1;

    public TabthreeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deals, container, false);
        expandList = (ExpandableListView) view.findViewById(R.id.exp_list);
        databaseHelper = new DatabaseHelper(this.getContext());

        return view;
    }


    private void retrieveAll() {

        Cursor res = databaseHelper.getAllData();
        if (res.getCount() == 0) {
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        group_list.clear();
        while (res.moveToNext()) {
            String name = res.getString(1);
            String code = res.getString(2);
            String color = res.getString(3);
            group g = new group();
            g.setName(name);
            child_list = new ArrayList<child>();
            child ch1_1 = new child();
            childItem item = new childItem(code, color);
            ch1_1.setItem(item);
            child_list.add(ch1_1);
            g.setItems(child_list);
            group_list.add(g);

        }

    }
    public group getGroup(int groupPosition){
        return group_list.get(groupPosition);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveAll();
        expandListItems = group_list;
        expandAdapter = new ExpandAdapter(this.getContext(), expandListItems);
        expandList.setAdapter(expandAdapter);

        expandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (previousGroup != groupPosition) {
                    expandList.collapseGroup(previousGroup);

                }
                previousGroup = groupPosition;
            }
        });
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandList.isGroupExpanded(groupPosition)) {

                } else {

                }
                return false;
            }
        });
    }




}
