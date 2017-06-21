package com.example.mingxiu.bottomnavigation;

/**
 * Created by mingxiu on 6/15/2017.
 */

public class childItem {
    private String code;
    private String color;

    public childItem(String code, String color){
        this.code = code;
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
