package com.example.tmarestaurant.client;

import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIRetrofit {
    @POST("/comment/predictions")
    Call<CommentResponseFromML> postComment(@Body RequestBody requestBody);
}
