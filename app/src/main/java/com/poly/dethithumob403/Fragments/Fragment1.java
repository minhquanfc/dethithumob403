package com.poly.dethithumob403.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poly.dethithumob403.MainActivity;
import com.poly.dethithumob403.R;
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

public class Fragment1 extends Fragment {
    RecyclerView recyclerView;
    List<Product> productList;
    ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        recyclerView = view.findViewById(R.id.rc_view_2);

        productList = new ArrayList<>();

        adapter = new ProductAdapter(getContext(),productList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();

        return view;
    }
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