package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        setTitle("Verify Volunteers");

        final LinearLayout root=(LinearLayout)findViewById(R.id.root_3);
        if(root==null)
        {
//            Toast.makeText(verify_list.this,"Begining root is null",Toast.LENGTH_LONG).show();
        }
        final String admin = getIntent().getStringExtra("user");
        final String type=getIntent().getStringExtra("type");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .build();
        loginAdmin lin = retrofit.create(loginAdmin.class);
        Call<ResponseBody> call = lin.getData(admin,type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
//                    Toast.makeText(verify_list.this, result, Toast.LENGTH_SHORT).show();
                    int end = result.lastIndexOf("}") + 1;
                    int start = result.indexOf("{");
                    result = result.substring(start, end);
                    JSONObject resultJSONStr = new JSONObject(result);
                    final int nos = resultJSONStr.getInt("no");
//                    Toast.makeText(verify_list.this,"nos is " + nos,Toast.LENGTH_SHORT).show();
                    if (nos > 0) {
                        String res = resultJSONStr.getString("donors");
                        JSONArray donors = new JSONArray(res);
                        final TextView[] names = new TextView[nos];
                        final TextView[] bloods = new TextView[nos];
                        final TextView[] phnos = new TextView[nos];
                        for (int i = 0; i < nos; i++) {
                            JSONObject donors_1 = donors.getJSONObject(i);
                            final String name_text = donors_1.getString("name");
                            final String username_text = donors_1.getString("username");
//                        Toast.makeText(verify_list.this,"username is " + username_text,Toast.LENGTH_LONG).show();
                            final String bloodtext = donors_1.getString("blood_group");
                            final String phnotext = donors_1.getString("phone");
                            LinearLayout donr = new LinearLayout(verify_list.this);
                            donr.setOrientation(LinearLayout.HORIZONTAL);
                            TextView name = new TextView(verify_list.this);
                            name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                            TextView username = new TextView(verify_list.this);
                            username.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                            final TextView blood = new TextView(verify_list.this);
                            blood.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                            TextView phno = new TextView(verify_list.this);
                            phno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                            LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(190, 45);
                            LinearLayout.LayoutParams Params2 = new LinearLayout.LayoutParams(95, 45);
                            name.setLayoutParams(Params1);
                            username.setLayoutParams(Params1);
                            blood.setLayoutParams(Params2);
                            phno.setLayoutParams(Params1);
                            blood.setPadding(0, 0, 0, 0);
                            name.setPadding(0, 0, 0, 0);
                            phno.setPadding(0, 0, 0, 0);
                            blood.setText(bloodtext);
                            username.setText(username_text);
                            name.setText(name_text);
                            phno.setText(phnotext);
                            donr.addView(name);
                            donr.addView(username);
                            donr.addView(blood);
                            donr.addView(phno);
                            donr.setId(i);
                            donr.setClickable(true);
                            root.addView(donr);
                            names[i] = name;
                            bloods[i] = blood;
                            phnos[i] = phno;
                            donr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Toast.makeText(verify_list.this, "" + v.getId(), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(verify_list.this, Verify_a_donor.class);
                                    i.putExtra("name", name_text);
                                    i.putExtra("username", username_text);
                                    i.putExtra("blood", bloodtext);
                                    i.putExtra("phno", phnotext);
                                    i.putExtra("admin", admin);
                                    startActivity(i);
                                }
                            });
                        }
                    }
                    else{
//                        Toast.makeText(verify_list.this,"Inside else",Toast.LENGTH_SHORT).show();
                        TextView none = new TextView(verify_list.this);
                        none.setText("There are no unverified volunteers.");
                        root.addView(none);
                    }
                }

                catch(Exception e){
                    Toast.makeText(verify_list.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(verify_list.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onBackPressed() {
        Intent i = new Intent(verify_list.this,AdminHomepage.class);
        String username = getIntent().getStringExtra("user");
        i.putExtra("uname_text",username);
        i.putExtra("userType","admin");
        startActivity(i);
        super.onBackPressed();
    }
}
interface loginAdmin{
    @FormUrlEncoded
    @POST("verify_donors.php")
    Call<ResponseBody> getData(@Field("username") String username, @Field("type") String type);
}
