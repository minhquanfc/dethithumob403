package com.poly.dethithumob403.api;

import com.poly.dethithumob403.model.Prd;
import com.poly.dethithumob403.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("getall.json")
    Call<Prd> getProduct();
}
