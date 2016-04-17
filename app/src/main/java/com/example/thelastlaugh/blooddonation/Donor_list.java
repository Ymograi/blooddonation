package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Donor_list extends AppCompatActivity {
    double lat,lon;
    String bld;
    String url,result,result1,error1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        setTitle("Search for Volunteers");

        final String error = "";
        final double lat,lon;
        String bld;
        final String[] url = new String[4];
        final String[] result = new String[1];
        String result1;
        final String[] error1 = new String[1];
        lat= getIntent().getDoubleExtra("lat",0);
        lon=getIntent().getDoubleExtra("lon",0);
        bld=getIntent().getStringExtra("bg");
        final String username = getIntent().getStringExtra("username");
        TextView lat1=(TextView)findViewById(R.id.textView11);
        TextView lon1=(TextView)findViewById(R.id.textView13);
        TextView bldgr=(TextView)findViewById(R.id.textView12);
        lat1.setText("Lat: "+lat);
        lon1.setText("Lon: "+lon);
        bldgr.setText("Blood Group: "+bld);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .build();
        Donorlist lin = retrofit.create(Donorlist.class);
//        Toast.makeText(Donor_list.this, "just before getting donor list", Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = lin.getData(lat,lon,bld);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try
                {
                    result[0] = response.body().string();
//                    Toast.makeText(Donor_list.this, result[0], Toast.LENGTH_LONG).show();
                    int end = result[0].lastIndexOf("}") + 1;
                    int start = result[0].indexOf("{");
                    result[0] = result[0].substring(start, end);
//                    Toast.makeText(Donor_list.this, result[0], Toast.LENGTH_SHORT).show();
                    JSONObject resultJSONStr = new JSONObject(result[0]);
//                    Toast.makeText(Donor_list.this, "yoyy", Toast.LENGTH_SHORT).show();
                    final String error=resultJSONStr.getString("error");
                    //error1=error;
                    final String error_msg=resultJSONStr.getString("error_msg");
                    final int nos=resultJSONStr.getInt("no");
                    url[0] ="https://maps.googleapis.com/maps/api/distancematrix/";
                    url[1] = +lat+","+lon;
                    //Toast.makeText(Donor_list.this, url, Toast.LENGTH_SHORT).show();
                    String destination="";
                    if(error.equalsIgnoreCase("false")){
                        //error1 ="false";
                        if(nos>0) {
//                            Toast.makeText(Donor_list.this, "Inside if error = false", Toast.LENGTH_LONG).show();
                            String res = resultJSONStr.getString("donors");
                            JSONArray donors = new JSONArray(res);
                            for (int i = 0; i < nos; i++) {
                                JSONObject donors_i = donors.getJSONObject(i);
                                if (i == (nos - 1))
                                    destination = destination + donors_i.getString("lat") + "," + donors_i.getString("lon");
                                else
                                    destination = destination + donors_i.getString("lat") + "," + donors_i.getString("lon") + "|";
                            }
                        }
                        //final String url1= url[0] +app+"?key=AIzaSyD3xQY1Z0P1CfZ8ZJjb6duDsbq7lD6niDM";
                        url[2]=destination;
                        Intent i=new Intent(Donor_list.this,Donor_list_lastpage.class);
                        i.putExtra("error", error);
                        i.putExtra("baseurl", url[0]);
                        i.putExtra("origin",url[1]);
                        i.putExtra("destination",url[2]);
//                        Toast.makeText(Donor_list.this, "Base : "+url[0]+"Origin: "+url[1]+"Destination :"+destination,Toast.LENGTH_LONG).show();
                        i.putExtra("lat",lat);
                        i.putExtra("lon",lon);
                        if(nos>0)
                            i.putExtra("donors",resultJSONStr.getString("donors"));
                        else
                            i.putExtra("donors","");
                        i.putExtra("nos",nos);
                        i.putExtra("username",username);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(Donor_list.this,"Error Occured",Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){Toast.makeText(Donor_list.this,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Donor_list.this,SeekerHomepage.class);
        String username = getIntent().getStringExtra("username");
        i.putExtra("uname_text",username);
        i.putExtra("userType","seeker");
        startActivity(i);
        super.onBackPressed();
    }
}
interface Donorlist{
    @FormUrlEncoded
    @POST("search_donors.php")
    Call<ResponseBody> getData(@Field("lat") double lat, @Field("lon") double lon,@Field("bld") String bld);
}
