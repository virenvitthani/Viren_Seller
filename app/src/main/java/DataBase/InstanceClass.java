package DataBase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceClass {

    public static API_Interface CallApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mihirecommarce.000webhostapp.com/Mysite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_Interface service = retrofit.create(API_Interface.class);
        return service;
    }
}
