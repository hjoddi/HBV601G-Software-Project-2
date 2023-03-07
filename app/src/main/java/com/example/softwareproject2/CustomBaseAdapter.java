package com.example.softwareproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> listRecipes;
    ArrayList<Integer> listImages;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, ArrayList<String> listRecipes, ArrayList<Integer> listImages){
        this.context = ctx;
        this.listRecipes = listRecipes;
        this.listImages = listImages;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listRecipes.size();
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
        convertView = inflater.inflate(R.layout.activity_search_result_list_view_row, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(listRecipes.get(position));
        recipeImage.setImageResource(listImages.get(position));
        return convertView;
    }
}
