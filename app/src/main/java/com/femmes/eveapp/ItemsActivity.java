package com.femmes.eveapp;

import com.femmes.eveapp.model.Item;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.femmes.eveapp.CustomGridAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras= getIntent().getExtras();
        int categoryid=extras.getInt("catid");
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
}

