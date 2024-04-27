package com.example.dangthanhtu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dangthanhtu.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ListView lvProduct;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter bannerPagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(bannerPagerAdapter);

        lvProduct = findViewById(R.id.lv_product);
        productList = new ArrayList<>();
        //note
        adapter = new ProductAdapter(this, productList, false);

        lvProduct.setAdapter(adapter);

        ImageButton ibCart = findViewById(R.id.ib_cart);
        ibCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartProductActivity.class);
                startActivity(intent);
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        ApiService.apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(HomeActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}