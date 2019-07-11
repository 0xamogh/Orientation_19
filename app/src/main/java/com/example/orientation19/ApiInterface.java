package com.example.orientation19;

import com.example.orientation19.MyPojos.LoginData;
import com.example.orientation19.MyPojos.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("user/login")
    Call<LoginResponse> requestLogin(@Body LoginData body);
}
