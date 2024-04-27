package com.example.dangthanhtu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class BannerAdapter extends ArrayAdapter<Integer> {
    public BannerAdapter(Context context, Integer[] banners) {
        super(context, 0, banners);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_banner, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.image);
        image.setImageResource(getItem(position));

        return convertView;
    }
}
