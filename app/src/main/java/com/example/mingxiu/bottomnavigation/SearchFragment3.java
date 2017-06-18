package com.example.mingxiu.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mingxiu on 6/14/2017.
 */

public class SearchFragment3 extends Fragment{

    private static TextView nameTextView;
    private static TextView codeTextView;
    private static TextView colorTextView;
    private static String name = "";
    private static String code = "";
    private static String color = "";
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search3, container, false);
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        codeTextView = (TextView) view.findViewById(R.id.codeTextView);
        colorTextView = (TextView) view.findViewById(R.id.colorTextView);

        return view;
    }

    public static void SetPage3Data(String n,String c,String col){


        name = n;
        code = c;
        color = col;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nameTextView.setText(name);
        codeTextView.setText(code);
        colorTextView.setText(color);

    }
}
