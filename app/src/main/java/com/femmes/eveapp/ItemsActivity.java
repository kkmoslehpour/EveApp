package com.femmes.eveapp;

import com.femmes.eveapp.model.Item;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.femmes.eveapp.CustomGridAdapter;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.femmes.eveapp.model.ShoppingItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends Activity{

    private String itemdirectory ="http://www.evebyeves.com.cn/index.php/catalog/phone/show/";
    private String itemjsonURL;
    private String directory = "http://www.evebyeves.com.cn/index.php/catalog/phone/product/";
    private String jsonURL;
    private CustomGridAdapter adapter;
//    private List<ShoppingItems> shopItems= new ArrayList<ShoppingItems>();
    private List<Integer> entityidArray = new ArrayList<Integer>();
    private List<Item> listItem = new ArrayList<Item>();
    private GridView gridView;
    int numberofgoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras= getIntent().getExtras();
        int categoryid=extras.getInt("catid");
        String actionbartitle=extras.getString("title");
        drawCustomActionBar(actionbartitle);
        setContentView(R.layout.activity_view_grid);
        Log.d("derp", "femmes::" + categoryid);
        jsonURL=directory+categoryid;
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new CustomGridAdapter(this, listItem);
        gridView.setAdapter(adapter);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, jsonURL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        JSONObject obj = response;
//                        ShoppingItems shoppingItems = new ShoppingItems();
                        try {
//                            shoppingItems.setCatid(obj.getInt("catid"));
//                            shoppingItems.setTitle(obj.getString("title"));
//                            shoppingItems.setGoodsnum(obj.getInt("goodsnum"));
//                            shoppingItems.setjArray(obj.getJSONArray("goodsdata"));
                            numberofgoods=obj.getInt("goodsnum");
                            JSONArray ja = obj.getJSONArray("goodsdata");
                            for(int i=0; i<ja.length();i++){
                                JSONObject item = ja.getJSONObject(i);
//                                Log.d("Response", " "+item.getInt("entity_id"));
//                                Log.d("Response", " "+item.getString("sku"));
//                                Log.d("Response", " "+item.getString("thumb"));
                                entityidArray.add(item.getInt("entity_id"));
                            }
//                            shopItems.add(shoppingItems);
                            //new call
                            TextView tv = (TextView)findViewById(R.id.headertitle);
                            tv.setText(numberofgoods+" items");
                            for(int i=0; i<entityidArray.size(); i++) {
                                requestItem(entityidArray.get(i));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getRequest);
    }

    public void goBack(View v){
        Log.d("actiobar:", "clicked");
//        NavUtils.navigateUpFromSameTask(this);
        onBackPressed();
    }
    public void requestItem(int entityid){
        itemjsonURL=itemdirectory+entityid;
        JsonObjectRequest getItem = new JsonObjectRequest(Request.Method.GET, itemjsonURL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        JSONObject obj = response;
                        try {
                            Item item = new Item();
                            item.setName(obj.getString("name"));
                            item.setImage("http://www.evebyeves.cn/media/catalog/product"+obj.getString("image"));
                            item.setPrice(obj.getDouble("price"));
                            listItem.add(item);
//                            Log.d("Response", " "+obj.getInt("entity_id"));
//                            Log.d("Response", " "+obj.getDouble("price"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getItem);
    }

    // ACTION BAR
    public void drawCustomActionBar(String actionbartitle){
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(this).inflate(R.layout.actionbar_shopping, null);
        actionBar.setCustomView(customNav, lp1);
        TextView tv = (TextView)findViewById(R.id.page_title);
        tv.setText(actionbartitle);
    }
}

