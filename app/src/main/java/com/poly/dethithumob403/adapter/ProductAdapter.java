package com.poly.dethithumob403.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.dethithumob403.R;
import com.poly.dethithumob403.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {
    Context context;
    List<Product> productList;
    int tongtien = 0;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = productList.get(position);
        holder.tv_id.setText(product.getPid());
        holder.tv_name.setText(product.getName());
        holder.tv_price.setText(String.valueOf(product.getPrice()));
        if (position % 2==0){
//            holder.img.setImageResource(R.drawable.star);

        }else {
            holder.img.setImageResource(R.drawable.star);
        }

        for(int i = 0; i< productList.size(); i++)
        {
            tongtien += productList.get(i).getPrice();
        }
        int dongia = tongtien/productList.size();
        Intent intent = new Intent("Tongtien");
        intent.putExtra("tongtien", dongia);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
