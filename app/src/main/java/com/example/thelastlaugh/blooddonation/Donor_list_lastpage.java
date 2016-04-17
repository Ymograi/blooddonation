package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Donor_list_lastpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_donor_list_lastpage);
        super.onCreate(savedInstanceState);

        setTitle("Search for Volunteers");

        final LinearLayout root = (LinearLayout) findViewById(R.id.root_donor);
        if(root==null)
        {
//            Toast.makeText(Donor_list_lastpage.this,"Begining root is null",Toast.LENGTH_LONG).show();
        }
        final String url = getIntent().getStringExtra("baseurl");
        final String ori=getIntent().getStringExtra("origin");
        final String dest=getIntent().getStringExtra("destination");
        final String donors = getIntent().getStringExtra("donors");
        final int nos = getIntent().getIntExtra("nos",-1);
        if(nos <= 0)
        {
            TextView sorry = new TextView(Donor_list_lastpage.this);
            sorry.setText("There are no nearby donors matching the requested blood group.");
            root.addView(sorry);
        }
        else {
            final String total = url + ori + dest;
//            Toast.makeText(Donor_list_lastpage.this, url + total, Toast.LENGTH_SHORT).show();
            final String error = getIntent().getStringExtra("error");
            final double lat = getIntent().getDoubleExtra("lat", 0);
            final double lon = getIntent().getDoubleExtra("lon", 0);
            final String key = "AIzaSyD3xQY1Z0P1CfZ8ZJjb6duDsbq7lD6niDM";
            //url=url+"/";

//        Toast.makeText(Donor_list_lastpage.this, "Justdonor last Error"+url, Toast.LENGTH_LONG).show();
            Retrofit retrofit1 = new Retrofit.Builder()
                    .baseUrl(url)
                    .build();
            disapi lin1 = retrofit1.create(disapi.class);
//            Toast.makeText(Donor_list_lastpage.this, "just before googlr interaction", Toast.LENGTH_SHORT).show();
            Call<ResponseBody> call1 = lin1.getData("metric", ori, dest, key);
            call1.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONArray donorsArray = new JSONArray(donors);
                        final String result1 = response.body().string();
//                        Toast.makeText(Donor_list_lastpage.this, result1, Toast.LENGTH_LONG).show();
                        Log.e("Json String", result1);
                        JSONObject googleResponse = new JSONObject(result1);
                        JSONArray rows = googleResponse.getJSONArray("rows");
                        JSONObject elements_string = rows.getJSONObject(0);
                        JSONArray elements = elements_string.getJSONArray("elements");

                        JSONArray listOfDonors = new JSONArray();
                        int count = 0;

                        for (int i = 0; i < nos; i++) {
                            JSONObject donor = donorsArray.getJSONObject(i);
                            JSONObject element = elements.getJSONObject(i);
                            String status = element.getString("status");
                            if (status.equalsIgnoreCase("OK")) {
                                JSONObject distance = element.getJSONObject("distance");
                                String distanceText = distance.getString("text");
                                int distanceValue = distance.getInt("value");
                                String donorUsername = donor.getString("username");
                                String donorPhone = donor.getString("phone");
                                JSONObject donorDistance = new JSONObject();
                                donorDistance.put("username", donorUsername);
                                donorDistance.put("value", distanceValue);
                                donorDistance.put("text", distanceText);
                                donorDistance.put("phone", donorPhone);
                                listOfDonors.put(count, donorDistance);
                                count++;
                            }

                        }

                        JSONArray sortedListOfDonors = new JSONArray();

                        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
                        for (int i = 0; i < listOfDonors.length(); i++) {
                            jsonValues.add(listOfDonors.getJSONObject(i));
                        }

                        Collections.sort(jsonValues, new Comparator<JSONObject>() {
                            private static final String KEY_NAME = "value";

                            @Override
                            public int compare(JSONObject a, JSONObject b) {
//                                String valA = new String();
//                                String valB = new String();
                                int valA = 0;
                                int valB = 0;

                                try {
//                                    valA = Integer.toString(a.getInt(KEY_NAME));
//                                    valB = Integer.toString(b.getInt(KEY_NAME));
                                    valA = a.getInt(KEY_NAME);
                                    valB = b.getInt(KEY_NAME);
                                }
                                catch (JSONException e) {
                                    //do something
                                    Toast.makeText(Donor_list_lastpage.this, e.toString(), Toast.LENGTH_LONG).show();
                                }

//                                return valA.compareTo(valB);
                                return valA-valB;
                                //if you want to change the sort order, simply use the following:
                                //return -valA.compareTo(valB);
                            }
                        });

                        for (int i = 0; i < listOfDonors.length(); i++) {
                            sortedListOfDonors.put(jsonValues.get(i));
                        }

                        if (count == 0) {
                            TextView sorry = new TextView(Donor_list_lastpage.this);
                            sorry.setText("There are no nearby donors matching the requested blood group.");
                            root.addView(sorry);
                        }
                        else {
                            TextView usernames[] = new TextView[count];
                            TextView distanceValues[] = new TextView[count];
                            TextView distanceTexts[] = new TextView[count];
                            TextView donorPhones[] = new TextView[count];

                            for (int i = 0; i < count; i++) {
                                JSONObject donor = sortedListOfDonors.getJSONObject(i);
                                String distanceText = donor.getString("text");
                                int distanceValue = donor.getInt("value");
                                String donorUsername = donor.getString("username");
                                final String donorPhone = donor.getString("phone");
//                                Toast.makeText(Donor_list_lastpage.this, donorUsername, Toast.LENGTH_LONG).show();

                                TextView donorUsernameTV = new TextView(Donor_list_lastpage.this);
                                TextView donorPhoneTV = new TextView(Donor_list_lastpage.this);
                                TextView distanceTextTV = new TextView(Donor_list_lastpage.this);
                                TextView distanceValueTV = new TextView(Donor_list_lastpage.this);

//                            Toast.makeText(Donor_list_lastpage.this,"After text view creation",Toast.LENGTH_LONG).show();

                                LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(190, 45);
                                LinearLayout.LayoutParams Params2 = new LinearLayout.LayoutParams(95, 45);
                                donorUsernameTV.setLayoutParams(Params1);
                                donorPhoneTV.setLayoutParams(Params1);
                                distanceTextTV.setLayoutParams(Params1);
                                distanceValueTV.setLayoutParams(Params1);

                                donorUsernameTV.setText(donorUsername);
//                            Toast.makeText(Donor_list_lastpage.this,"After username set",Toast.LENGTH_LONG).show();
                                donorPhoneTV.setText(donorPhone);
//                            Toast.makeText(Donor_list_lastpage.this,"After phone set",Toast.LENGTH_LONG).show();
                                distanceTextTV.setText(distanceText);
//                            Toast.makeText(Donor_list_lastpage.this,"After distance text set",Toast.LENGTH_LONG).show();
                                distanceValueTV.setText(Integer.toString(distanceValue));
//                            Toast.makeText(Donor_list_lastpage.this,"After distance value set",Toast.LENGTH_LONG).show();

//                                Toast.makeText(Donor_list_lastpage.this, "After texts set", Toast.LENGTH_LONG).show();

                                usernames[i] = donorUsernameTV;
                                distanceValues[i] = distanceValueTV;
                                distanceTexts[i] = distanceTextTV;
                                donorPhones[i] = donorPhoneTV;

//                                Toast.makeText(Donor_list_lastpage.this, "After reference copy", Toast.LENGTH_LONG).show();

                                LinearLayout donorView = new LinearLayout(Donor_list_lastpage.this);
                                donorView.setOrientation(LinearLayout.HORIZONTAL);

                                donorView.addView(donorUsernameTV);
//                            Toast.makeText(Donor_list_lastpage.this,"After username add",Toast.LENGTH_LONG).show();
                                donorView.addView(donorPhoneTV);
//                            Toast.makeText(Donor_list_lastpage.this,"After phone add add",Toast.LENGTH_LONG).show();
                                donorView.addView(distanceTextTV);
//                            Toast.makeText(Donor_list_lastpage.this,"After distance text add",Toast.LENGTH_LONG).show();
//                            donorView.setId(i);
                                donorView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        intent.setData(Uri.parse("tel:" + donorPhone));
                                        startActivity(intent);
                                    }
                                });
                                if (root == null) {
//                                    Toast.makeText(Donor_list_lastpage.this, "Inside root is null", Toast.LENGTH_LONG).show();
                                }

                                root.addView(donorView);
//                                Toast.makeText(Donor_list_lastpage.this, "After add to root", Toast.LENGTH_LONG).show();

                            }
                        }
//                        Toast.makeText(Donor_list_lastpage.this, "After everything count is " + count, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(Donor_list_lastpage.this, e.toString(), Toast.LENGTH_LONG).show();
                        Log.e("Error: ", e.getStackTrace().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Donor_list_lastpage.this, t.toString(), Toast.LENGTH_LONG).show();
                }

            });


        }
    }
    public void onBackPressed() {
        Intent i = new Intent(Donor_list_lastpage.this,SeekerHomepage.class);
        String username = getIntent().getStringExtra("username");
        i.putExtra("uname_text",username);
        i.putExtra("userType","seeker");
        startActivity(i);
        super.onBackPressed();
    }
}
interface disapi{
    @GET("json?")
    Call<ResponseBody> getData(@Query("units") String units,@Query("origins") String origins,@Query("destinations") String dest,@Query("key") String key);

}