package com.femmes.eveapp;
import com.femmes.eveapp.R;
import com.femmes.eveapp.AppController;
import com.femmes.eveapp.model.SubCat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<SubCat> catItems;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<SubCat> catItems) {
        this.activity = activity;
        this.catItems = catItems;
    }

    @Override
    public int getCount() {
        return catItems.size();
    }

    @Override
    public Object getItem(int location) {
        return catItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView
//                .findViewById(R.id.thumbnail);
        TextView subCategory = (TextView) convertView.findViewById(R.id.subCat);
        TextView productUrl = (TextView) convertView.findViewById(R.id.url);
//        TextView genre = (TextView) convertView.findViewById(R.id.genre);
//        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        SubCat c = catItems.get(position);

        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        subCategory.setText(c.getSubCat());

        // rating
        productUrl.setText(c.getProductUrl());

        return convertView;
    }
}
