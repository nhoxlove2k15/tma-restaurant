package com.example.tmarestaurant.client;

import com.example.tmarestaurant.utils.MyConstant;
import lombok.Data;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Data
public class Connection {
    private Retrofit retrofit;
    private APIRetrofit apiRetrofit;
    private String url ;

    public Connection() {

    }

    public void connectToMLServer(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MyConstant.PATH_ML)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiRetrofit = retrofit.create(APIRetrofit.class);
    }
}
