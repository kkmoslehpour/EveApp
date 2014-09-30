package com.femmes.eveapp;
import com.femmes.eveapp.R;
import com.femmes.eveapp.AppController;
import com.femmes.eveapp.model.Item;

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

public class CustomGridAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Item> itemList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomGridAdapter(Activity activity, List<Item> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int location) {
        return itemList.get(location);
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
            convertView = inflater.inflate(R.layout.grid_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.item_image);
        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        TextView price = (TextView) convertView.findViewById(R.id.item_price);

        // getting movie data for the row
        Item I = itemList.get(position);

        // thumbnail image
        thumbNail.setImageUrl(I.getImage(), imageLoader);

        // title
        name.setText(I.getName());

        // rating
        price.setText(String.valueOf(I.getPrice()));

        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);

        // release year
        return convertView;
    }
}
