package com.example.dangthanhtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private ImageView ivCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Product product = (Product) getIntent().getSerializableExtra("product");

        TextView title = findViewById(R.id.tv_tittle);
        TextView price = findViewById(R.id.tv_price);
        ImageView image = findViewById(R.id.iv_image);
        TextView description = findViewById(R.id.tv_description);

        title.setText(product.getTitle());
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());
        Picasso.get().load(product.getImage()).into(image);

        // Xử lý nút back
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Xử lý nút thêm vào giỏ hàng
        Button btnAddToCart = findViewById(R.id.btn_addcart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().addProduct(product);
                Toast.makeText(DetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút giỏ hàng
        ivCart = findViewById(R.id.iv_cart);
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, CartProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
