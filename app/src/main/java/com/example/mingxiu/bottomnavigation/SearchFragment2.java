package com.example.mingxiu.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by mingxiu on 6/14/2017.
 */

public class SearchFragment2 extends Fragment{
    public static final String TAG = "SearchFragment2";
    private ListView listView;
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> codes = new ArrayList<>();
    public static ArrayList<String> colors = new ArrayList<>();
    private int count = 0;
    public SearchFragment2(){}
    public static void setData(String n,String c,String color){
        names.add(n);
        codes.add(c);
        colors.add(color);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search2, container, false);

        MainActivity.fragment = MainActivity.searchFragment;
        listView = (ListView) view.findViewById(R.id.listView);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, names){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
               View view_list = super.getView(position, convertView, parent);
               TextView tv = (TextView) view_list.findViewById(android.R.id.text1);
               tv.setGravity(Gravity.CENTER);
               return view_list;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaboneFragment.setCode(3);
                SearchFragment3.SetPage3Data(names.get(position).toString(),codes.get(position).toString(),colors.get(position).toString());
                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, MainActivity.searchFragment3).commit();
            }
        });
    }
}
