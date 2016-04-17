package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class VolunteerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_homepage);

        setTitle("Volunteer Homepage");

        String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");
        final LinearLayout r=(LinearLayout) findViewById(R.id.root);
        final TextView tv = new TextView(VolunteerHomepage.this);
        if(r==null)
        {
//            Toast.makeText(VolunteerHomepage.this,"Begining root is null",Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(VolunteerHomepage.this,"Homepage of " + username,Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();


//        Toast.makeText(VolunteerHomepage.this,"Inside VHP "+ username,Toast.LENGTH_LONG).show();
        Intent i=new Intent(VolunteerHomepage.this,AndroidLocationServices.class);
        i.putExtra("username",username);
        startService(i);

        TextView volunteerName = (TextView)findViewById(R.id.volunteer_name);

        volunteerName.setText("Welcome, " + username);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .build();
        Voldondet a=retrofit.create(Voldondet.class);
        Call<ResponseBody> call = a.getData(username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
//                    Toast.makeText(VolunteerHomepage.this, result, Toast.LENGTH_SHORT).show();
                    int end = result.lastIndexOf("}") + 1;
                    int start = result.indexOf("{");
                    result=result.substring(start,end);
                    JSONObject resultJSONStr = new JSONObject(result);
                    String error=resultJSONStr.getString("error");
                    if(error.equalsIgnoreCase("false"))
                    {
                        int nos = resultJSONStr.getInt("nos");
//                        TextView[] tv=new TextView[1];
                        tv.setText("You have donated blood " + nos + " time(s). Congratulations and thank you!");
                        tv.setTextSize(15);
                        tv.setPadding(10,10,10,10);
                        r.addView(tv);

                    }
                    else{
//                        TextView[] tv=new TextView[1];
                        tv.setText("You haven't donated any blood till now. Donate blood, save a life.");
                        tv.setTextSize(15);
                        tv.setPadding(10,10,10,10);
                        r.addView(tv);
                    }
                } catch (Exception e) {
                    Toast.makeText(VolunteerHomepage.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(VolunteerHomepage.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
interface Voldondet{
    @FormUrlEncoded
    @POST("donadone.php")
    Call<ResponseBody> getData(@Field("username") String uname_text);
}