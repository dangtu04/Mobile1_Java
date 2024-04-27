package com.example.dangthanhtu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CartProductActivity extends AppCompatActivity {
    private ListView lvCartProducts;
    private ProductAdapter adapter;
    private List<Product> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product);

        lvCartProducts = findViewById(R.id.lv_cart_products);
        cartProducts = CartManager.getInstance().getCartProducts();
        adapter = new ProductAdapter(this, cartProducts, true);
        lvCartProducts.setAdapter(adapter);

        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateTotalPrice();
            }
        });

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        float totalPrice = 0;
        for (Product product : cartProducts) {
            if (product.isSelected()) {
                totalPrice += product.getPrice();
            }
        }

        TextView tvTotal = findViewById(R.id.tv_total);
        tvTotal.setText(String.valueOf(totalPrice));
    }
}
