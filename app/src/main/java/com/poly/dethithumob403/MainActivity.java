package com.poly.dethithumob403;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.poly.dethithumob403.adapter.ProductAdapter;
import com.poly.dethithumob403.api.ApiService;
import com.poly.dethithumob403.model.Prd;
import com.poly.dethithumob403.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    List<Product> productList;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rc_view_product);
        textView=findViewById(R.id.tv_dongia);

        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(broadcastReceiver, new IntentFilter("Tongtien"));


        productList = new ArrayList<>();

        adapter = new ProductAdapter(MainActivity.this,productList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();
    }
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            int tongtien = intent.getIntExtra("tongtien", 0);
            textView.setText("Đơn giá trung bình: "+tongtien);
        }
    };

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Prd> call = apiService.getProduct();
        call.enqueue(new Callback<Prd>() {
            @Override
            public void onResponse(Call<Prd> call, Response<Prd> response) {
                if (response.isSuccessful())
                {
                    Prd prd = response.body();
                    List<Product> products = prd.getProducts();

                    for (Product product: products)
                    {
                        productList.add(product);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Prd> call, Throwable t) {

            }
        });
    }
}