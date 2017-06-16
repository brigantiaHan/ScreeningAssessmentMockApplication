package com.example.mingxiu.bottomnavigation;

import java.util.ArrayList;

/**
 * Created by mingxiu on 6/15/2017.
 */

public class group {
    private String Name;
    private ArrayList<child> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<child> Items) {
        this.Items = Items;
    }
}
