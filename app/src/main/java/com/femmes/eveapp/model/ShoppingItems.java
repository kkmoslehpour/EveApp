package com.femmes.eveapp.model;

import org.json.JSONArray;

import java.util.ArrayList;

public class ShoppingItems {
    private String title;
    private int catid, goodsnum;
    private JSONArray jArray;

    public ShoppingItems() {
    }

    public ShoppingItems(String title, int catid, int goodsnum,
        JSONArray jArray) {
            this.title = title;
            this.catid = catid;
            this.goodsnum = goodsnum;
            this.jArray = jArray;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }

    public int getCatid(){return catid;}
    public void setCatid(int catid){this.catid=catid;}

    public int getGoodsnum() {return goodsnum;}
    public void setGoodsnum(int goodsnum){this.goodsnum=goodsnum;}

    public JSONArray getjArray(){ return jArray;}
    public void setjArray(JSONArray jArray){ this.jArray= jArray;}
}
