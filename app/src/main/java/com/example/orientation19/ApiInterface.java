package com.example.orientation19;

import com.example.orientation19.MyPojos.LoginData;
import com.example.orientation19.MyPojos.LoginResponse;

import com.example.orientation19.MyPojos.TshirtRegDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("user/login")
    Call<LoginResponse> requestLogin(@Body LoginData body);

    @POST("tshirt/register")
    Call<String> registerTshirt(@Header("Authorization") String JWT, @Body TshirtRegDetails tshirtRegDetails);

    @GET("tshirt/details")
    Call<TshirtRegDetails> checkTshirtRegistered(@Header("Authorization") String JWT);
}
