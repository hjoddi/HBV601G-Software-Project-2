package com.example.softwareproject2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter for use in displaying a recipe's comments in a RecyclerView.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<String> mData;

    public Adapter(ArrayList<String> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String comment = mData.get(position);
        holder.mTextView.setText(comment);
        holder.mTextView.setTextSize(16); // set text size to 16sp
        holder.mTextView.setTextColor(Color.BLACK); // set text color to black
        holder.mTextView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL)); // set font to sans-serif
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }

        public void bind(String data) {
            mTextView.setText(data);
        }
    }
}
