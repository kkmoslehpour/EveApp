package com.femmes.eveapp;

import com.femmes.eveapp.CustomListAdapter;
import com.femmes.eveapp.AppController;
import com.femmes.eveapp.model.SubCat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import javax.xml.transform.ErrorListener;

public final class TestFragment extends ListFragment {
    private static final String TAG = TestFragment.class.getSimpleName();

    static String directory="http://www.evebyeves.cn/index.php/catalog/phone/cate/";
    private String jsonURL;
    private List<SubCat> subCatList= new ArrayList<SubCat>();
    private CustomListAdapter adapter;
    private String KEY_CONTENT;
    //private ListView listView;
//    private ProgressDialog pDialog;
//    private List<SubCat> mContent;

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();
        Log.d(TAG, " "+fragment.KEY_CONTENT);
        if(content=="Women") {fragment.jsonURL=directory+"3"; fragment.KEY_CONTENT="W";}
        else if(content=="Men") {fragment.jsonURL=directory+"47"; fragment.KEY_CONTENT="M";}
        else if(content=="Kids") {fragment.jsonURL=directory+"46"; fragment.KEY_CONTENT="K";}
        else if(content=="Jewelry") {fragment.jsonURL=directory+"9"; fragment.KEY_CONTENT="J";}
        else if(content=="Beauty") {fragment.jsonURL=directory+"20"; fragment.KEY_CONTENT="B";}
        else if(content=="Lifestyle") {fragment.jsonURL=directory+"58"; fragment.KEY_CONTENT="L";}

        fragment.subCatList =fragment.makeRequest(fragment.jsonURL);
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < 4; i++) {
//            builder.append(content).append(" ");
//        }
//        builder.deleteCharAt(builder.length() - 1);
//        fragment.mContent = builder.toString();

//        fragment.mContent = subCatList;
        Log.d(TAG, " "+fragment.KEY_CONTENT);
        return fragment;
    }
    //called after onCreate() *from activity*
    //but before onCreateView() *in fragment
    //The system calls this when creating the fragment.
    // Within your implementation, you should
    // initialize essential components of the fragment
    // that you want to retain when the fragment is
    // paused or stopped, then resumed.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " "+KEY_CONTENT);
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
//            mContent = savedInstanceState.getParcelable(KEY_CONTENT);
        }
    }

    //call layout inflater
    //The system calls this when it's time for the
    // fragment to draw its user interface for the
    // first time. To draw a UI for your fragment, you
    // must return a View from this method that is the
    // root of your fragment's layout. You can return
    // null if the fragment does not provide a UI.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_my, container, false);
        ListView listView;
//        listView = (ListView) getView().findViewById(android.R.id.list);
        listView = (ListView) rootView.findViewById(android.R.id.list);
//switch mContent with subCatList
        adapter = new CustomListAdapter(getActivity(), subCatList);

        listView.setAdapter(adapter);
//        pDialog = new ProgressDialog(getActivity());
//        pDialog.setMessage("Loading...");
//        pDialog.show();

//        TextView text = new TextView(getActivity());
//        text.setGravity(Gravity.CENTER);
//        text.setText(mContent);
//        text.setTextSize(20 * getResources().getDisplayMetrics().density);
//        text.setPadding(20, 20, 20, 20);
//
//        LinearLayout layout = new LinearLayout(getActivity());
//        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//        layout.setGravity(Gravity.CENTER);
//        layout.addView(text);
//
//        return layout;
        return rootView;

    }

//    private void hidePDialog() {
//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelableArray(KEY_CONTENT,mContent);
//        outState.putString(KEY_CONTENT, mContent);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, ""+position+"::"+id);
    }

    public List<SubCat> makeRequest(String jsonURL){
        JsonArrayRequest catReq = new JsonArrayRequest(jsonURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
//                        hidePDialog();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                SubCat subcat = new SubCat();
                                subcat.setSubCat(obj.getString("name"));
                                subcat.setProductUrl(obj.getString("url"));
                                subCatList.add(subcat);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                        //
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                            hidePDialog();
                    }
                });
        AppController.getInstance().addToRequestQueue(catReq);
        return subCatList;
    }
}