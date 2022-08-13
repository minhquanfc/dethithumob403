package com.poly.dethithumob403.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.dethithumob403.R;

public class ProductHolder extends RecyclerView.ViewHolder {
    TextView tv_id,tv_name,tv_price;
    ImageView img;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        tv_id=itemView.findViewById(R.id.tv_id);
        tv_name=itemView.findViewById(R.id.tv_name);
        tv_price=itemView.findViewById(R.id.tv_price);
        img=itemView.findViewById(R.id.img);
    }
}
