package com.example.thelastlaugh.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.LinearLayout;
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

public class verify_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_list);
        final LinearLayout root=(LinearLayout)findViewById(R.id.root_3);
        final String username = getIntent().getStringExtra("user");
        final String type=getIntent().getStringExtra("type");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .build();
        loginAdmin lin = retrofit.create(loginAdmin.class);
        Call<ResponseBody> call = lin.getData(username,type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Toast.makeText(verify_list.this, result, Toast.LENGTH_SHORT).show();
                    int end = result.lastIndexOf("}") + 1;
                    int start = result.indexOf("{");
                    result = result.substring(start, end);
                    JSONObject resultJSONStr = new JSONObject(result);
                    String res=resultJSONStr.getString("donors");
                    JSONArray donors=new JSONArray(res);
                    final int nos=resultJSONStr.getInt("no");
                    final TextView[] names=new TextView[nos];
                    final TextView[] bloods=new TextView[nos];
                    final TextView[] phnos=new TextView[nos];
                    for(int i=0;i<nos;i++)
                    {
                        JSONObject donors_1=donors.getJSONObject(i);
                        String name_text=donors_1.getString("name");
                        String bloodtext=donors_1.getString("blood_group");
                        String phnotext=donors_1.getString("phone");
                        LinearLayout donr=new LinearLayout(verify_list.this);
                        donr.setOrientation(LinearLayout.HORIZONTAL);
                        TextView name=new TextView(verify_list.this);
                        name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                        TextView blood=new TextView(verify_list.this);
                        blood.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                        TextView phno=new TextView(verify_list.this);
                        phno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                        LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(190,45);
                        LinearLayout.LayoutParams Params2 = new LinearLayout.LayoutParams(190,45);
                        name.setLayoutParams(Params1);
                        blood.setLayoutParams(Params1);
                        phno.setLayoutParams(Params1);
                        blood.setPadding(10, 10, 0, 0);
                        name.setPadding(0, 0, 0, 0);
                        phno.setPadding(50, 0, 0, 0);
                        blood.setText(bloodtext);
                        name.setText(name_text);
                        phno.setText(phnotext);
                        donr.addView(name);
                        donr.addView(blood);
                        donr.addView(phno);
                        donr.setId(i);
                        donr.setClickable(true);
                        root.addView(donr);
                        names[i]=name;
                        bloods[i]=blood;
                        phnos[i]=phno;
                        donr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(verify_list.this,""+v.getId(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                catch(Exception e){
                    Toast.makeText(verify_list.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
interface loginAdmin{
    @FormUrlEncoded
    @POST("verify_donors.php")
    Call<ResponseBody> getData(@Field("username") String username, @Field("type") String type);
}
