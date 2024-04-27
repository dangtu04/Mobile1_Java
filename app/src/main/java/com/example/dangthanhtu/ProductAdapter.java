package com.example.dangthanhtu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private boolean useProductItem;

    public ProductAdapter(Context context, List<Product> products, boolean useProductItem) {
        super(context, 0, products);
        this.useProductItem = useProductItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (useProductItem) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_item, parent, false);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
        }

        Product product = getItem(position);

        TextView title = convertView.findViewById(R.id.title);
        TextView price = convertView.findViewById(R.id.price);
        ImageView image = convertView.findViewById(R.id.image);

        title.setText(product.getTitle());
        price.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImage()).into(image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("product", product);
                getContext().startActivity(intent);
            }
        });

        if (useProductItem) {
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            checkBox.setChecked(product.isSelected());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    product.setSelected(isChecked);
                }
            });
        }

        return convertView;
    }
}
