package com.example.softwareproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String listRecipes[];
    int listImages[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String [] listRecipes, int[] listImages){
        this.context = ctx;
        this.listRecipes = listRecipes;
        this.listImages = listImages;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listRecipes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_list_view_row, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(listRecipes[position]);
        recipeImage.setImageResource(listImages[position]);
        return convertView;
    }
}
