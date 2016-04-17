package com.example.thelastlaugh.blooddonation;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Donor_list_lastpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String url = getIntent().getStringExtra("baseurl");
        final String ori=getIntent().getStringExtra("origin");
        final String dest=getIntent().getStringExtra("destination");
        final String total=url+ori+dest;
        Toast.makeText(Donor_list_lastpage.this, url + total, Toast.LENGTH_SHORT).show();
        final String error = getIntent().getStringExtra("error");
        final double lat = getIntent().getDoubleExtra("lat", 0);
        final double lon = getIntent().getDoubleExtra("lon", 0);
        super.onCreate(savedInstanceState);
        final String key="AIzaSyD3xQY1Z0P1CfZ8ZJjb6duDsbq7lD6niDM";
        //url=url+"/";
        setContentView(R.layout.activity_donor_list_lastpage);
        Toast.makeText(Donor_list_lastpage.this, "Justdonor last Error"+url, Toast.LENGTH_LONG).show();
            Retrofit retrofit1 = new Retrofit.Builder()
                    .baseUrl(url)
                    .build();
            disapi lin1 = retrofit1.create(disapi.class);
            Toast.makeText(Donor_list_lastpage.this, "just before googlr interaction", Toast.LENGTH_SHORT).show();
            Call<ResponseBody> call1 = lin1.getData("metric",ori,dest,key);
            call1.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        final String result1 = response.body().string();
                        Toast.makeText(Donor_list_lastpage.this,result1,Toast.LENGTH_LONG).show();
                        Log.e("Json String",result1);
                    }
                    catch(Exception e){
                        Toast.makeText(Donor_list_lastpage.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Donor_list_lastpage.this,t.toString(),Toast.LENGTH_LONG).show();
                }

            });



    }
    }
interface disapi{
    @GET("json?")
    Call<ResponseBody> getData(@Query("units") String units,@Query("origins") String origins,@Query("destinations") String dest,@Query("key") String key);

}