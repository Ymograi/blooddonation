package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class AddDonationRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation_record);
        final EditText seeker = (EditText) findViewById(R.id.seeker_username);
        final EditText volunteer = (EditText) findViewById(R.id.volunteer_username);
        final EditText agency = (EditText) findViewById(R.id.agency);
        final TextView confirmation= (TextView) findViewById(R.id.confirmation);
        final Button submit = (Button) findViewById(R.id.submit);

        setTitle("Add a Blood Donation Record");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String seeker_username = seeker.getText().toString().trim();
                String volunteer_username = volunteer.getText().toString().trim();
                String agency_text = agency.getText().toString().trim();
                Calendar c = Calendar.getInstance();
                String date_time = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH)
                        + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
//                Toast.makeText(AddDonationRecord.this,date_time,Toast.LENGTH_SHORT).show();
                if(seeker_username.equalsIgnoreCase("")){
                    valid = false;
                    seeker.setError("Please enter a valid username.");
                }
                if(volunteer_username.equalsIgnoreCase("")){
                    valid = false;
                    volunteer.setError("Please enter a valid username.");
                }
                if(agency_text.equalsIgnoreCase("")){
                    valid = false;
                    agency.setError("Please enter a valid agency name.");
                }
                if(valid){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getResources().getString(R.string.URL))
                            .build();
                    AddDonRec addDonRec = retrofit.create(AddDonRec.class);
                    Call<ResponseBody> call =addDonRec.getData(seeker_username,volunteer_username,agency_text,date_time);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try{
                                String result = response.body().string();
//                                Toast.makeText(AddDonationRecord.this,result,Toast.LENGTH_LONG).show();
                                int end = result.lastIndexOf("}") + 1;
                                int start = result.indexOf("{");
                                result = result.substring(start, end);
                                JSONObject resultJSONStr = new JSONObject(result);
                                String errorCode = resultJSONStr.getString("error");
//                                Toast.makeText(AddDonationRecord.this, "hello i am here", Toast.LENGTH_SHORT).show();
                                if (errorCode.equals("false")){
                                    submit.setEnabled(false);
                                    confirmation.setText("Donation record added succesfully.");
                                }
                                else
                                {
                                    confirmation.setText(resultJSONStr.getString("error_msg"));
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(AddDonationRecord.this,e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AddDonationRecord.this,t.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    confirmation.setText("Please enter all details.");
                }
            }
        });
    }
    public void onBackPressed() {
        Intent i = new Intent(AddDonationRecord.this,AdminHomepage.class);
        String username = getIntent().getStringExtra("user");
        i.putExtra("uname_text",username);
        i.putExtra("userType","admin");
        startActivity(i);
        super.onBackPressed();
    }
}
interface AddDonRec{
    @FormUrlEncoded
    @POST("add_donation_record.php")
    Call<ResponseBody> getData(@Field("seeker") String seeker, @Field("volunteer") String voluteer, @Field("agency") String agency, @Field("date_time") String date_time);
}
